package com.ezdi.framework.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles
{

	List<String>			filesListInDir	= new ArrayList<String>();
	public static String	zipDirName		= null;

	/**
	 * This method is zipping folder and give name
	 * 
	 * @author skhalasi
	 * @since 14/11/2014
	 */
	public static void zipp()
	{
		Log4J.logp.info("Started -- Zipp");
		/* File file = new File("/Users/pankaj/sitemap.xml");
		 String zipFileName = "/Users/pankaj/sitemap.zip";*/

		/*File dir = new File("/home/likewise-open/EZDI-DOMAIN/skhalasi/git/repezQAAutomation/EzdiAutomationMaven/test-output");
		String zipDirName = "/home/likewise-open/EZDI-DOMAIN/skhalasi/git/repezQAAutomation/EzdiAutomationMaven/ZipContents/tmp.zip";
		*/
		File dir = new File("test-output");
		DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss_dd-MM-yyyy");
		Date date = new Date();
		String currentTime = dateFormat.format(date);
		Log4J.logp.info("Current time =" + currentTime);

		zipDirName = "ZipContents/" + currentTime;

		Log4J.logp.info("Test Output zip file path and name =" + zipDirName);

		//zipSingleFile(file, zipFileName);

		ZipFiles zipFiles = new ZipFiles();
		zipFiles.zipDirectory(dir, zipDirName);
		Log4J.logp.info("Ending -- Zipp");
	}

	/**
	 * This method zips the directory
	 * 
	 * @author skhalasi
	 * @since 14/11/2014
	 * @param dir
	 * @param zipDirName
	 */
	private void zipDirectory(File dir, String zipDirName)
	{
		try
		{
			Log4J.logp.info("Started -- zipDirectory");
			populateFilesList(dir);
			//now zip files one by one
			//create ZipOutputStream to write to the zip file
			FileOutputStream fos = new FileOutputStream(zipDirName);
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (String filePath : filesListInDir)
			{
				//for ZipEntry we need to keep only relative file path, so we used substring on absolute path
				ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
				zos.putNextEntry(ze);
				//read the file and write to ZipOutputStream
				FileInputStream fis = new FileInputStream(filePath);
				byte[] buffer = new byte[2048];
				int len;
				while ((len = fis.read(buffer)) > 0)
				{
					zos.write(buffer, 0, len);
				}
				zos.closeEntry();
				fis.close();
			}
			zos.close();
			fos.close();
			Log4J.logp.info("Ending -- zipDirectory");

		}
		catch (IOException e)
		{
			Log4J.logp.error("Problem found in -- zipDirectory");
			e.printStackTrace();
		}
	}

	/**
	 * This method populates all the files in a directory to a List
	 * 
	 * @author skhalasi
	 * @since 14/11/2014
	 * @param dir
	 * @throws IOException
	 */
	private void populateFilesList(File dir) throws IOException
	{
		Log4J.logp.info("Started -- populateFilesList");
		File[] files = dir.listFiles();
		for (File file : files)
		{
			if (file.isFile())
				filesListInDir.add(file.getAbsolutePath());
			else
				populateFilesList(file);
		}
		Log4J.logp.info("Ending -- populateFilesList");

	}

}