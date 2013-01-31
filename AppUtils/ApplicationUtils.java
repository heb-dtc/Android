public class ApplicationUtils {
	public static Bitmap decodeSampledBitmapFromNetwork(String path, int width, int height) {
		//Log.v(TAG, "decodeSampledBitmapFromNetwork");
		URL url = null;
		InputStream input = null;
		HttpURLConnection connection = null;
		
		try {
			url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			
			input = connection.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*WindowManager wm = (WindowManager) mContext.getSystemService("window");
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		Rect displayRect = new Rect();
		displayRect.set(0, 0, metrics.widthPixels, metrics.heightPixels);*/
		
	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeStream(input, null, options);
	    
	    try {
			input.close();
			connection.disconnect();
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			
			input = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    
	    // Calculate inSampleSize
	    int sampleSize = calculateInSampleSize(options, width, height);
	    options.inSampleSize = sampleSize;
	    //Log.v(TAG, "Calculate inSampleSize: " + sampleSize);
	    
	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    Bitmap img = BitmapFactory.decodeStream(input, null, options);
	    
	    try {
			input.close();
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    return img;
	}
	
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float)height / (float)reqHeight);
			} else {
				inSampleSize = Math.round((float)width / (float)reqWidth);
			}
		}
		
		return inSampleSize;
	}
	
	static public int stringToSec(String time){
		int result = 0;
		
		String hours;
		String minutes;
		String seconds;
		  
		if(time != null){
			//grab the hours
			hours = time.substring(0, 2);
			result = Integer.parseInt(hours) * 3600;
			  
			//grab the minutes
			minutes = time.substring(3, 5);
			result += Integer.parseInt(minutes) * 60;
			  
			//grab the seconds
			seconds = time.substring(6, time.length());
			result += Integer.parseInt(seconds);
			  
			return result;
		}
		  
		return -1;
	}
	
	static public String secToString(int time){
		if(time != 0){
			int seconds = (time % 60);
			int minutes = ((time/60) % 60);
			int hours = ((time/3600) % 24);
		  
			String secondsStr = (seconds<10 ? "0" : "")+ seconds;
			String minutesStr = (minutes<10 ? "0" : "")+ minutes;
			String hoursStr = (hours<10 ? "0" : "")+ hours;
	  
			return new String(hoursStr + ":" + minutesStr + ":" + secondsStr);
		}
	  
		return null;
	}
	
	static public List<String> parseCSVString(String list){
		List<String> result = null;
	 
		result = new ArrayList<String>();
	  
		int commaPos = list.indexOf(','); //get the index of the first comma
	  
		while(commaPos != -1){
			result.add(list.substring(0, commaPos));
			list = list.substring(commaPos+1, list.length());
			
			commaPos = list.indexOf(',');
		}
	  
		result. add(list);
		return result;
	}
}