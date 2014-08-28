package kr.web.dev.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File 관련 유틸
 * @version 1.0
 * @author jrkang
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
    /**
     * 해당 파일의 hash string값을 리턴한다.
     * @param String filename
     * @return String
     * @throws Exception
     * @author jrkang
     * @date 2014-07-30
     */
	public static String extractHashSHA256FromFile(String filename) throws Exception {
		String SHA = "";
		int buff = 16384;
		try {
			RandomAccessFile file = new RandomAccessFile( filename , "r" );
			MessageDigest hashSum = MessageDigest.getInstance( "SHA-256" );
			byte[] buffer = new byte[buff];
			byte[] partialHash = null;
			long read = 0;
			// calculate the hash of the hole file for the test
			long offset = file.length();
			int unitsize;
			while(read < offset) {
			    unitsize = ( int ) ( ( ( offset - read ) >= buff ) ? buff : ( offset - read ) );
			    file.read( buffer , 0 , unitsize );
			    hashSum.update( buffer , 0 , unitsize );
			    read += unitsize;
			}
			file.close();
			partialHash = new byte[hashSum.getDigestLength()];
			partialHash = hashSum.digest();
			StringBuffer sb = new StringBuffer();
			for( int i = 0 ; i < partialHash.length ; i++ ) {
			    sb.append( Integer.toString( ( partialHash[ i ] & 0xff ) + 0x100 , 16 ).substring( 1 ) );
			}
			SHA = sb.toString( );
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return SHA;
	}
	
    /**
     * 해당 InputStream의 hash string값을 리턴한다.
     * @param InputStream is
     * @return String
     * @throws 
     * @author jrkang
     * @date 2014-07-30
     */
	public static String extractHashSHA256FromInputStream(InputStream is) {
		String output;
		int read;
		byte[] buffer = new byte[8192];

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}
			byte[] hash = digest.digest();
			BigInteger bigInt = new BigInteger(1, hash);
			output = bigInt.toString(16);
		} 
		catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return output;
	}
	
    /**
     * 해당 file의 InputStream값을 리턴한다.
     * @param String fileName
     * @return InputStream
     * @throws 
     * @author jrkang
     * @date 2014-07-30
     */
	public static InputStream getFileInputStream(String fileName) {
		InputStream in = null;
		try {
			in = new FileInputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return in;
	}
	
}
