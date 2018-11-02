 
package DSpeechTyperEn;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import java.net.URL;

  class SpeechRecognizer implements Runnable
{
	 private Recognizer mRecognizer = null;
         private Microphone mMicrophone = null;

	 private volatile Thread mRecognitionThread = null;

	 private boolean mRecognitionThreadEnabled = false;
         
	 private LinkedList<String> mRecognizedStringQueue;
	
        public SpeechRecognizer()
	{ 	
               //   cm = new ConfigurationManager(DSpeechTyper.class.getResource("DSpeechTyper.config.xml"));
           
                  ConfigurationManager cm ;
                   cm = new ConfigurationManager(DSpeechTyperEn.class.getResource("DSpeechTyperEn.config.xml"));
                      System.out.println("Loading...");
			mRecognizer = (Recognizer) cm.lookup("recognizer");
			mRecognizer.allocate();
                        mMicrophone = (Microphone) cm.lookup("microphone");
		   
		   // allocate the recognizer
         	 
              mRecognizedStringQueue = new LinkedList<String>();
	}
     
	 public void run()
	{
	//	System.out.println("debug Recognition thread starting");

		while (true == mRecognitionThreadEnabled)
		{
			if (!mMicrophone.isRecording())
			{
				System.out.println("warning Recognition thread is running, but " 
					+ "the microphone is disabled.");
			}
			else
			{
				Result result = mRecognizer.recognize();

				if (result != null)
				{
					String s = result.getBestFinalResultNoFiller();

					// Only save non-empty strings.
					if (!s.equals(""))
					{
						//System.out.println("debug  Finished recognizing");
						mRecognizedStringQueue.addLast(s);
					}
				}
			}
		}

		// System.out.println("debug Recognition thread finished");
	}

    public int getQueueSize()
	{
		return mRecognizedStringQueue.size();
	}

     public String popString()
	{
		if (getQueueSize() > 0)
		{
			return mRecognizedStringQueue.removeFirst();
		}
		else
		{
			return "";
		}
	}

	public void setEnabled(boolean e)
	{
		if (e)
		{
			System.out.println("debug Starting microphone...");
			boolean success = mMicrophone.startRecording();
			System.out.println("debug  Microphone on");

			if (!success)
			{
				System.out.println("warning Cannot initialize microphone. " + 
					"Speech recognition disabled.");
				return;
			}
			else
			{
				if (null != mRecognitionThread)
				{
					System.out.println("warning New recognition thread being " 
						+ "created before the previous one finished.");
				}

				mRecognitionThread = new Thread(this, "Recognition thread");

				// Start running the recognition thread.
				mRecognitionThreadEnabled = true;
				mRecognitionThread.start();
			}
		}
		else
		{
			System.out.println("debug Stopping microphone...");
			mMicrophone.stopRecording();
			System.out.println("debug  Microphone off");

			// The following line indirectly stops the recognition thread 
			// from running.  The next time the recognition thread checks 
			// this variable, it will stop running.
			mRecognitionThreadEnabled = false;

			// Wait for the thread to die before proceeding.
			while (mRecognitionThread.isAlive())
			{
				System.out.println("debug Waiting for recognition thread to die...");

				try
				{
					// Have the main thread sleep for a bit...
					Thread.sleep(100);
				}
				catch (InterruptedException exception)
				{
				}
			}

			mRecognitionThread = null;
			mMicrophone.clear();

			System.out.println("debug Clearing recognized string queue");
			mRecognizedStringQueue.clear();
		}
	}

    public boolean isEnabled()
	{
		return mMicrophone.isRecording();
	}

	 public void destroy()
	{
		// This function call will shut down everything, including the 
		// recognition thread.
		setEnabled(false);

		// It should now be safe to deallocate the recognizer.
		mRecognizer.deallocate();
	}
}
public class DSpeechTyperEn
{
  
    private static SpeechRecognizer mRecognizer = null;
    
  	public static void initEi()
	{
                       
		  	mRecognizer = new SpeechRecognizer();
                       // setRecognizerEnabled(true);
       }
       
	 public static void destroy()
	{
		System.out.println("debug  Shutting down...");
		 
		if (null != mRecognizer)
		{
			mRecognizer.destroy();
		}

		System.out.println("  Shutdown complete");
	}
      
	 

	/// Returns the number of recognized strings currently in the 
	/// recognizer's queue.
	public static int getRecognizerQueueSize()
	{
		if (null == mRecognizer)
		{
			System.out.println("warning  getRecognizerQueueSize "
				+ "called before recognizer was initialized.  Returning " 
				+ "0.");
			return 0;
		}

		return mRecognizer.getQueueSize();
	}

     public static String popRecognizedString()
	{
		if (null == mRecognizer)
		{
			System.out.println("warning  popRecognizedString "
				+ "called before recognizer was initialized.  Returning " 
				+ "an empty string.");
			return "";
		}

		return mRecognizer.popString();
	}

	 public static void setRecognizerEnabled(boolean e)
	{
		if (null == mRecognizer)
		{
			System.out.println("warning  setRecognizerEnabled "
				+ "called before recognizer was initialized.  Request " 
				+ "will be ignored.");
			return;
		}

		mRecognizer.setEnabled(e);
	}

    public static boolean isRecognizerEnabled()
	{
		if (null == mRecognizer)
		{
			System.out.println("warning  isRecognizerEnabled "
				+ "called before recognizer was initialized.  Returning " 
				+ "false.");
			return false;
		}	

		return mRecognizer.isEnabled();
	}

}
