/**
 * 
 */
package com.puntojapon.articles;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 * @author jdecastroc
 *
 */
public class KettleTransformation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            /**
             * Initialize the Kettle Enviornment
             */
            KettleEnvironment.init();

            /**
             * Create a trans object to properly assign the ktr metadata.
             * 
             * @filedb: The ktr file path to be executed.
             * 
             */
            TransMeta metadata = new TransMeta("src/main/resources/RSS to json.ktr");
            Trans trans = new Trans(metadata);

            // Execute the transformation
            trans.execute(null);
            trans.waitUntilFinished();

            // checking for errors
            if (trans.getErrors() > 0) {
                System.out.println("Erroruting Transformation");
            }
            // Executing bash script to index the files to elastic search
            else {
            	ElasticSearch.filesIndexation();
            }
            
            
        } catch (KettleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}

}
