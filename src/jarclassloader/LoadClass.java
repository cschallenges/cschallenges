package jarclassloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.MalformedInputException;

public class LoadClass {
	private Object playerObject;
	
	public LoadClass(String s, String init) {
		URL[] classLoaderUrls = new URL[1];
		try {
			classLoaderUrls[0] = new URL("http://cschallenges.org/players/" + s + "/" + s + ".jar");
		} catch( MalformedURLException e) {
			e.printStackTrace();
		}
		URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
		
		Class<?> playerClass = null;
		try {
			playerClass = urlClassLoader.loadClass(s + "." + init);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Constructor<?> constructor = null;
		try {
			constructor = playerClass.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerObject = null;
		try {
			playerObject = constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Object getPlayer() {
		return playerObject;
	}
}