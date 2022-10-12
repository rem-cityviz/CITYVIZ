// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package cityviz.public_market_extract_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.API;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: public_market_extract Purpose: Execution de requête http vers l'API
 * open-data pour récupération des données de marchés publiques<br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class public_market_extract implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (cityviz_staging_AdditionalParams != null) {

				this.setProperty("cityviz_staging_AdditionalParams", cityviz_staging_AdditionalParams.toString());

			}

			if (cityviz_staging_Database != null) {

				this.setProperty("cityviz_staging_Database", cityviz_staging_Database.toString());

			}

			if (cityviz_staging_Login != null) {

				this.setProperty("cityviz_staging_Login", cityviz_staging_Login.toString());

			}

			if (cityviz_staging_Password != null) {

				this.setProperty("cityviz_staging_Password", cityviz_staging_Password.toString());

			}

			if (cityviz_staging_Port != null) {

				this.setProperty("cityviz_staging_Port", cityviz_staging_Port.toString());

			}

			if (cityviz_staging_Schema != null) {

				this.setProperty("cityviz_staging_Schema", cityviz_staging_Schema.toString());

			}

			if (cityviz_staging_Server != null) {

				this.setProperty("cityviz_staging_Server", cityviz_staging_Server.toString());

			}

			if (api_result_filename != null) {

				this.setProperty("api_result_filename", api_result_filename.toString());

			}

			if (data_storage_path != null) {

				this.setProperty("data_storage_path", data_storage_path.toString());

			}

			if (param_file_path != null) {

				this.setProperty("param_file_path", param_file_path.toString());

			}

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

		public String cityviz_staging_AdditionalParams;

		public String getCityviz_staging_AdditionalParams() {
			return this.cityviz_staging_AdditionalParams;
		}

		public String cityviz_staging_Database;

		public String getCityviz_staging_Database() {
			return this.cityviz_staging_Database;
		}

		public String cityviz_staging_Login;

		public String getCityviz_staging_Login() {
			return this.cityviz_staging_Login;
		}

		public java.lang.String cityviz_staging_Password;

		public java.lang.String getCityviz_staging_Password() {
			return this.cityviz_staging_Password;
		}

		public String cityviz_staging_Port;

		public String getCityviz_staging_Port() {
			return this.cityviz_staging_Port;
		}

		public String cityviz_staging_Schema;

		public String getCityviz_staging_Schema() {
			return this.cityviz_staging_Schema;
		}

		public String cityviz_staging_Server;

		public String getCityviz_staging_Server() {
			return this.cityviz_staging_Server;
		}

		public String api_result_filename;

		public String getApi_result_filename() {
			return this.api_result_filename;
		}

		public String data_storage_path;

		public String getData_storage_path() {
			return this.data_storage_path;
		}

		public String param_file_path;

		public String getParam_file_path() {
			return this.param_file_path;
		}
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "public_market_extract";
	private final String projectName = "CITYVIZ";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					public_market_extract.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(public_market_extract.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tDBConnection_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBConnection_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileFetch_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputJSON_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tWarn_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBCommit_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBCommit_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBConnection_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputJSON_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBCommit_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBConnection_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBConnection_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tDBConnection_1 begin ] start
				 */

				ok_Hash.put("tDBConnection_1", false);
				start_Hash.put("tDBConnection_1", System.currentTimeMillis());

				currentComponent = "tDBConnection_1";

				int tos_count_tDBConnection_1 = 0;

				String dbProperties_tDBConnection_1 = context.cityviz_staging_AdditionalParams;
				String url_tDBConnection_1 = "jdbc:postgresql://" + context.cityviz_staging_Server + ":"
						+ context.cityviz_staging_Port + "/" + context.cityviz_staging_Database;

				if (dbProperties_tDBConnection_1 != null && !"".equals(dbProperties_tDBConnection_1.trim())) {
					url_tDBConnection_1 = url_tDBConnection_1 + "?" + dbProperties_tDBConnection_1;
				}
				String dbUser_tDBConnection_1 = context.cityviz_staging_Login;

				final String decryptedPassword_tDBConnection_1 = context.cityviz_staging_Password;
				String dbPwd_tDBConnection_1 = decryptedPassword_tDBConnection_1;

				java.sql.Connection conn_tDBConnection_1 = null;

				java.util.Enumeration<java.sql.Driver> drivers_tDBConnection_1 = java.sql.DriverManager.getDrivers();
				java.util.Set<String> redShiftDriverNames_tDBConnection_1 = new java.util.HashSet<String>(
						java.util.Arrays.asList("com.amazon.redshift.jdbc.Driver", "com.amazon.redshift.jdbc41.Driver",
								"com.amazon.redshift.jdbc42.Driver"));
				while (drivers_tDBConnection_1.hasMoreElements()) {
					java.sql.Driver d_tDBConnection_1 = drivers_tDBConnection_1.nextElement();
					if (redShiftDriverNames_tDBConnection_1.contains(d_tDBConnection_1.getClass().getName())) {
						try {
							java.sql.DriverManager.deregisterDriver(d_tDBConnection_1);
							java.sql.DriverManager.registerDriver(d_tDBConnection_1);
						} catch (java.lang.Exception e_tDBConnection_1) {
							globalMap.put("tDBConnection_1_ERROR_MESSAGE", e_tDBConnection_1.getMessage());
							// do nothing
						}
					}
				}
				String driverClass_tDBConnection_1 = "org.postgresql.Driver";
				java.lang.Class jdbcclazz_tDBConnection_1 = java.lang.Class.forName(driverClass_tDBConnection_1);
				globalMap.put("driverClass_tDBConnection_1", driverClass_tDBConnection_1);

				conn_tDBConnection_1 = java.sql.DriverManager.getConnection(url_tDBConnection_1, dbUser_tDBConnection_1,
						dbPwd_tDBConnection_1);

				globalMap.put("conn_tDBConnection_1", conn_tDBConnection_1);
				if (null != conn_tDBConnection_1) {

					conn_tDBConnection_1.setAutoCommit(false);
				}

				globalMap.put("schema_" + "tDBConnection_1", context.cityviz_staging_Schema);

				/**
				 * [tDBConnection_1 begin ] stop
				 */

				/**
				 * [tDBConnection_1 main ] start
				 */

				currentComponent = "tDBConnection_1";

				tos_count_tDBConnection_1++;

				/**
				 * [tDBConnection_1 main ] stop
				 */

				/**
				 * [tDBConnection_1 process_data_begin ] start
				 */

				currentComponent = "tDBConnection_1";

				/**
				 * [tDBConnection_1 process_data_begin ] stop
				 */

				/**
				 * [tDBConnection_1 process_data_end ] start
				 */

				currentComponent = "tDBConnection_1";

				/**
				 * [tDBConnection_1 process_data_end ] stop
				 */

				/**
				 * [tDBConnection_1 end ] start
				 */

				currentComponent = "tDBConnection_1";

				ok_Hash.put("tDBConnection_1", true);
				end_Hash.put("tDBConnection_1", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk1", 0, "ok");
				}
				tFileInputDelimited_1Process(globalMap);

				/**
				 * [tDBConnection_1 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBConnection_1 finally ] start
				 */

				currentComponent = "tDBConnection_1";

				/**
				 * [tDBConnection_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBConnection_1_SUBPROCESS_STATE", 1);
	}

	public static class API_URLStruct implements routines.system.IPersistableRow<API_URLStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_public_market_extract = new byte[0];
		static byte[] commonByteArray_CITYVIZ_public_market_extract = new byte[0];

		public String URL;

		public String getURL() {
			return this.URL;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_public_market_extract.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_public_market_extract.length == 0) {
						commonByteArray_CITYVIZ_public_market_extract = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_public_market_extract = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_public_market_extract, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_public_market_extract, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_public_market_extract.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_public_market_extract.length == 0) {
						commonByteArray_CITYVIZ_public_market_extract = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_public_market_extract = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_public_market_extract, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_public_market_extract, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_public_market_extract) {

				try {

					int length = 0;

					this.URL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_public_market_extract) {

				try {

					int length = 0;

					this.URL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.URL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.URL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("URL=" + URL);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(API_URLStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_public_market_extract = new byte[0];
		static byte[] commonByteArray_CITYVIZ_public_market_extract = new byte[0];

		public String dataset;

		public String getDataset() {
			return this.dataset;
		}

		public String q;

		public String getQ() {
			return this.q;
		}

		public String rows;

		public String getRows() {
			return this.rows;
		}

		public String start;

		public String getStart() {
			return this.start;
		}

		public String FR;

		public String getFR() {
			return this.FR;
		}

		public String geo_long;

		public String getGeo_long() {
			return this.geo_long;
		}

		public String geo_lat;

		public String getGeo_lat() {
			return this.geo_lat;
		}

		public String distance;

		public String getDistance() {
			return this.distance;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_public_market_extract.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_public_market_extract.length == 0) {
						commonByteArray_CITYVIZ_public_market_extract = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_public_market_extract = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_public_market_extract, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_public_market_extract, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_public_market_extract.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_public_market_extract.length == 0) {
						commonByteArray_CITYVIZ_public_market_extract = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_public_market_extract = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_public_market_extract, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_public_market_extract, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_public_market_extract) {

				try {

					int length = 0;

					this.dataset = readString(dis);

					this.q = readString(dis);

					this.rows = readString(dis);

					this.start = readString(dis);

					this.FR = readString(dis);

					this.geo_long = readString(dis);

					this.geo_lat = readString(dis);

					this.distance = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_public_market_extract) {

				try {

					int length = 0;

					this.dataset = readString(dis);

					this.q = readString(dis);

					this.rows = readString(dis);

					this.start = readString(dis);

					this.FR = readString(dis);

					this.geo_long = readString(dis);

					this.geo_lat = readString(dis);

					this.distance = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.dataset, dos);

				// String

				writeString(this.q, dos);

				// String

				writeString(this.rows, dos);

				// String

				writeString(this.start, dos);

				// String

				writeString(this.FR, dos);

				// String

				writeString(this.geo_long, dos);

				// String

				writeString(this.geo_lat, dos);

				// String

				writeString(this.distance, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.dataset, dos);

				// String

				writeString(this.q, dos);

				// String

				writeString(this.rows, dos);

				// String

				writeString(this.start, dos);

				// String

				writeString(this.FR, dos);

				// String

				writeString(this.geo_long, dos);

				// String

				writeString(this.geo_lat, dos);

				// String

				writeString(this.distance, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("dataset=" + dataset);
			sb.append(",q=" + q);
			sb.append(",rows=" + rows);
			sb.append(",start=" + start);
			sb.append(",FR=" + FR);
			sb.append(",geo_long=" + geo_long);
			sb.append(",geo_lat=" + geo_lat);
			sb.append(",distance=" + distance);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row2Struct row2 = new row2Struct();
				API_URLStruct API_URL = new API_URLStruct();

				/**
				 * [tFileFetch_1 begin ] start
				 */

				ok_Hash.put("tFileFetch_1", false);
				start_Hash.put("tFileFetch_1", System.currentTimeMillis());

				currentComponent = "tFileFetch_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "API_URL");
				}

				int tos_count_tFileFetch_1 = 0;

				/**
				 * [tFileFetch_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
					String urlFromParams;
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				API_URLStruct API_URL_tmp = new API_URLStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = context.param_file_path;
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								context.param_file_path, "ISO-8859-15", ";", "\n", true, 1, 0,
								limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row2 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row2 = new row2Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							columnIndexWithD_tFileInputDelimited_1 = 0;

							row2.dataset = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 1;

							row2.q = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row2.rows = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row2.start = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row2.FR = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row2.geo_long = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							row2.geo_lat = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 7;

							row2.distance = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row2 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row2"
						if (row2 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row2"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;
								Var.urlFromParams = API.buildURL(row2.dataset, row2.q, row2.rows, row2.start, row2.FR,
										row2.geo_long, row2.geo_lat, row2.distance);// ###############################
								// ###############################
								// # Output tables

								API_URL = null;

// # Output table : 'API_URL'
								API_URL_tmp.URL = Var.urlFromParams;
								API_URL = API_URL_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_begin ] stop
							 */
// Start of branch "API_URL"
							if (API_URL != null) {

								/**
								 * [tFileFetch_1 main ] start
								 */

								currentComponent = "tFileFetch_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "API_URL"

									);
								}

								java.io.InputStream retIS_tFileFetch_1 = null;

								java.net.URI uriToFetch_tFileFetch_1 = null;
								try {
									uriToFetch_tFileFetch_1 = new java.net.URI(API_URL.URL);
								} catch (Exception e) {
									globalMap.put("tFileFetch_1_ERROR_MESSAGE", e.getMessage());
									System.err.println(
											"URI is not correct or not encoded, please input a valid one or use 'Encode URI' option");
								}

								class SocketFactory_tFileFetch_1
										implements org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory {

									private javax.net.ssl.SSLContext sslcontext = null;

									private javax.net.ssl.SSLContext createSSLContext() throws java.io.IOException {
										javax.net.ssl.SSLContext sslcontext = null;
										try {
											sslcontext = javax.net.ssl.SSLContext.getInstance("SSL");
											javax.net.ssl.KeyManager[] keyManagers = null;
											String keyStoreType = java.util.Optional
													.ofNullable(System.getProperty("javax.net.ssl.keyStoreType"))
													.orElse("");
											String keyStoreFile = java.util.Optional
													.ofNullable(System.getProperty("javax.net.ssl.keyStore"))
													.orElse("");
											String keyStorePassword = java.util.Optional
													.ofNullable(System.getProperty("javax.net.ssl.keyStorePassword"))
													.orElse("");
											if (!keyStoreType.isEmpty() && !keyStoreFile.isEmpty()) {
												try {
													java.security.KeyStore keyStore = java.security.KeyStore
															.getInstance(keyStoreType);
													keyStore.load(new java.io.FileInputStream(keyStoreFile),
															keyStorePassword.toCharArray());
													javax.net.ssl.KeyManagerFactory kmf = javax.net.ssl.KeyManagerFactory
															.getInstance(javax.net.ssl.KeyManagerFactory
																	.getDefaultAlgorithm());
													kmf.init(keyStore, keyStorePassword.toCharArray());
													keyManagers = kmf.getKeyManagers();
												} catch (java.security.UnrecoverableKeyException
														| java.security.KeyStoreException
														| java.security.cert.CertificateException e) {
													globalMap.put("tFileFetch_1_ERROR_MESSAGE", e.getMessage());
													e.printStackTrace();
												}
											}
											javax.net.ssl.TrustManager[] trustManagers = new javax.net.ssl.TrustManager[] {
													new TrustAnyTrustManager() };
											String trustStoreType = java.util.Optional
													.ofNullable(System.getProperty("javax.net.ssl.trustStoreType"))
													.orElse("");
											String trustStoreFile = java.util.Optional
													.ofNullable(System.getProperty("javax.net.ssl.trustStore"))
													.orElse("");
											String trustStorePassword = java.util.Optional
													.ofNullable(System.getProperty("javax.net.ssl.trustStorePassword"))
													.orElse("");
											if (!trustStoreType.isEmpty() && !trustStoreFile.isEmpty()) {
												try {
													java.security.KeyStore trustStore = java.security.KeyStore
															.getInstance(trustStoreType);
													trustStore.load(new java.io.FileInputStream(trustStoreFile),
															trustStorePassword.toCharArray());
													javax.net.ssl.TrustManagerFactory tmf = javax.net.ssl.TrustManagerFactory
															.getInstance(javax.net.ssl.TrustManagerFactory
																	.getDefaultAlgorithm());
													tmf.init(trustStore);
													trustManagers = tmf.getTrustManagers();

												} catch (java.security.KeyStoreException
														| java.security.cert.CertificateException e) {
													globalMap.put("tFileFetch_1_ERROR_MESSAGE", e.getMessage());
													e.printStackTrace();
													trustManagers = new javax.net.ssl.TrustManager[] {};

												}
											}
											sslcontext.init(keyManagers, trustManagers,
													new java.security.SecureRandom());
										} catch (java.security.NoSuchAlgorithmException e) {
											globalMap.put("tFileFetch_1_ERROR_MESSAGE", e.getMessage());

											e.printStackTrace();
										} catch (java.security.KeyManagementException e) {
											globalMap.put("tFileFetch_1_ERROR_MESSAGE", e.getMessage());

											e.printStackTrace();
										}
										return sslcontext;
									}

									private javax.net.ssl.SSLContext getSSLContext() throws java.io.IOException {

										if (this.sslcontext == null) {
											this.sslcontext = createSSLContext();
										}
										return this.sslcontext;
									}

									public java.net.Socket createSocket(java.net.Socket socket, String host, int port,
											boolean autoClose)
											throws java.io.IOException, java.net.UnknownHostException {
										return getSSLContext().getSocketFactory().createSocket(socket, host, port,
												autoClose);
									}

									public java.net.Socket createSocket(String host, int port)
											throws java.io.IOException, java.net.UnknownHostException {
										return getSSLContext().getSocketFactory().createSocket(host, port);
									}

									public java.net.Socket createSocket(String host, int port,
											java.net.InetAddress clientHost, int clientPort)
											throws java.io.IOException, java.net.UnknownHostException {
										return getSSLContext().getSocketFactory().createSocket(host, port, clientHost,
												clientPort);
									}

									public java.net.Socket createSocket(String host, int port,
											java.net.InetAddress localAddress, int localPort,
											org.apache.commons.httpclient.params.HttpConnectionParams params)
											throws java.io.IOException, java.net.UnknownHostException,
											org.apache.commons.httpclient.ConnectTimeoutException {

										if (params == null) {
											throw new IllegalArgumentException("Parameters may not be null");
										}
										int timeout = params.getConnectionTimeout();
										javax.net.SocketFactory socketfactory = getSSLContext().getSocketFactory();

										if (timeout == 0) {
											return socketfactory.createSocket(host, port, localAddress, localPort);
										} else {
											java.net.Socket socket = socketfactory.createSocket();
											java.net.SocketAddress localaddr = new java.net.InetSocketAddress(
													localAddress, localPort);
											java.net.SocketAddress remoteaddr = new java.net.InetSocketAddress(host,
													port);
											socket.bind(localaddr);
											socket.connect(remoteaddr, timeout);
											return socket;
										}
									}

									class TrustAnyTrustManager implements javax.net.ssl.X509TrustManager {
										public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
												String authType) throws java.security.cert.CertificateException {
										}

										public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
												String authType) throws java.security.cert.CertificateException {
										}

										public java.security.cert.X509Certificate[] getAcceptedIssuers() {
											return new java.security.cert.X509Certificate[] {};
										}
									}
								}

								org.apache.commons.httpclient.protocol.Protocol myhttps_tFileFetch_1 = new org.apache.commons.httpclient.protocol.Protocol(
										"https", new SocketFactory_tFileFetch_1(), 443);
								org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",
										myhttps_tFileFetch_1);
								org.apache.commons.httpclient.HttpClient client_tFileFetch_1 = new org.apache.commons.httpclient.HttpClient();

								client_tFileFetch_1.getHttpConnectionManager().getParams().setConnectionTimeout(30000);

								client_tFileFetch_1.getParams()
										.setCookiePolicy(org.apache.commons.httpclient.cookie.CookiePolicy.DEFAULT);

								org.apache.commons.httpclient.HttpMethod method_tFileFetch_1 = new org.apache.commons.httpclient.methods.GetMethod(
										uriToFetch_tFileFetch_1.toString());

								boolean isContinue_tFileFetch_1 = true;
								int status_tFileFetch_1;
								String finalURL_tFileFetch_1 = uriToFetch_tFileFetch_1.toString();

								try { // B_01

									status_tFileFetch_1 = client_tFileFetch_1.executeMethod(method_tFileFetch_1);

									if (!(status_tFileFetch_1 >= 200 && status_tFileFetch_1 < 300)) {// Status code 2XX
																										// indicates
																										// success
										throw new java.lang.Exception(
												"Method failed: " + method_tFileFetch_1.getStatusLine());
									}

								} catch (java.lang.Exception e) {
									globalMap.put("tFileFetch_1_ERROR_MESSAGE", e.getMessage());

									throw (e);

								}

								if (isContinue_tFileFetch_1) {

									java.io.InputStream in_tFileFetch_1 = method_tFileFetch_1.getResponseBodyAsStream();
									String sDir_tFileFetch_1 = (context.data_storage_path).trim();
									String fileName_tFileFetch_1 = (context.api_result_filename).trim();
									// open directory
									java.net.URL url_tFileFetch_1 = new java.net.URL(finalURL_tFileFetch_1);
									String sURIPath_tFileFetch_1 = "";
									int iLastSlashIndex_tFileFetch_1 = 0;
									sURIPath_tFileFetch_1 = url_tFileFetch_1.getFile();
									iLastSlashIndex_tFileFetch_1 = sURIPath_tFileFetch_1.lastIndexOf("/");

									// if not input file name, get the name from URI
									if ("".equals(fileName_tFileFetch_1)) {
										if (iLastSlashIndex_tFileFetch_1 > 0
												&& (!sURIPath_tFileFetch_1.endsWith("/"))) {
											fileName_tFileFetch_1 = sURIPath_tFileFetch_1
													.substring(iLastSlashIndex_tFileFetch_1 + 1);
										} else {
											fileName_tFileFetch_1 = "defaultfilename.txt";
										}
									}
									java.io.File dir_tFileFetch_1 = new java.io.File(sDir_tFileFetch_1);

									// pretreatment
									try {
										java.io.File test_file_tFileFetch_1 = new java.io.File(dir_tFileFetch_1,
												fileName_tFileFetch_1);
										test_file_tFileFetch_1.getParentFile().mkdirs();

										if (test_file_tFileFetch_1.createNewFile()) {
											test_file_tFileFetch_1.delete();
										}
									} catch (java.lang.Exception e) {
										globalMap.put("tFileFetch_1_ERROR_MESSAGE", e.getMessage());

										fileName_tFileFetch_1 = "defaultfilename.txt";
									}
									java.io.File file_tFileFetch_1 = new java.io.File(dir_tFileFetch_1,
											fileName_tFileFetch_1);
									file_tFileFetch_1.getParentFile().mkdirs();
									java.io.FileOutputStream out_tFileFetch_1 = new java.io.FileOutputStream(
											file_tFileFetch_1);
									byte[] buffer_tFileFetch_1 = new byte[1024];
									int count_tFileFetch_1 = 0;

									while ((count_tFileFetch_1 = in_tFileFetch_1.read(buffer_tFileFetch_1)) > 0) {
										out_tFileFetch_1.write(buffer_tFileFetch_1, 0, count_tFileFetch_1);
									}
									// close opened object
									in_tFileFetch_1.close();
									out_tFileFetch_1.close();

									method_tFileFetch_1.releaseConnection();

								} // B_01
								globalMap.put("tFileFetch_1_INPUT_STREAM", retIS_tFileFetch_1);

								tos_count_tFileFetch_1++;

								/**
								 * [tFileFetch_1 main ] stop
								 */

								/**
								 * [tFileFetch_1 process_data_begin ] start
								 */

								currentComponent = "tFileFetch_1";

								/**
								 * [tFileFetch_1 process_data_begin ] stop
								 */

								/**
								 * [tFileFetch_1 process_data_end ] start
								 */

								currentComponent = "tFileFetch_1";

								/**
								 * [tFileFetch_1 process_data_end ] stop
								 */

							} // End of branch "API_URL"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row2"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) (context.param_file_path) instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tFileFetch_1 end ] start
				 */

				currentComponent = "tFileFetch_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "API_URL");
				}

				ok_Hash.put("tFileFetch_1", true);
				end_Hash.put("tFileFetch_1", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk2", 0, "ok");
				}
				tFileInputJSON_3Process(globalMap);

				/**
				 * [tFileFetch_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tFileFetch_1 finally ] start
				 */

				currentComponent = "tFileFetch_1";

				/**
				 * [tFileFetch_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_public_market_extract = new byte[0];
		static byte[] commonByteArray_CITYVIZ_public_market_extract = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String recordid;

		public String getRecordid() {
			return this.recordid;
		}

		public String sirenetablissementvalide;

		public String getSirenetablissementvalide() {
			return this.sirenetablissementvalide;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public String libellecommuneacheteur;

		public String getLibellecommuneacheteur() {
			return this.libellecommuneacheteur;
		}

		public String lieuexecutioncode;

		public String getLieuexecutioncode() {
			return this.lieuexecutioncode;
		}

		public String adresseetablissement;

		public String getAdresseetablissement() {
			return this.adresseetablissement;
		}

		public String codepostalacheteur;

		public String getCodepostalacheteur() {
			return this.codepostalacheteur;
		}

		public String libelleregionacheteur;

		public String getLibelleregionacheteur() {
			return this.libelleregionacheteur;
		}

		public String departementacheteur;

		public String getDepartementacheteur() {
			return this.departementacheteur;
		}

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		public Integer dureemois;

		public Integer getDureemois() {
			return this.dureemois;
		}

		public String datenotification;

		public String getDatenotification() {
			return this.datenotification;
		}

		public Float montantcalcule;

		public Float getMontantcalcule() {
			return this.montantcalcule;
		}

		public String libelledepartementacheteur;

		public String getLibelledepartementacheteur() {
			return this.libelledepartementacheteur;
		}

		public Integer codecpv_division;

		public Integer getCodecpv_division() {
			return this.codecpv_division;
		}

		public String lieuexecutionnom;

		public String getLieuexecutionnom() {
			return this.lieuexecutionnom;
		}

		public String libellearrondissementacheteur;

		public String getLibellearrondissementacheteur() {
			return this.libellearrondissementacheteur;
		}

		public String populationcommuneetablissement;

		public String getPopulationcommuneetablissement() {
			return this.populationcommuneetablissement;
		}

		public String codecommuneacheteur;

		public String getCodecommuneacheteur() {
			return this.codecommuneacheteur;
		}

		public String superficiecommuneetablissement;

		public String getSuperficiecommuneetablissement() {
			return this.superficiecommuneetablissement;
		}

		public String nicetablissement;

		public String getNicetablissement() {
			return this.nicetablissement;
		}

		public String geoloccommuneetablissement___;

		public String getGeoloccommuneetablissement___() {
			return this.geoloccommuneetablissement___;
		}

		public String objetmarche;

		public String getObjetmarche() {
			return this.objetmarche;
		}

		public String nature;

		public String getNature() {
			return this.nature;
		}

		public String nombretitulairesurmarchepresume;

		public String getNombretitulairesurmarchepresume() {
			return this.nombretitulairesurmarchepresume;
		}

		public String libelleregionetablissement;

		public String getLibelleregionetablissement() {
			return this.libelleregionetablissement;
		}

		public String id;

		public String getId() {
			return this.id;
		}

		public String source;

		public String getSource() {
			return this.source;
		}

		public String dureemoisestimee;

		public String getDureemoisestimee() {
			return this.dureemoisestimee;
		}

		public String nomacheteur;

		public String getNomacheteur() {
			return this.nomacheteur;
		}

		public String referencecpv;

		public String getReferencecpv() {
			return this.referencecpv;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public Float superficiecommuneacheteur;

		public Float getSuperficiecommuneacheteur() {
			return this.superficiecommuneacheteur;
		}

		public Integer codearrondissementacheteur;

		public Integer getCodearrondissementacheteur() {
			return this.codearrondissementacheteur;
		}

		public String distanceacheteuretablissement;

		public String getDistanceacheteuretablissement() {
			return this.distanceacheteuretablissement;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public String montant;

		public String getMontant() {
			return this.montant;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public String datepublicationdonnees;

		public String getDatepublicationdonnees() {
			return this.datepublicationdonnees;
		}

		public String sirenacheteurvalide;

		public String getSirenacheteurvalide() {
			return this.sirenacheteurvalide;
		}

		public String moisnotification;

		public String getMoisnotification() {
			return this.moisnotification;
		}

		public String coderegionetablissement;

		public String getCoderegionetablissement() {
			return this.coderegionetablissement;
		}

		public String formeprix;

		public String getFormeprix() {
			return this.formeprix;
		}

		public String siretetablissementvalide;

		public String getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public Float codedepartementexecution;

		public Float getCodedepartementexecution() {
			return this.codedepartementexecution;
		}

		public Float dist;

		public Float getDist() {
			return this.dist;
		}

		public String coderegionacheteur;

		public String getCoderegionacheteur() {
			return this.coderegionacheteur;
		}

		public String lieuexecutiontypecode;

		public String getLieuexecutiontypecode() {
			return this.lieuexecutiontypecode;
		}

		public String codecommuneetablissement;

		public String getCodecommuneetablissement() {
			return this.codecommuneetablissement;
		}

		public Float populationcommuneacheteur;

		public Float getPopulationcommuneacheteur() {
			return this.populationcommuneacheteur;
		}

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public String codecpv_original;

		public String getCodecpv_original() {
			return this.codecpv_original;
		}

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public String libelledepartementetablissement;

		public String getLibelledepartementetablissement() {
			return this.libelledepartementetablissement;
		}

		public String typeidentifiantetablissement;

		public String getTypeidentifiantetablissement() {
			return this.typeidentifiantetablissement;
		}

		public String categorieetablissement;

		public String getCategorieetablissement() {
			return this.categorieetablissement;
		}

		public Float coderegionexecution;

		public Float getCoderegionexecution() {
			return this.coderegionexecution;
		}

		public String codepostaletablissement;

		public String getCodepostaletablissement() {
			return this.codepostaletablissement;
		}

		public String natureobjetmarche;

		public String getNatureobjetmarche() {
			return this.natureobjetmarche;
		}

		public Float dureemoiscalculee;

		public Float getDureemoiscalculee() {
			return this.dureemoiscalculee;
		}

		public String codetypeetablissement;

		public String getCodetypeetablissement() {
			return this.codetypeetablissement;
		}

		public String geoloccommuneacheteur___;

		public String getGeoloccommuneacheteur___() {
			return this.geoloccommuneacheteur___;
		}

		public String codecpv;

		public String getCodecpv() {
			return this.codecpv;
		}

		public String anneenotification;

		public String getAnneenotification() {
			return this.anneenotification;
		}

		public String libelleregionexecution;

		public String getLibelleregionexecution() {
			return this.libelleregionexecution;
		}

		public String procedure;

		public String getProcedure() {
			return this.procedure;
		}

		public String libellearrondissementetablissement;

		public String getLibellearrondissementetablissement() {
			return this.libellearrondissementetablissement;
		}

		public String codearrondissementetablissement;

		public String getCodearrondissementetablissement() {
			return this.codearrondissementetablissement;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.recordid == null) ? 0 : this.recordid.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row6Struct other = (row6Struct) obj;

			if (this.recordid == null) {
				if (other.recordid != null)
					return false;

			} else if (!this.recordid.equals(other.recordid))

				return false;

			return true;
		}

		public void copyDataTo(row6Struct other) {

			other.recordid = this.recordid;
			other.sirenetablissementvalide = this.sirenetablissementvalide;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.libellecommuneacheteur = this.libellecommuneacheteur;
			other.lieuexecutioncode = this.lieuexecutioncode;
			other.adresseetablissement = this.adresseetablissement;
			other.codepostalacheteur = this.codepostalacheteur;
			other.libelleregionacheteur = this.libelleregionacheteur;
			other.departementacheteur = this.departementacheteur;
			other.idmarche = this.idmarche;
			other.dureemois = this.dureemois;
			other.datenotification = this.datenotification;
			other.montantcalcule = this.montantcalcule;
			other.libelledepartementacheteur = this.libelledepartementacheteur;
			other.codecpv_division = this.codecpv_division;
			other.lieuexecutionnom = this.lieuexecutionnom;
			other.libellearrondissementacheteur = this.libellearrondissementacheteur;
			other.populationcommuneetablissement = this.populationcommuneetablissement;
			other.codecommuneacheteur = this.codecommuneacheteur;
			other.superficiecommuneetablissement = this.superficiecommuneetablissement;
			other.nicetablissement = this.nicetablissement;
			other.geoloccommuneetablissement___ = this.geoloccommuneetablissement___;
			other.objetmarche = this.objetmarche;
			other.nature = this.nature;
			other.nombretitulairesurmarchepresume = this.nombretitulairesurmarchepresume;
			other.libelleregionetablissement = this.libelleregionetablissement;
			other.id = this.id;
			other.source = this.source;
			other.dureemoisestimee = this.dureemoisestimee;
			other.nomacheteur = this.nomacheteur;
			other.referencecpv = this.referencecpv;
			other.type = this.type;
			other.sirenetablissement = this.sirenetablissement;
			other.superficiecommuneacheteur = this.superficiecommuneacheteur;
			other.codearrondissementacheteur = this.codearrondissementacheteur;
			other.distanceacheteuretablissement = this.distanceacheteuretablissement;
			other.communeetablissement = this.communeetablissement;
			other.montant = this.montant;
			other.departementetablissement = this.departementetablissement;
			other.datepublicationdonnees = this.datepublicationdonnees;
			other.sirenacheteurvalide = this.sirenacheteurvalide;
			other.moisnotification = this.moisnotification;
			other.coderegionetablissement = this.coderegionetablissement;
			other.formeprix = this.formeprix;
			other.siretetablissementvalide = this.siretetablissementvalide;
			other.codedepartementexecution = this.codedepartementexecution;
			other.dist = this.dist;
			other.coderegionacheteur = this.coderegionacheteur;
			other.lieuexecutiontypecode = this.lieuexecutiontypecode;
			other.codecommuneetablissement = this.codecommuneetablissement;
			other.populationcommuneacheteur = this.populationcommuneacheteur;
			other.idacheteur = this.idacheteur;
			other.codecpv_original = this.codecpv_original;
			other.siretetablissement = this.siretetablissement;
			other.libelledepartementetablissement = this.libelledepartementetablissement;
			other.typeidentifiantetablissement = this.typeidentifiantetablissement;
			other.categorieetablissement = this.categorieetablissement;
			other.coderegionexecution = this.coderegionexecution;
			other.codepostaletablissement = this.codepostaletablissement;
			other.natureobjetmarche = this.natureobjetmarche;
			other.dureemoiscalculee = this.dureemoiscalculee;
			other.codetypeetablissement = this.codetypeetablissement;
			other.geoloccommuneacheteur___ = this.geoloccommuneacheteur___;
			other.codecpv = this.codecpv;
			other.anneenotification = this.anneenotification;
			other.libelleregionexecution = this.libelleregionexecution;
			other.procedure = this.procedure;
			other.libellearrondissementetablissement = this.libellearrondissementetablissement;
			other.codearrondissementetablissement = this.codearrondissementetablissement;

		}

		public void copyKeysDataTo(row6Struct other) {

			other.recordid = this.recordid;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_public_market_extract.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_public_market_extract.length == 0) {
						commonByteArray_CITYVIZ_public_market_extract = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_public_market_extract = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_public_market_extract, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_public_market_extract, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_public_market_extract.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_public_market_extract.length == 0) {
						commonByteArray_CITYVIZ_public_market_extract = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_public_market_extract = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_public_market_extract, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_public_market_extract, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_public_market_extract) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					this.sirenetablissementvalide = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.libellecommuneacheteur = readString(dis);

					this.lieuexecutioncode = readString(dis);

					this.adresseetablissement = readString(dis);

					this.codepostalacheteur = readString(dis);

					this.libelleregionacheteur = readString(dis);

					this.departementacheteur = readString(dis);

					this.idmarche = readInteger(dis);

					this.dureemois = readInteger(dis);

					this.datenotification = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montantcalcule = null;
					} else {
						this.montantcalcule = dis.readFloat();
					}

					this.libelledepartementacheteur = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.libellearrondissementacheteur = readString(dis);

					this.populationcommuneetablissement = readString(dis);

					this.codecommuneacheteur = readString(dis);

					this.superficiecommuneetablissement = readString(dis);

					this.nicetablissement = readString(dis);

					this.geoloccommuneetablissement___ = readString(dis);

					this.objetmarche = readString(dis);

					this.nature = readString(dis);

					this.nombretitulairesurmarchepresume = readString(dis);

					this.libelleregionetablissement = readString(dis);

					this.id = readString(dis);

					this.source = readString(dis);

					this.dureemoisestimee = readString(dis);

					this.nomacheteur = readString(dis);

					this.referencecpv = readString(dis);

					this.type = readString(dis);

					this.sirenetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.superficiecommuneacheteur = null;
					} else {
						this.superficiecommuneacheteur = dis.readFloat();
					}

					this.codearrondissementacheteur = readInteger(dis);

					this.distanceacheteuretablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.montant = readString(dis);

					this.departementetablissement = readString(dis);

					this.datepublicationdonnees = readString(dis);

					this.sirenacheteurvalide = readString(dis);

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.formeprix = readString(dis);

					this.siretetablissementvalide = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.dist = null;
					} else {
						this.dist = dis.readFloat();
					}

					this.coderegionacheteur = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.codecommuneetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneacheteur = null;
					} else {
						this.populationcommuneacheteur = dis.readFloat();
					}

					this.idacheteur = readString(dis);

					this.codecpv_original = readString(dis);

					this.siretetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.typeidentifiantetablissement = readString(dis);

					this.categorieetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.codepostaletablissement = readString(dis);

					this.natureobjetmarche = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.dureemoiscalculee = null;
					} else {
						this.dureemoiscalculee = dis.readFloat();
					}

					this.codetypeetablissement = readString(dis);

					this.geoloccommuneacheteur___ = readString(dis);

					this.codecpv = readString(dis);

					this.anneenotification = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.procedure = readString(dis);

					this.libellearrondissementetablissement = readString(dis);

					this.codearrondissementetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_public_market_extract) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					this.sirenetablissementvalide = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.libellecommuneacheteur = readString(dis);

					this.lieuexecutioncode = readString(dis);

					this.adresseetablissement = readString(dis);

					this.codepostalacheteur = readString(dis);

					this.libelleregionacheteur = readString(dis);

					this.departementacheteur = readString(dis);

					this.idmarche = readInteger(dis);

					this.dureemois = readInteger(dis);

					this.datenotification = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montantcalcule = null;
					} else {
						this.montantcalcule = dis.readFloat();
					}

					this.libelledepartementacheteur = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.libellearrondissementacheteur = readString(dis);

					this.populationcommuneetablissement = readString(dis);

					this.codecommuneacheteur = readString(dis);

					this.superficiecommuneetablissement = readString(dis);

					this.nicetablissement = readString(dis);

					this.geoloccommuneetablissement___ = readString(dis);

					this.objetmarche = readString(dis);

					this.nature = readString(dis);

					this.nombretitulairesurmarchepresume = readString(dis);

					this.libelleregionetablissement = readString(dis);

					this.id = readString(dis);

					this.source = readString(dis);

					this.dureemoisestimee = readString(dis);

					this.nomacheteur = readString(dis);

					this.referencecpv = readString(dis);

					this.type = readString(dis);

					this.sirenetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.superficiecommuneacheteur = null;
					} else {
						this.superficiecommuneacheteur = dis.readFloat();
					}

					this.codearrondissementacheteur = readInteger(dis);

					this.distanceacheteuretablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.montant = readString(dis);

					this.departementetablissement = readString(dis);

					this.datepublicationdonnees = readString(dis);

					this.sirenacheteurvalide = readString(dis);

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.formeprix = readString(dis);

					this.siretetablissementvalide = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.dist = null;
					} else {
						this.dist = dis.readFloat();
					}

					this.coderegionacheteur = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.codecommuneetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneacheteur = null;
					} else {
						this.populationcommuneacheteur = dis.readFloat();
					}

					this.idacheteur = readString(dis);

					this.codecpv_original = readString(dis);

					this.siretetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.typeidentifiantetablissement = readString(dis);

					this.categorieetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.codepostaletablissement = readString(dis);

					this.natureobjetmarche = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.dureemoiscalculee = null;
					} else {
						this.dureemoiscalculee = dis.readFloat();
					}

					this.codetypeetablissement = readString(dis);

					this.geoloccommuneacheteur___ = readString(dis);

					this.codecpv = readString(dis);

					this.anneenotification = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.procedure = readString(dis);

					this.libellearrondissementetablissement = readString(dis);

					this.codearrondissementetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.recordid, dos);

				// String

				writeString(this.sirenetablissementvalide, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.libellecommuneacheteur, dos);

				// String

				writeString(this.lieuexecutioncode, dos);

				// String

				writeString(this.adresseetablissement, dos);

				// String

				writeString(this.codepostalacheteur, dos);

				// String

				writeString(this.libelleregionacheteur, dos);

				// String

				writeString(this.departementacheteur, dos);

				// Integer

				writeInteger(this.idmarche, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.datenotification, dos);

				// Float

				if (this.montantcalcule == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalcule);
				}

				// String

				writeString(this.libelledepartementacheteur, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.libellearrondissementacheteur, dos);

				// String

				writeString(this.populationcommuneetablissement, dos);

				// String

				writeString(this.codecommuneacheteur, dos);

				// String

				writeString(this.superficiecommuneetablissement, dos);

				// String

				writeString(this.nicetablissement, dos);

				// String

				writeString(this.geoloccommuneetablissement___, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.nombretitulairesurmarchepresume, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

				// String

				writeString(this.id, dos);

				// String

				writeString(this.source, dos);

				// String

				writeString(this.dureemoisestimee, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.referencecpv, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.sirenetablissement, dos);

				// Float

				if (this.superficiecommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneacheteur);
				}

				// Integer

				writeInteger(this.codearrondissementacheteur, dos);

				// String

				writeString(this.distanceacheteuretablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.montant, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.datepublicationdonnees, dos);

				// String

				writeString(this.sirenacheteurvalide, dos);

				// String

				writeString(this.moisnotification, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// Float

				if (this.dist == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dist);
				}

				// String

				writeString(this.coderegionacheteur, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// Float

				if (this.populationcommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneacheteur);
				}

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.codecpv_original, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.typeidentifiantetablissement, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.codepostaletablissement, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// Float

				if (this.dureemoiscalculee == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dureemoiscalculee);
				}

				// String

				writeString(this.codetypeetablissement, dos);

				// String

				writeString(this.geoloccommuneacheteur___, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.anneenotification, dos);

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.libellearrondissementetablissement, dos);

				// String

				writeString(this.codearrondissementetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.recordid, dos);

				// String

				writeString(this.sirenetablissementvalide, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.libellecommuneacheteur, dos);

				// String

				writeString(this.lieuexecutioncode, dos);

				// String

				writeString(this.adresseetablissement, dos);

				// String

				writeString(this.codepostalacheteur, dos);

				// String

				writeString(this.libelleregionacheteur, dos);

				// String

				writeString(this.departementacheteur, dos);

				// Integer

				writeInteger(this.idmarche, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.datenotification, dos);

				// Float

				if (this.montantcalcule == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalcule);
				}

				// String

				writeString(this.libelledepartementacheteur, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.libellearrondissementacheteur, dos);

				// String

				writeString(this.populationcommuneetablissement, dos);

				// String

				writeString(this.codecommuneacheteur, dos);

				// String

				writeString(this.superficiecommuneetablissement, dos);

				// String

				writeString(this.nicetablissement, dos);

				// String

				writeString(this.geoloccommuneetablissement___, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.nombretitulairesurmarchepresume, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

				// String

				writeString(this.id, dos);

				// String

				writeString(this.source, dos);

				// String

				writeString(this.dureemoisestimee, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.referencecpv, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.sirenetablissement, dos);

				// Float

				if (this.superficiecommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneacheteur);
				}

				// Integer

				writeInteger(this.codearrondissementacheteur, dos);

				// String

				writeString(this.distanceacheteuretablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.montant, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.datepublicationdonnees, dos);

				// String

				writeString(this.sirenacheteurvalide, dos);

				// String

				writeString(this.moisnotification, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// Float

				if (this.dist == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dist);
				}

				// String

				writeString(this.coderegionacheteur, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// Float

				if (this.populationcommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneacheteur);
				}

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.codecpv_original, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.typeidentifiantetablissement, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.codepostaletablissement, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// Float

				if (this.dureemoiscalculee == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dureemoiscalculee);
				}

				// String

				writeString(this.codetypeetablissement, dos);

				// String

				writeString(this.geoloccommuneacheteur___, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.anneenotification, dos);

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.libellearrondissementetablissement, dos);

				// String

				writeString(this.codearrondissementetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("recordid=" + recordid);
			sb.append(",sirenetablissementvalide=" + sirenetablissementvalide);
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",libellecommuneacheteur=" + libellecommuneacheteur);
			sb.append(",lieuexecutioncode=" + lieuexecutioncode);
			sb.append(",adresseetablissement=" + adresseetablissement);
			sb.append(",codepostalacheteur=" + codepostalacheteur);
			sb.append(",libelleregionacheteur=" + libelleregionacheteur);
			sb.append(",departementacheteur=" + departementacheteur);
			sb.append(",idmarche=" + String.valueOf(idmarche));
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",datenotification=" + datenotification);
			sb.append(",montantcalcule=" + String.valueOf(montantcalcule));
			sb.append(",libelledepartementacheteur=" + libelledepartementacheteur);
			sb.append(",codecpv_division=" + String.valueOf(codecpv_division));
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",libellearrondissementacheteur=" + libellearrondissementacheteur);
			sb.append(",populationcommuneetablissement=" + populationcommuneetablissement);
			sb.append(",codecommuneacheteur=" + codecommuneacheteur);
			sb.append(",superficiecommuneetablissement=" + superficiecommuneetablissement);
			sb.append(",nicetablissement=" + nicetablissement);
			sb.append(",geoloccommuneetablissement___=" + geoloccommuneetablissement___);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",nature=" + nature);
			sb.append(",nombretitulairesurmarchepresume=" + nombretitulairesurmarchepresume);
			sb.append(",libelleregionetablissement=" + libelleregionetablissement);
			sb.append(",id=" + id);
			sb.append(",source=" + source);
			sb.append(",dureemoisestimee=" + dureemoisestimee);
			sb.append(",nomacheteur=" + nomacheteur);
			sb.append(",referencecpv=" + referencecpv);
			sb.append(",type=" + type);
			sb.append(",sirenetablissement=" + sirenetablissement);
			sb.append(",superficiecommuneacheteur=" + String.valueOf(superficiecommuneacheteur));
			sb.append(",codearrondissementacheteur=" + String.valueOf(codearrondissementacheteur));
			sb.append(",distanceacheteuretablissement=" + distanceacheteuretablissement);
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",montant=" + montant);
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",datepublicationdonnees=" + datepublicationdonnees);
			sb.append(",sirenacheteurvalide=" + sirenacheteurvalide);
			sb.append(",moisnotification=" + moisnotification);
			sb.append(",coderegionetablissement=" + coderegionetablissement);
			sb.append(",formeprix=" + formeprix);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",codedepartementexecution=" + String.valueOf(codedepartementexecution));
			sb.append(",dist=" + String.valueOf(dist));
			sb.append(",coderegionacheteur=" + coderegionacheteur);
			sb.append(",lieuexecutiontypecode=" + lieuexecutiontypecode);
			sb.append(",codecommuneetablissement=" + codecommuneetablissement);
			sb.append(",populationcommuneacheteur=" + String.valueOf(populationcommuneacheteur));
			sb.append(",idacheteur=" + idacheteur);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",siretetablissement=" + siretetablissement);
			sb.append(",libelledepartementetablissement=" + libelledepartementetablissement);
			sb.append(",typeidentifiantetablissement=" + typeidentifiantetablissement);
			sb.append(",categorieetablissement=" + categorieetablissement);
			sb.append(",coderegionexecution=" + String.valueOf(coderegionexecution));
			sb.append(",codepostaletablissement=" + codepostaletablissement);
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",dureemoiscalculee=" + String.valueOf(dureemoiscalculee));
			sb.append(",codetypeetablissement=" + codetypeetablissement);
			sb.append(",geoloccommuneacheteur___=" + geoloccommuneacheteur___);
			sb.append(",codecpv=" + codecpv);
			sb.append(",anneenotification=" + anneenotification);
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append(",procedure=" + procedure);
			sb.append(",libellearrondissementetablissement=" + libellearrondissementetablissement);
			sb.append(",codearrondissementetablissement=" + codearrondissementetablissement);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.recordid, other.recordid);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_public_market_extract = new byte[0];
		static byte[] commonByteArray_CITYVIZ_public_market_extract = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String recordid;

		public String getRecordid() {
			return this.recordid;
		}

		public String sirenetablissementvalide;

		public String getSirenetablissementvalide() {
			return this.sirenetablissementvalide;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public String libellecommuneacheteur;

		public String getLibellecommuneacheteur() {
			return this.libellecommuneacheteur;
		}

		public String lieuexecutioncode;

		public String getLieuexecutioncode() {
			return this.lieuexecutioncode;
		}

		public String adresseetablissement;

		public String getAdresseetablissement() {
			return this.adresseetablissement;
		}

		public String codepostalacheteur;

		public String getCodepostalacheteur() {
			return this.codepostalacheteur;
		}

		public String libelleregionacheteur;

		public String getLibelleregionacheteur() {
			return this.libelleregionacheteur;
		}

		public String departementacheteur;

		public String getDepartementacheteur() {
			return this.departementacheteur;
		}

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		public Integer dureemois;

		public Integer getDureemois() {
			return this.dureemois;
		}

		public String datenotification;

		public String getDatenotification() {
			return this.datenotification;
		}

		public Float montantcalcule;

		public Float getMontantcalcule() {
			return this.montantcalcule;
		}

		public String libelledepartementacheteur;

		public String getLibelledepartementacheteur() {
			return this.libelledepartementacheteur;
		}

		public Integer codecpv_division;

		public Integer getCodecpv_division() {
			return this.codecpv_division;
		}

		public String lieuexecutionnom;

		public String getLieuexecutionnom() {
			return this.lieuexecutionnom;
		}

		public String libellearrondissementacheteur;

		public String getLibellearrondissementacheteur() {
			return this.libellearrondissementacheteur;
		}

		public String populationcommuneetablissement;

		public String getPopulationcommuneetablissement() {
			return this.populationcommuneetablissement;
		}

		public String codecommuneacheteur;

		public String getCodecommuneacheteur() {
			return this.codecommuneacheteur;
		}

		public String superficiecommuneetablissement;

		public String getSuperficiecommuneetablissement() {
			return this.superficiecommuneetablissement;
		}

		public String nicetablissement;

		public String getNicetablissement() {
			return this.nicetablissement;
		}

		public String geoloccommuneetablissement___;

		public String getGeoloccommuneetablissement___() {
			return this.geoloccommuneetablissement___;
		}

		public String objetmarche;

		public String getObjetmarche() {
			return this.objetmarche;
		}

		public String nature;

		public String getNature() {
			return this.nature;
		}

		public String nombretitulairesurmarchepresume;

		public String getNombretitulairesurmarchepresume() {
			return this.nombretitulairesurmarchepresume;
		}

		public String libelleregionetablissement;

		public String getLibelleregionetablissement() {
			return this.libelleregionetablissement;
		}

		public String id;

		public String getId() {
			return this.id;
		}

		public String source;

		public String getSource() {
			return this.source;
		}

		public String dureemoisestimee;

		public String getDureemoisestimee() {
			return this.dureemoisestimee;
		}

		public String nomacheteur;

		public String getNomacheteur() {
			return this.nomacheteur;
		}

		public String referencecpv;

		public String getReferencecpv() {
			return this.referencecpv;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public Float superficiecommuneacheteur;

		public Float getSuperficiecommuneacheteur() {
			return this.superficiecommuneacheteur;
		}

		public Integer codearrondissementacheteur;

		public Integer getCodearrondissementacheteur() {
			return this.codearrondissementacheteur;
		}

		public String distanceacheteuretablissement;

		public String getDistanceacheteuretablissement() {
			return this.distanceacheteuretablissement;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public String montant;

		public String getMontant() {
			return this.montant;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public String datepublicationdonnees;

		public String getDatepublicationdonnees() {
			return this.datepublicationdonnees;
		}

		public String sirenacheteurvalide;

		public String getSirenacheteurvalide() {
			return this.sirenacheteurvalide;
		}

		public String moisnotification;

		public String getMoisnotification() {
			return this.moisnotification;
		}

		public String coderegionetablissement;

		public String getCoderegionetablissement() {
			return this.coderegionetablissement;
		}

		public String formeprix;

		public String getFormeprix() {
			return this.formeprix;
		}

		public String siretetablissementvalide;

		public String getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public Float codedepartementexecution;

		public Float getCodedepartementexecution() {
			return this.codedepartementexecution;
		}

		public Float dist;

		public Float getDist() {
			return this.dist;
		}

		public String coderegionacheteur;

		public String getCoderegionacheteur() {
			return this.coderegionacheteur;
		}

		public String lieuexecutiontypecode;

		public String getLieuexecutiontypecode() {
			return this.lieuexecutiontypecode;
		}

		public String codecommuneetablissement;

		public String getCodecommuneetablissement() {
			return this.codecommuneetablissement;
		}

		public Float populationcommuneacheteur;

		public Float getPopulationcommuneacheteur() {
			return this.populationcommuneacheteur;
		}

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public String codecpv_original;

		public String getCodecpv_original() {
			return this.codecpv_original;
		}

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public String libelledepartementetablissement;

		public String getLibelledepartementetablissement() {
			return this.libelledepartementetablissement;
		}

		public String typeidentifiantetablissement;

		public String getTypeidentifiantetablissement() {
			return this.typeidentifiantetablissement;
		}

		public String categorieetablissement;

		public String getCategorieetablissement() {
			return this.categorieetablissement;
		}

		public Float coderegionexecution;

		public Float getCoderegionexecution() {
			return this.coderegionexecution;
		}

		public String codepostaletablissement;

		public String getCodepostaletablissement() {
			return this.codepostaletablissement;
		}

		public String natureobjetmarche;

		public String getNatureobjetmarche() {
			return this.natureobjetmarche;
		}

		public Float dureemoiscalculee;

		public Float getDureemoiscalculee() {
			return this.dureemoiscalculee;
		}

		public String codetypeetablissement;

		public String getCodetypeetablissement() {
			return this.codetypeetablissement;
		}

		public String geoloccommuneacheteur___;

		public String getGeoloccommuneacheteur___() {
			return this.geoloccommuneacheteur___;
		}

		public String codecpv;

		public String getCodecpv() {
			return this.codecpv;
		}

		public String anneenotification;

		public String getAnneenotification() {
			return this.anneenotification;
		}

		public String libelleregionexecution;

		public String getLibelleregionexecution() {
			return this.libelleregionexecution;
		}

		public String procedure;

		public String getProcedure() {
			return this.procedure;
		}

		public String libellearrondissementetablissement;

		public String getLibellearrondissementetablissement() {
			return this.libellearrondissementetablissement;
		}

		public String codearrondissementetablissement;

		public String getCodearrondissementetablissement() {
			return this.codearrondissementetablissement;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.recordid == null) ? 0 : this.recordid.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row1Struct other = (row1Struct) obj;

			if (this.recordid == null) {
				if (other.recordid != null)
					return false;

			} else if (!this.recordid.equals(other.recordid))

				return false;

			return true;
		}

		public void copyDataTo(row1Struct other) {

			other.recordid = this.recordid;
			other.sirenetablissementvalide = this.sirenetablissementvalide;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.libellecommuneacheteur = this.libellecommuneacheteur;
			other.lieuexecutioncode = this.lieuexecutioncode;
			other.adresseetablissement = this.adresseetablissement;
			other.codepostalacheteur = this.codepostalacheteur;
			other.libelleregionacheteur = this.libelleregionacheteur;
			other.departementacheteur = this.departementacheteur;
			other.idmarche = this.idmarche;
			other.dureemois = this.dureemois;
			other.datenotification = this.datenotification;
			other.montantcalcule = this.montantcalcule;
			other.libelledepartementacheteur = this.libelledepartementacheteur;
			other.codecpv_division = this.codecpv_division;
			other.lieuexecutionnom = this.lieuexecutionnom;
			other.libellearrondissementacheteur = this.libellearrondissementacheteur;
			other.populationcommuneetablissement = this.populationcommuneetablissement;
			other.codecommuneacheteur = this.codecommuneacheteur;
			other.superficiecommuneetablissement = this.superficiecommuneetablissement;
			other.nicetablissement = this.nicetablissement;
			other.geoloccommuneetablissement___ = this.geoloccommuneetablissement___;
			other.objetmarche = this.objetmarche;
			other.nature = this.nature;
			other.nombretitulairesurmarchepresume = this.nombretitulairesurmarchepresume;
			other.libelleregionetablissement = this.libelleregionetablissement;
			other.id = this.id;
			other.source = this.source;
			other.dureemoisestimee = this.dureemoisestimee;
			other.nomacheteur = this.nomacheteur;
			other.referencecpv = this.referencecpv;
			other.type = this.type;
			other.sirenetablissement = this.sirenetablissement;
			other.superficiecommuneacheteur = this.superficiecommuneacheteur;
			other.codearrondissementacheteur = this.codearrondissementacheteur;
			other.distanceacheteuretablissement = this.distanceacheteuretablissement;
			other.communeetablissement = this.communeetablissement;
			other.montant = this.montant;
			other.departementetablissement = this.departementetablissement;
			other.datepublicationdonnees = this.datepublicationdonnees;
			other.sirenacheteurvalide = this.sirenacheteurvalide;
			other.moisnotification = this.moisnotification;
			other.coderegionetablissement = this.coderegionetablissement;
			other.formeprix = this.formeprix;
			other.siretetablissementvalide = this.siretetablissementvalide;
			other.codedepartementexecution = this.codedepartementexecution;
			other.dist = this.dist;
			other.coderegionacheteur = this.coderegionacheteur;
			other.lieuexecutiontypecode = this.lieuexecutiontypecode;
			other.codecommuneetablissement = this.codecommuneetablissement;
			other.populationcommuneacheteur = this.populationcommuneacheteur;
			other.idacheteur = this.idacheteur;
			other.codecpv_original = this.codecpv_original;
			other.siretetablissement = this.siretetablissement;
			other.libelledepartementetablissement = this.libelledepartementetablissement;
			other.typeidentifiantetablissement = this.typeidentifiantetablissement;
			other.categorieetablissement = this.categorieetablissement;
			other.coderegionexecution = this.coderegionexecution;
			other.codepostaletablissement = this.codepostaletablissement;
			other.natureobjetmarche = this.natureobjetmarche;
			other.dureemoiscalculee = this.dureemoiscalculee;
			other.codetypeetablissement = this.codetypeetablissement;
			other.geoloccommuneacheteur___ = this.geoloccommuneacheteur___;
			other.codecpv = this.codecpv;
			other.anneenotification = this.anneenotification;
			other.libelleregionexecution = this.libelleregionexecution;
			other.procedure = this.procedure;
			other.libellearrondissementetablissement = this.libellearrondissementetablissement;
			other.codearrondissementetablissement = this.codearrondissementetablissement;

		}

		public void copyKeysDataTo(row1Struct other) {

			other.recordid = this.recordid;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_public_market_extract.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_public_market_extract.length == 0) {
						commonByteArray_CITYVIZ_public_market_extract = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_public_market_extract = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_public_market_extract, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_public_market_extract, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_public_market_extract.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_public_market_extract.length == 0) {
						commonByteArray_CITYVIZ_public_market_extract = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_public_market_extract = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_public_market_extract, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_public_market_extract, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_public_market_extract) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					this.sirenetablissementvalide = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.libellecommuneacheteur = readString(dis);

					this.lieuexecutioncode = readString(dis);

					this.adresseetablissement = readString(dis);

					this.codepostalacheteur = readString(dis);

					this.libelleregionacheteur = readString(dis);

					this.departementacheteur = readString(dis);

					this.idmarche = readInteger(dis);

					this.dureemois = readInteger(dis);

					this.datenotification = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montantcalcule = null;
					} else {
						this.montantcalcule = dis.readFloat();
					}

					this.libelledepartementacheteur = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.libellearrondissementacheteur = readString(dis);

					this.populationcommuneetablissement = readString(dis);

					this.codecommuneacheteur = readString(dis);

					this.superficiecommuneetablissement = readString(dis);

					this.nicetablissement = readString(dis);

					this.geoloccommuneetablissement___ = readString(dis);

					this.objetmarche = readString(dis);

					this.nature = readString(dis);

					this.nombretitulairesurmarchepresume = readString(dis);

					this.libelleregionetablissement = readString(dis);

					this.id = readString(dis);

					this.source = readString(dis);

					this.dureemoisestimee = readString(dis);

					this.nomacheteur = readString(dis);

					this.referencecpv = readString(dis);

					this.type = readString(dis);

					this.sirenetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.superficiecommuneacheteur = null;
					} else {
						this.superficiecommuneacheteur = dis.readFloat();
					}

					this.codearrondissementacheteur = readInteger(dis);

					this.distanceacheteuretablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.montant = readString(dis);

					this.departementetablissement = readString(dis);

					this.datepublicationdonnees = readString(dis);

					this.sirenacheteurvalide = readString(dis);

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.formeprix = readString(dis);

					this.siretetablissementvalide = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.dist = null;
					} else {
						this.dist = dis.readFloat();
					}

					this.coderegionacheteur = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.codecommuneetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneacheteur = null;
					} else {
						this.populationcommuneacheteur = dis.readFloat();
					}

					this.idacheteur = readString(dis);

					this.codecpv_original = readString(dis);

					this.siretetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.typeidentifiantetablissement = readString(dis);

					this.categorieetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.codepostaletablissement = readString(dis);

					this.natureobjetmarche = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.dureemoiscalculee = null;
					} else {
						this.dureemoiscalculee = dis.readFloat();
					}

					this.codetypeetablissement = readString(dis);

					this.geoloccommuneacheteur___ = readString(dis);

					this.codecpv = readString(dis);

					this.anneenotification = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.procedure = readString(dis);

					this.libellearrondissementetablissement = readString(dis);

					this.codearrondissementetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_public_market_extract) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					this.sirenetablissementvalide = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.libellecommuneacheteur = readString(dis);

					this.lieuexecutioncode = readString(dis);

					this.adresseetablissement = readString(dis);

					this.codepostalacheteur = readString(dis);

					this.libelleregionacheteur = readString(dis);

					this.departementacheteur = readString(dis);

					this.idmarche = readInteger(dis);

					this.dureemois = readInteger(dis);

					this.datenotification = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montantcalcule = null;
					} else {
						this.montantcalcule = dis.readFloat();
					}

					this.libelledepartementacheteur = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.libellearrondissementacheteur = readString(dis);

					this.populationcommuneetablissement = readString(dis);

					this.codecommuneacheteur = readString(dis);

					this.superficiecommuneetablissement = readString(dis);

					this.nicetablissement = readString(dis);

					this.geoloccommuneetablissement___ = readString(dis);

					this.objetmarche = readString(dis);

					this.nature = readString(dis);

					this.nombretitulairesurmarchepresume = readString(dis);

					this.libelleregionetablissement = readString(dis);

					this.id = readString(dis);

					this.source = readString(dis);

					this.dureemoisestimee = readString(dis);

					this.nomacheteur = readString(dis);

					this.referencecpv = readString(dis);

					this.type = readString(dis);

					this.sirenetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.superficiecommuneacheteur = null;
					} else {
						this.superficiecommuneacheteur = dis.readFloat();
					}

					this.codearrondissementacheteur = readInteger(dis);

					this.distanceacheteuretablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.montant = readString(dis);

					this.departementetablissement = readString(dis);

					this.datepublicationdonnees = readString(dis);

					this.sirenacheteurvalide = readString(dis);

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.formeprix = readString(dis);

					this.siretetablissementvalide = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.dist = null;
					} else {
						this.dist = dis.readFloat();
					}

					this.coderegionacheteur = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.codecommuneetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneacheteur = null;
					} else {
						this.populationcommuneacheteur = dis.readFloat();
					}

					this.idacheteur = readString(dis);

					this.codecpv_original = readString(dis);

					this.siretetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.typeidentifiantetablissement = readString(dis);

					this.categorieetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.codepostaletablissement = readString(dis);

					this.natureobjetmarche = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.dureemoiscalculee = null;
					} else {
						this.dureemoiscalculee = dis.readFloat();
					}

					this.codetypeetablissement = readString(dis);

					this.geoloccommuneacheteur___ = readString(dis);

					this.codecpv = readString(dis);

					this.anneenotification = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.procedure = readString(dis);

					this.libellearrondissementetablissement = readString(dis);

					this.codearrondissementetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.recordid, dos);

				// String

				writeString(this.sirenetablissementvalide, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.libellecommuneacheteur, dos);

				// String

				writeString(this.lieuexecutioncode, dos);

				// String

				writeString(this.adresseetablissement, dos);

				// String

				writeString(this.codepostalacheteur, dos);

				// String

				writeString(this.libelleregionacheteur, dos);

				// String

				writeString(this.departementacheteur, dos);

				// Integer

				writeInteger(this.idmarche, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.datenotification, dos);

				// Float

				if (this.montantcalcule == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalcule);
				}

				// String

				writeString(this.libelledepartementacheteur, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.libellearrondissementacheteur, dos);

				// String

				writeString(this.populationcommuneetablissement, dos);

				// String

				writeString(this.codecommuneacheteur, dos);

				// String

				writeString(this.superficiecommuneetablissement, dos);

				// String

				writeString(this.nicetablissement, dos);

				// String

				writeString(this.geoloccommuneetablissement___, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.nombretitulairesurmarchepresume, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

				// String

				writeString(this.id, dos);

				// String

				writeString(this.source, dos);

				// String

				writeString(this.dureemoisestimee, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.referencecpv, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.sirenetablissement, dos);

				// Float

				if (this.superficiecommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneacheteur);
				}

				// Integer

				writeInteger(this.codearrondissementacheteur, dos);

				// String

				writeString(this.distanceacheteuretablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.montant, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.datepublicationdonnees, dos);

				// String

				writeString(this.sirenacheteurvalide, dos);

				// String

				writeString(this.moisnotification, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// Float

				if (this.dist == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dist);
				}

				// String

				writeString(this.coderegionacheteur, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// Float

				if (this.populationcommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneacheteur);
				}

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.codecpv_original, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.typeidentifiantetablissement, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.codepostaletablissement, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// Float

				if (this.dureemoiscalculee == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dureemoiscalculee);
				}

				// String

				writeString(this.codetypeetablissement, dos);

				// String

				writeString(this.geoloccommuneacheteur___, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.anneenotification, dos);

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.libellearrondissementetablissement, dos);

				// String

				writeString(this.codearrondissementetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.recordid, dos);

				// String

				writeString(this.sirenetablissementvalide, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.libellecommuneacheteur, dos);

				// String

				writeString(this.lieuexecutioncode, dos);

				// String

				writeString(this.adresseetablissement, dos);

				// String

				writeString(this.codepostalacheteur, dos);

				// String

				writeString(this.libelleregionacheteur, dos);

				// String

				writeString(this.departementacheteur, dos);

				// Integer

				writeInteger(this.idmarche, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.datenotification, dos);

				// Float

				if (this.montantcalcule == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalcule);
				}

				// String

				writeString(this.libelledepartementacheteur, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.libellearrondissementacheteur, dos);

				// String

				writeString(this.populationcommuneetablissement, dos);

				// String

				writeString(this.codecommuneacheteur, dos);

				// String

				writeString(this.superficiecommuneetablissement, dos);

				// String

				writeString(this.nicetablissement, dos);

				// String

				writeString(this.geoloccommuneetablissement___, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.nombretitulairesurmarchepresume, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

				// String

				writeString(this.id, dos);

				// String

				writeString(this.source, dos);

				// String

				writeString(this.dureemoisestimee, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.referencecpv, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.sirenetablissement, dos);

				// Float

				if (this.superficiecommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneacheteur);
				}

				// Integer

				writeInteger(this.codearrondissementacheteur, dos);

				// String

				writeString(this.distanceacheteuretablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.montant, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.datepublicationdonnees, dos);

				// String

				writeString(this.sirenacheteurvalide, dos);

				// String

				writeString(this.moisnotification, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// Float

				if (this.dist == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dist);
				}

				// String

				writeString(this.coderegionacheteur, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// Float

				if (this.populationcommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneacheteur);
				}

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.codecpv_original, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.typeidentifiantetablissement, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.codepostaletablissement, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// Float

				if (this.dureemoiscalculee == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dureemoiscalculee);
				}

				// String

				writeString(this.codetypeetablissement, dos);

				// String

				writeString(this.geoloccommuneacheteur___, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.anneenotification, dos);

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.libellearrondissementetablissement, dos);

				// String

				writeString(this.codearrondissementetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("recordid=" + recordid);
			sb.append(",sirenetablissementvalide=" + sirenetablissementvalide);
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",libellecommuneacheteur=" + libellecommuneacheteur);
			sb.append(",lieuexecutioncode=" + lieuexecutioncode);
			sb.append(",adresseetablissement=" + adresseetablissement);
			sb.append(",codepostalacheteur=" + codepostalacheteur);
			sb.append(",libelleregionacheteur=" + libelleregionacheteur);
			sb.append(",departementacheteur=" + departementacheteur);
			sb.append(",idmarche=" + String.valueOf(idmarche));
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",datenotification=" + datenotification);
			sb.append(",montantcalcule=" + String.valueOf(montantcalcule));
			sb.append(",libelledepartementacheteur=" + libelledepartementacheteur);
			sb.append(",codecpv_division=" + String.valueOf(codecpv_division));
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",libellearrondissementacheteur=" + libellearrondissementacheteur);
			sb.append(",populationcommuneetablissement=" + populationcommuneetablissement);
			sb.append(",codecommuneacheteur=" + codecommuneacheteur);
			sb.append(",superficiecommuneetablissement=" + superficiecommuneetablissement);
			sb.append(",nicetablissement=" + nicetablissement);
			sb.append(",geoloccommuneetablissement___=" + geoloccommuneetablissement___);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",nature=" + nature);
			sb.append(",nombretitulairesurmarchepresume=" + nombretitulairesurmarchepresume);
			sb.append(",libelleregionetablissement=" + libelleregionetablissement);
			sb.append(",id=" + id);
			sb.append(",source=" + source);
			sb.append(",dureemoisestimee=" + dureemoisestimee);
			sb.append(",nomacheteur=" + nomacheteur);
			sb.append(",referencecpv=" + referencecpv);
			sb.append(",type=" + type);
			sb.append(",sirenetablissement=" + sirenetablissement);
			sb.append(",superficiecommuneacheteur=" + String.valueOf(superficiecommuneacheteur));
			sb.append(",codearrondissementacheteur=" + String.valueOf(codearrondissementacheteur));
			sb.append(",distanceacheteuretablissement=" + distanceacheteuretablissement);
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",montant=" + montant);
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",datepublicationdonnees=" + datepublicationdonnees);
			sb.append(",sirenacheteurvalide=" + sirenacheteurvalide);
			sb.append(",moisnotification=" + moisnotification);
			sb.append(",coderegionetablissement=" + coderegionetablissement);
			sb.append(",formeprix=" + formeprix);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",codedepartementexecution=" + String.valueOf(codedepartementexecution));
			sb.append(",dist=" + String.valueOf(dist));
			sb.append(",coderegionacheteur=" + coderegionacheteur);
			sb.append(",lieuexecutiontypecode=" + lieuexecutiontypecode);
			sb.append(",codecommuneetablissement=" + codecommuneetablissement);
			sb.append(",populationcommuneacheteur=" + String.valueOf(populationcommuneacheteur));
			sb.append(",idacheteur=" + idacheteur);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",siretetablissement=" + siretetablissement);
			sb.append(",libelledepartementetablissement=" + libelledepartementetablissement);
			sb.append(",typeidentifiantetablissement=" + typeidentifiantetablissement);
			sb.append(",categorieetablissement=" + categorieetablissement);
			sb.append(",coderegionexecution=" + String.valueOf(coderegionexecution));
			sb.append(",codepostaletablissement=" + codepostaletablissement);
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",dureemoiscalculee=" + String.valueOf(dureemoiscalculee));
			sb.append(",codetypeetablissement=" + codetypeetablissement);
			sb.append(",geoloccommuneacheteur___=" + geoloccommuneacheteur___);
			sb.append(",codecpv=" + codecpv);
			sb.append(",anneenotification=" + anneenotification);
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append(",procedure=" + procedure);
			sb.append(",libellearrondissementetablissement=" + libellearrondissementetablissement);
			sb.append(",codearrondissementetablissement=" + codearrondissementetablissement);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.recordid, other.recordid);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputJSON_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputJSON_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				row1Struct row6 = row1;

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
				}

				int tos_count_tDBOutput_2 = 0;

				String dbschema_tDBOutput_2 = null;
				dbschema_tDBOutput_2 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_2 = null;
				if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
					tableName_tDBOutput_2 = ("marche");
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "\".\"" + ("marche");
				}

				int updateKeyCount_tDBOutput_2 = 1;
				if (updateKeyCount_tDBOutput_2 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_2 == 69 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_2 = 0;
				int nb_line_update_tDBOutput_2 = 0;
				int nb_line_inserted_tDBOutput_2 = 0;
				int nb_line_deleted_tDBOutput_2 = 0;
				int nb_line_rejected_tDBOutput_2 = 0;

				int deletedCount_tDBOutput_2 = 0;
				int updatedCount_tDBOutput_2 = 0;
				int insertedCount_tDBOutput_2 = 0;
				int rowsToCommitCount_tDBOutput_2 = 0;
				int rejectedCount_tDBOutput_2 = 0;

				boolean whetherReject_tDBOutput_2 = false;

				java.sql.Connection conn_tDBOutput_2 = null;
				String dbUser_tDBOutput_2 = null;

				conn_tDBOutput_2 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				int count_tDBOutput_2 = 0;
				java.sql.DatabaseMetaData dbMetaData_tDBOutput_2 = conn_tDBOutput_2.getMetaData();
				boolean whetherExist_tDBOutput_2 = false;
				try (java.sql.ResultSet rsTable_tDBOutput_2 = dbMetaData_tDBOutput_2.getTables(null, null, null,
						new String[] { "TABLE" })) {
					String defaultSchema_tDBOutput_2 = "public";
					if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
						try (java.sql.Statement stmtSchema_tDBOutput_2 = conn_tDBOutput_2.createStatement();
								java.sql.ResultSet rsSchema_tDBOutput_2 = stmtSchema_tDBOutput_2
										.executeQuery("select current_schema() ")) {
							while (rsSchema_tDBOutput_2.next()) {
								defaultSchema_tDBOutput_2 = rsSchema_tDBOutput_2.getString("current_schema");
							}
						}
					}
					while (rsTable_tDBOutput_2.next()) {
						String table_tDBOutput_2 = rsTable_tDBOutput_2.getString("TABLE_NAME");
						String schema_tDBOutput_2 = rsTable_tDBOutput_2.getString("TABLE_SCHEM");
						if (table_tDBOutput_2.equals(("marche")) && (schema_tDBOutput_2.equals(dbschema_tDBOutput_2)
								|| ((dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0)
										&& defaultSchema_tDBOutput_2.equals(schema_tDBOutput_2)))) {
							whetherExist_tDBOutput_2 = true;
							break;
						}
					}
				}
				if (!whetherExist_tDBOutput_2) {
					try (java.sql.Statement stmtCreate_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
						stmtCreate_tDBOutput_2.execute("CREATE TABLE \"" + tableName_tDBOutput_2
								+ "\"(\"recordid\" VARCHAR(40)  ,\"sirenetablissementvalide\" VARCHAR(255)  ,\"denominationsocialeetablissement\" VARCHAR(255)  ,\"libellecommuneacheteur\" VARCHAR(255)  ,\"lieuexecutioncode\" VARCHAR(10)  ,\"adresseetablissement\" VARCHAR(255)  ,\"codepostalacheteur\" VARCHAR(10)  ,\"libelleregionacheteur\" VARCHAR(255)  ,\"departementacheteur\" VARCHAR(5)  ,\"idmarche\" INT4 ,\"dureemois\" INT4 ,\"datenotification\" VARCHAR(255)  ,\"montantcalcule\" FLOAT4 ,\"libelledepartementacheteur\" VARCHAR(255)  ,\"codecpv_division\" INT4 ,\"lieuexecutionnom\" VARCHAR(255)  ,\"libellearrondissementacheteur\" VARCHAR(255)  ,\"populationcommuneetablissement\" VARCHAR(255)  ,\"codecommuneacheteur\" VARCHAR(10)  ,\"superficiecommuneetablissement\" VARCHAR(255)  ,\"nicetablissement\" VARCHAR(255)  ,\"geoloccommuneetablissement___\" VARCHAR(255)  ,\"objetmarche\" VARCHAR(512)  ,\"nature\" VARCHAR(255)  ,\"nombretitulairesurmarchepresume\" VARCHAR(255)  ,\"libelleregionetablissement\" VARCHAR(255)  ,\"id\" VARCHAR(255)  ,\"source\" VARCHAR(255)  ,\"dureemoisestimee\" VARCHAR(255)  ,\"nomacheteur\" VARCHAR(255)  ,\"referencecpv\" VARCHAR(255)  ,\"type\" VARCHAR(255)  ,\"sirenetablissement\" VARCHAR(255)  ,\"superficiecommuneacheteur\" FLOAT4 ,\"codearrondissementacheteur\" INT4 ,\"distanceacheteuretablissement\" VARCHAR(255)  ,\"communeetablissement\" VARCHAR(255)  ,\"montant\" VARCHAR(255)  ,\"departementetablissement\" VARCHAR(255)  ,\"datepublicationdonnees\" VARCHAR ,\"sirenacheteurvalide\" VARCHAR(255)  ,\"moisnotification\" VARCHAR(255)  ,\"coderegionetablissement\" VARCHAR(255)  ,\"formeprix\" VARCHAR(255)  ,\"siretetablissementvalide\" VARCHAR(255)  ,\"codedepartementexecution\" FLOAT4 ,\"dist\" FLOAT4 ,\"coderegionacheteur\" VARCHAR ,\"lieuexecutiontypecode\" VARCHAR(255)  ,\"codecommuneetablissement\" VARCHAR(255)  ,\"populationcommuneacheteur\" FLOAT4 ,\"idacheteur\" VARCHAR(255)  ,\"codecpv_original\" VARCHAR(255)  ,\"siretetablissement\" VARCHAR(255)  ,\"libelledepartementetablissement\" VARCHAR(255)  ,\"typeidentifiantetablissement\" VARCHAR(255)  ,\"categorieetablissement\" VARCHAR(255)  ,\"coderegionexecution\" FLOAT4 ,\"codepostaletablissement\" VARCHAR(255)  ,\"natureobjetmarche\" VARCHAR(255)  ,\"dureemoiscalculee\" FLOAT4 ,\"codetypeetablissement\" VARCHAR(255)  ,\"geoloccommuneacheteur___\" VARCHAR(255)  ,\"codecpv\" VARCHAR(255)  ,\"anneenotification\" VARCHAR(255)  ,\"libelleregionexecution\" VARCHAR(255)  ,\"procedure\" VARCHAR(255)  ,\"libellearrondissementetablissement\" VARCHAR(255)  ,\"codearrondissementetablissement\" VARCHAR(255)  ,primary key(\"recordid\"))");
					}
				}
				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_2 + "\" WHERE \"recordid\" = ?");
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);
				String insert_tDBOutput_2 = "INSERT INTO \"" + tableName_tDBOutput_2
						+ "\" (\"recordid\",\"sirenetablissementvalide\",\"denominationsocialeetablissement\",\"libellecommuneacheteur\",\"lieuexecutioncode\",\"adresseetablissement\",\"codepostalacheteur\",\"libelleregionacheteur\",\"departementacheteur\",\"idmarche\",\"dureemois\",\"datenotification\",\"montantcalcule\",\"libelledepartementacheteur\",\"codecpv_division\",\"lieuexecutionnom\",\"libellearrondissementacheteur\",\"populationcommuneetablissement\",\"codecommuneacheteur\",\"superficiecommuneetablissement\",\"nicetablissement\",\"geoloccommuneetablissement___\",\"objetmarche\",\"nature\",\"nombretitulairesurmarchepresume\",\"libelleregionetablissement\",\"id\",\"source\",\"dureemoisestimee\",\"nomacheteur\",\"referencecpv\",\"type\",\"sirenetablissement\",\"superficiecommuneacheteur\",\"codearrondissementacheteur\",\"distanceacheteuretablissement\",\"communeetablissement\",\"montant\",\"departementetablissement\",\"datepublicationdonnees\",\"sirenacheteurvalide\",\"moisnotification\",\"coderegionetablissement\",\"formeprix\",\"siretetablissementvalide\",\"codedepartementexecution\",\"dist\",\"coderegionacheteur\",\"lieuexecutiontypecode\",\"codecommuneetablissement\",\"populationcommuneacheteur\",\"idacheteur\",\"codecpv_original\",\"siretetablissement\",\"libelledepartementetablissement\",\"typeidentifiantetablissement\",\"categorieetablissement\",\"coderegionexecution\",\"codepostaletablissement\",\"natureobjetmarche\",\"dureemoiscalculee\",\"codetypeetablissement\",\"geoloccommuneacheteur___\",\"codecpv\",\"anneenotification\",\"libelleregionexecution\",\"procedure\",\"libellearrondissementetablissement\",\"codearrondissementetablissement\") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_2 = conn_tDBOutput_2
						.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmtInsert_tDBOutput_2", pstmtInsert_tDBOutput_2);
				String update_tDBOutput_2 = "UPDATE \"" + tableName_tDBOutput_2
						+ "\" SET \"sirenetablissementvalide\" = ?,\"denominationsocialeetablissement\" = ?,\"libellecommuneacheteur\" = ?,\"lieuexecutioncode\" = ?,\"adresseetablissement\" = ?,\"codepostalacheteur\" = ?,\"libelleregionacheteur\" = ?,\"departementacheteur\" = ?,\"idmarche\" = ?,\"dureemois\" = ?,\"datenotification\" = ?,\"montantcalcule\" = ?,\"libelledepartementacheteur\" = ?,\"codecpv_division\" = ?,\"lieuexecutionnom\" = ?,\"libellearrondissementacheteur\" = ?,\"populationcommuneetablissement\" = ?,\"codecommuneacheteur\" = ?,\"superficiecommuneetablissement\" = ?,\"nicetablissement\" = ?,\"geoloccommuneetablissement___\" = ?,\"objetmarche\" = ?,\"nature\" = ?,\"nombretitulairesurmarchepresume\" = ?,\"libelleregionetablissement\" = ?,\"id\" = ?,\"source\" = ?,\"dureemoisestimee\" = ?,\"nomacheteur\" = ?,\"referencecpv\" = ?,\"type\" = ?,\"sirenetablissement\" = ?,\"superficiecommuneacheteur\" = ?,\"codearrondissementacheteur\" = ?,\"distanceacheteuretablissement\" = ?,\"communeetablissement\" = ?,\"montant\" = ?,\"departementetablissement\" = ?,\"datepublicationdonnees\" = ?,\"sirenacheteurvalide\" = ?,\"moisnotification\" = ?,\"coderegionetablissement\" = ?,\"formeprix\" = ?,\"siretetablissementvalide\" = ?,\"codedepartementexecution\" = ?,\"dist\" = ?,\"coderegionacheteur\" = ?,\"lieuexecutiontypecode\" = ?,\"codecommuneetablissement\" = ?,\"populationcommuneacheteur\" = ?,\"idacheteur\" = ?,\"codecpv_original\" = ?,\"siretetablissement\" = ?,\"libelledepartementetablissement\" = ?,\"typeidentifiantetablissement\" = ?,\"categorieetablissement\" = ?,\"coderegionexecution\" = ?,\"codepostaletablissement\" = ?,\"natureobjetmarche\" = ?,\"dureemoiscalculee\" = ?,\"codetypeetablissement\" = ?,\"geoloccommuneacheteur___\" = ?,\"codecpv\" = ?,\"anneenotification\" = ?,\"libelleregionexecution\" = ?,\"procedure\" = ?,\"libellearrondissementetablissement\" = ?,\"codearrondissementetablissement\" = ? WHERE \"recordid\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_2 = conn_tDBOutput_2
						.prepareStatement(update_tDBOutput_2);
				resourceMap.put("pstmtUpdate_tDBOutput_2", pstmtUpdate_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tWarn_3 begin ] start
				 */

				ok_Hash.put("tWarn_3", false);
				start_Hash.put("tWarn_3", System.currentTimeMillis());

				currentComponent = "tWarn_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tWarn_3 = 0;

				/**
				 * [tWarn_3 begin ] stop
				 */

				/**
				 * [tFileInputJSON_3 begin ] start
				 */

				ok_Hash.put("tFileInputJSON_3", false);
				start_Hash.put("tFileInputJSON_3", System.currentTimeMillis());

				currentComponent = "tFileInputJSON_3";

				int tos_count_tFileInputJSON_3 = 0;

				class JsonPathCache_tFileInputJSON_3 {
					final java.util.Map<String, com.jayway.jsonpath.JsonPath> jsonPathString2compiledJsonPath = new java.util.HashMap<String, com.jayway.jsonpath.JsonPath>();

					public com.jayway.jsonpath.JsonPath getCompiledJsonPath(String jsonPath) {
						if (jsonPathString2compiledJsonPath.containsKey(jsonPath)) {
							return jsonPathString2compiledJsonPath.get(jsonPath);
						} else {
							com.jayway.jsonpath.JsonPath compiledLoopPath = com.jayway.jsonpath.JsonPath
									.compile(jsonPath);
							jsonPathString2compiledJsonPath.put(jsonPath, compiledLoopPath);
							return compiledLoopPath;
						}
					}
				}

				int nb_line_tFileInputJSON_3 = 0;

				JsonPathCache_tFileInputJSON_3 jsonPathCache_tFileInputJSON_3 = new JsonPathCache_tFileInputJSON_3();

				String loopPath_tFileInputJSON_3 = "$.records[*]";
				java.util.List<Object> resultset_tFileInputJSON_3 = new java.util.ArrayList<Object>();

				java.io.InputStream is_tFileInputJSON_3 = null;
				com.jayway.jsonpath.ParseContext parseContext_tFileInputJSON_3 = com.jayway.jsonpath.JsonPath
						.using(com.jayway.jsonpath.Configuration.defaultConfiguration());
				Object filenameOrStream_tFileInputJSON_3 = null;
				try {
					filenameOrStream_tFileInputJSON_3 = context.data_storage_path + "/" + context.api_result_filename;
				} catch (java.lang.Exception e_tFileInputJSON_3) {
					globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());

					globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
					System.err.println(e_tFileInputJSON_3.getMessage());
				}

				com.jayway.jsonpath.ReadContext document_tFileInputJSON_3 = null;
				try {
					if (filenameOrStream_tFileInputJSON_3 instanceof java.io.InputStream) {
						is_tFileInputJSON_3 = (java.io.InputStream) filenameOrStream_tFileInputJSON_3;
					} else {

						is_tFileInputJSON_3 = new java.io.FileInputStream((String) filenameOrStream_tFileInputJSON_3);

					}

					document_tFileInputJSON_3 = parseContext_tFileInputJSON_3.parse(is_tFileInputJSON_3, "UTF-8");
					com.jayway.jsonpath.JsonPath compiledLoopPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
							.getCompiledJsonPath(loopPath_tFileInputJSON_3);
					Object result_tFileInputJSON_3 = document_tFileInputJSON_3.read(compiledLoopPath_tFileInputJSON_3,
							net.minidev.json.JSONObject.class);
					if (result_tFileInputJSON_3 instanceof net.minidev.json.JSONArray) {
						resultset_tFileInputJSON_3 = (net.minidev.json.JSONArray) result_tFileInputJSON_3;
					} else {
						resultset_tFileInputJSON_3.add(result_tFileInputJSON_3);
					}
				} catch (java.lang.Exception e_tFileInputJSON_3) {
					globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
					globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
					System.err.println(e_tFileInputJSON_3.getMessage());
				} finally {
					if (is_tFileInputJSON_3 != null) {
						is_tFileInputJSON_3.close();
					}
				}

				String jsonPath_tFileInputJSON_3 = null;
				com.jayway.jsonpath.JsonPath compiledJsonPath_tFileInputJSON_3 = null;

				Object value_tFileInputJSON_3 = null;
				Object root_tFileInputJSON_3 = null;
				for (Object row_tFileInputJSON_3 : resultset_tFileInputJSON_3) {
					nb_line_tFileInputJSON_3++;
					row1 = null;
					boolean whetherReject_tFileInputJSON_3 = false;
					row1 = new row1Struct();

					try {
						jsonPath_tFileInputJSON_3 = "recordid";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.recordid = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.recordid =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.sirenetablissementvalide";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.sirenetablissementvalide = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.sirenetablissementvalide =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.denominationsocialeetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.denominationsocialeetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.denominationsocialeetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.libellecommuneacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.libellecommuneacheteur = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.libellecommuneacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.lieuexecutioncode";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.lieuexecutioncode = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.lieuexecutioncode =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.adresseetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.adresseetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.adresseetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codepostalacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.codepostalacheteur = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codepostalacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.libelleregionacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.libelleregionacheteur = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.libelleregionacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.departementacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.departementacheteur = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.departementacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.idmarche";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.idmarche = ParserUtils.parseTo_Integer(value_tFileInputJSON_3.toString());
							} else {
								row1.idmarche =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.idmarche =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.dureemois";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.dureemois = ParserUtils.parseTo_Integer(value_tFileInputJSON_3.toString());
							} else {
								row1.dureemois =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.dureemois =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.datenotification";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.datenotification = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.datenotification =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.montantcalcule";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.montantcalcule = ParserUtils.parseTo_Float(value_tFileInputJSON_3.toString());
							} else {
								row1.montantcalcule =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.montantcalcule =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.libelledepartementacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.libelledepartementacheteur = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.libelledepartementacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codecpv_division";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.codecpv_division = ParserUtils.parseTo_Integer(value_tFileInputJSON_3.toString());
							} else {
								row1.codecpv_division =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codecpv_division =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.lieuexecutionnom";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.lieuexecutionnom = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.lieuexecutionnom =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.libellearrondissementacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.libellearrondissementacheteur = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.libellearrondissementacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.populationcommuneetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.populationcommuneetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.populationcommuneetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codecommuneacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.codecommuneacheteur = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codecommuneacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.superficiecommuneetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.superficiecommuneetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.superficiecommuneetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.nicetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.nicetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.nicetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.geoloccommuneetablissement__";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.geoloccommuneetablissement___ = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.geoloccommuneetablissement___ =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.objetmarche";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.objetmarche = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.objetmarche =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.nature";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.nature = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.nature =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.nombretitulairesurmarchepresume";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.nombretitulairesurmarchepresume = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.nombretitulairesurmarchepresume =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.libelleregionetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.libelleregionetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.libelleregionetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.id";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.id = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.id =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.source";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.source = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.source =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.dureemoisestimee";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.dureemoisestimee = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.dureemoisestimee =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.nomacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.nomacheteur = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.nomacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.referencecpv";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.referencecpv = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.referencecpv =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.type";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.type = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.type =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.sirenetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.sirenetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.sirenetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.superficiecommuneacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.superficiecommuneacheteur = ParserUtils
										.parseTo_Float(value_tFileInputJSON_3.toString());
							} else {
								row1.superficiecommuneacheteur =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.superficiecommuneacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codearrondissementacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.codearrondissementacheteur = ParserUtils
										.parseTo_Integer(value_tFileInputJSON_3.toString());
							} else {
								row1.codearrondissementacheteur =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codearrondissementacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.distanceacheteuretablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.distanceacheteuretablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.distanceacheteuretablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.communeetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.communeetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.communeetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.montant";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.montant = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.montant =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.departementetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.departementetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.departementetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.datepublicationdonnees";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.datepublicationdonnees = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.datepublicationdonnees =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.sirenacheteurvalide";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.sirenacheteurvalide = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.sirenacheteurvalide =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.moisnotification";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.moisnotification = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.moisnotification =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.coderegionetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.coderegionetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.coderegionetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.formeprix";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.formeprix = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.formeprix =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.siretetablissementvalide";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.siretetablissementvalide = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.siretetablissementvalide =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codedepartementexecution";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.codedepartementexecution = ParserUtils
										.parseTo_Float(value_tFileInputJSON_3.toString());
							} else {
								row1.codedepartementexecution =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codedepartementexecution =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.dist";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.dist = ParserUtils.parseTo_Float(value_tFileInputJSON_3.toString());
							} else {
								row1.dist =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.dist =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.coderegionacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.coderegionacheteur = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.coderegionacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.lieuexecutiontypecode";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.lieuexecutiontypecode = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.lieuexecutiontypecode =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codecommuneetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.codecommuneetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codecommuneetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.populationcommuneacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.populationcommuneacheteur = ParserUtils
										.parseTo_Float(value_tFileInputJSON_3.toString());
							} else {
								row1.populationcommuneacheteur =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.populationcommuneacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.idacheteur";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.idacheteur = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.idacheteur =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codecpv_original";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.codecpv_original = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codecpv_original =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.siretetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.siretetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.siretetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.libelledepartementetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.libelledepartementetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.libelledepartementetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.typeidentifiantetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.typeidentifiantetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.typeidentifiantetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.categorieetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.categorieetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.categorieetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.coderegionexecution";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.coderegionexecution = ParserUtils.parseTo_Float(value_tFileInputJSON_3.toString());
							} else {
								row1.coderegionexecution =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.coderegionexecution =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codepostaletablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.codepostaletablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codepostaletablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.natureobjetmarche";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.natureobjetmarche = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.natureobjetmarche =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.dureemoiscalculee";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							if (value_tFileInputJSON_3 != null && !value_tFileInputJSON_3.toString().isEmpty()) {
								row1.dureemoiscalculee = ParserUtils.parseTo_Float(value_tFileInputJSON_3.toString());
							} else {
								row1.dureemoiscalculee =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.dureemoiscalculee =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codetypeetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.codetypeetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codetypeetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.geoloccommuneacheteur__";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.geoloccommuneacheteur___ = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.geoloccommuneacheteur___ =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codecpv";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.codecpv = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codecpv =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.anneenotification";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.anneenotification = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.anneenotification =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.libelleregionexecution";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.libelleregionexecution = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.libelleregionexecution =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.procedure";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.procedure = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.procedure =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.libellearrondissementetablissemnt";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.libellearrondissementetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.libellearrondissementetablissement =

									null;
						}
						jsonPath_tFileInputJSON_3 = "fields.codearrondissementetablissement";
						compiledJsonPath_tFileInputJSON_3 = jsonPathCache_tFileInputJSON_3
								.getCompiledJsonPath(jsonPath_tFileInputJSON_3);

						try {

							value_tFileInputJSON_3 = compiledJsonPath_tFileInputJSON_3.read(row_tFileInputJSON_3);

							row1.codearrondissementetablissement = value_tFileInputJSON_3 == null ?

									null : value_tFileInputJSON_3.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_3) {
							globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
							row1.codearrondissementetablissement =

									null;
						}
					} catch (java.lang.Exception e_tFileInputJSON_3) {
						globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
						whetherReject_tFileInputJSON_3 = true;
						System.err.println(e_tFileInputJSON_3.getMessage());
						row1 = null;
						globalMap.put("tFileInputJSON_3_ERROR_MESSAGE", e_tFileInputJSON_3.getMessage());
					}
//}

					/**
					 * [tFileInputJSON_3 begin ] stop
					 */

					/**
					 * [tFileInputJSON_3 main ] start
					 */

					currentComponent = "tFileInputJSON_3";

					tos_count_tFileInputJSON_3++;

					/**
					 * [tFileInputJSON_3 main ] stop
					 */

					/**
					 * [tFileInputJSON_3 process_data_begin ] start
					 */

					currentComponent = "tFileInputJSON_3";

					/**
					 * [tFileInputJSON_3 process_data_begin ] stop
					 */
// Start of branch "row1"
					if (row1 != null) {

						/**
						 * [tWarn_3 main ] start
						 */

						currentComponent = "tWarn_3";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row1"

							);
						}

						try {

							resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_3", "", Thread.currentThread().getId() + "",
									"WARN", "", "Extracting record : " + row1.denominationsocialeetablissement + " => "
											+ row1.recordid,
									"", "");
							globalMap.put("tWarn_3_WARN_MESSAGES", "Extracting record : "
									+ row1.denominationsocialeetablissement + " => " + row1.recordid);
							globalMap.put("tWarn_3_WARN_PRIORITY", 4);
							globalMap.put("tWarn_3_WARN_CODE", 42);

						} catch (Exception e_tWarn_3) {
							globalMap.put("tWarn_3_ERROR_MESSAGE", e_tWarn_3.getMessage());
							logIgnoredError(
									String.format("tWarn_3 - tWarn failed to log message due to internal error: %s",
											e_tWarn_3),
									e_tWarn_3);
						}

						row6 = row1;

						tos_count_tWarn_3++;

						/**
						 * [tWarn_3 main ] stop
						 */

						/**
						 * [tWarn_3 process_data_begin ] start
						 */

						currentComponent = "tWarn_3";

						/**
						 * [tWarn_3 process_data_begin ] stop
						 */

						/**
						 * [tDBOutput_2 main ] start
						 */

						currentComponent = "tDBOutput_2";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row6"

							);
						}

						whetherReject_tDBOutput_2 = false;
						if (row6.recordid == null) {
							pstmt_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
						} else {
							pstmt_tDBOutput_2.setString(1, row6.recordid);
						}

						int checkCount_tDBOutput_2 = -1;
						try (java.sql.ResultSet rs_tDBOutput_2 = pstmt_tDBOutput_2.executeQuery()) {
							while (rs_tDBOutput_2.next()) {
								checkCount_tDBOutput_2 = rs_tDBOutput_2.getInt(1);
							}
						}
						if (checkCount_tDBOutput_2 > 0) {
							if (row6.sirenetablissementvalide == null) {
								pstmtUpdate_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(1, row6.sirenetablissementvalide);
							}

							if (row6.denominationsocialeetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(2, row6.denominationsocialeetablissement);
							}

							if (row6.libellecommuneacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(3, row6.libellecommuneacheteur);
							}

							if (row6.lieuexecutioncode == null) {
								pstmtUpdate_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(4, row6.lieuexecutioncode);
							}

							if (row6.adresseetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(5, row6.adresseetablissement);
							}

							if (row6.codepostalacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(6, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(6, row6.codepostalacheteur);
							}

							if (row6.libelleregionacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(7, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(7, row6.libelleregionacheteur);
							}

							if (row6.departementacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(8, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(8, row6.departementacheteur);
							}

							if (row6.idmarche == null) {
								pstmtUpdate_tDBOutput_2.setNull(9, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_2.setInt(9, row6.idmarche);
							}

							if (row6.dureemois == null) {
								pstmtUpdate_tDBOutput_2.setNull(10, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_2.setInt(10, row6.dureemois);
							}

							if (row6.datenotification == null) {
								pstmtUpdate_tDBOutput_2.setNull(11, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(11, row6.datenotification);
							}

							if (row6.montantcalcule == null) {
								pstmtUpdate_tDBOutput_2.setNull(12, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_2.setFloat(12, row6.montantcalcule);
							}

							if (row6.libelledepartementacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(13, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(13, row6.libelledepartementacheteur);
							}

							if (row6.codecpv_division == null) {
								pstmtUpdate_tDBOutput_2.setNull(14, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_2.setInt(14, row6.codecpv_division);
							}

							if (row6.lieuexecutionnom == null) {
								pstmtUpdate_tDBOutput_2.setNull(15, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(15, row6.lieuexecutionnom);
							}

							if (row6.libellearrondissementacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(16, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(16, row6.libellearrondissementacheteur);
							}

							if (row6.populationcommuneetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(17, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(17, row6.populationcommuneetablissement);
							}

							if (row6.codecommuneacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(18, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(18, row6.codecommuneacheteur);
							}

							if (row6.superficiecommuneetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(19, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(19, row6.superficiecommuneetablissement);
							}

							if (row6.nicetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(20, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(20, row6.nicetablissement);
							}

							if (row6.geoloccommuneetablissement___ == null) {
								pstmtUpdate_tDBOutput_2.setNull(21, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(21, row6.geoloccommuneetablissement___);
							}

							if (row6.objetmarche == null) {
								pstmtUpdate_tDBOutput_2.setNull(22, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(22, row6.objetmarche);
							}

							if (row6.nature == null) {
								pstmtUpdate_tDBOutput_2.setNull(23, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(23, row6.nature);
							}

							if (row6.nombretitulairesurmarchepresume == null) {
								pstmtUpdate_tDBOutput_2.setNull(24, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(24, row6.nombretitulairesurmarchepresume);
							}

							if (row6.libelleregionetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(25, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(25, row6.libelleregionetablissement);
							}

							if (row6.id == null) {
								pstmtUpdate_tDBOutput_2.setNull(26, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(26, row6.id);
							}

							if (row6.source == null) {
								pstmtUpdate_tDBOutput_2.setNull(27, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(27, row6.source);
							}

							if (row6.dureemoisestimee == null) {
								pstmtUpdate_tDBOutput_2.setNull(28, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(28, row6.dureemoisestimee);
							}

							if (row6.nomacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(29, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(29, row6.nomacheteur);
							}

							if (row6.referencecpv == null) {
								pstmtUpdate_tDBOutput_2.setNull(30, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(30, row6.referencecpv);
							}

							if (row6.type == null) {
								pstmtUpdate_tDBOutput_2.setNull(31, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(31, row6.type);
							}

							if (row6.sirenetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(32, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(32, row6.sirenetablissement);
							}

							if (row6.superficiecommuneacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(33, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_2.setFloat(33, row6.superficiecommuneacheteur);
							}

							if (row6.codearrondissementacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(34, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_2.setInt(34, row6.codearrondissementacheteur);
							}

							if (row6.distanceacheteuretablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(35, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(35, row6.distanceacheteuretablissement);
							}

							if (row6.communeetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(36, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(36, row6.communeetablissement);
							}

							if (row6.montant == null) {
								pstmtUpdate_tDBOutput_2.setNull(37, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(37, row6.montant);
							}

							if (row6.departementetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(38, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(38, row6.departementetablissement);
							}

							if (row6.datepublicationdonnees == null) {
								pstmtUpdate_tDBOutput_2.setNull(39, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(39, row6.datepublicationdonnees);
							}

							if (row6.sirenacheteurvalide == null) {
								pstmtUpdate_tDBOutput_2.setNull(40, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(40, row6.sirenacheteurvalide);
							}

							if (row6.moisnotification == null) {
								pstmtUpdate_tDBOutput_2.setNull(41, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(41, row6.moisnotification);
							}

							if (row6.coderegionetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(42, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(42, row6.coderegionetablissement);
							}

							if (row6.formeprix == null) {
								pstmtUpdate_tDBOutput_2.setNull(43, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(43, row6.formeprix);
							}

							if (row6.siretetablissementvalide == null) {
								pstmtUpdate_tDBOutput_2.setNull(44, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(44, row6.siretetablissementvalide);
							}

							if (row6.codedepartementexecution == null) {
								pstmtUpdate_tDBOutput_2.setNull(45, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_2.setFloat(45, row6.codedepartementexecution);
							}

							if (row6.dist == null) {
								pstmtUpdate_tDBOutput_2.setNull(46, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_2.setFloat(46, row6.dist);
							}

							if (row6.coderegionacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(47, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(47, row6.coderegionacheteur);
							}

							if (row6.lieuexecutiontypecode == null) {
								pstmtUpdate_tDBOutput_2.setNull(48, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(48, row6.lieuexecutiontypecode);
							}

							if (row6.codecommuneetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(49, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(49, row6.codecommuneetablissement);
							}

							if (row6.populationcommuneacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(50, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_2.setFloat(50, row6.populationcommuneacheteur);
							}

							if (row6.idacheteur == null) {
								pstmtUpdate_tDBOutput_2.setNull(51, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(51, row6.idacheteur);
							}

							if (row6.codecpv_original == null) {
								pstmtUpdate_tDBOutput_2.setNull(52, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(52, row6.codecpv_original);
							}

							if (row6.siretetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(53, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(53, row6.siretetablissement);
							}

							if (row6.libelledepartementetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(54, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(54, row6.libelledepartementetablissement);
							}

							if (row6.typeidentifiantetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(55, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(55, row6.typeidentifiantetablissement);
							}

							if (row6.categorieetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(56, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(56, row6.categorieetablissement);
							}

							if (row6.coderegionexecution == null) {
								pstmtUpdate_tDBOutput_2.setNull(57, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_2.setFloat(57, row6.coderegionexecution);
							}

							if (row6.codepostaletablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(58, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(58, row6.codepostaletablissement);
							}

							if (row6.natureobjetmarche == null) {
								pstmtUpdate_tDBOutput_2.setNull(59, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(59, row6.natureobjetmarche);
							}

							if (row6.dureemoiscalculee == null) {
								pstmtUpdate_tDBOutput_2.setNull(60, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_2.setFloat(60, row6.dureemoiscalculee);
							}

							if (row6.codetypeetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(61, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(61, row6.codetypeetablissement);
							}

							if (row6.geoloccommuneacheteur___ == null) {
								pstmtUpdate_tDBOutput_2.setNull(62, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(62, row6.geoloccommuneacheteur___);
							}

							if (row6.codecpv == null) {
								pstmtUpdate_tDBOutput_2.setNull(63, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(63, row6.codecpv);
							}

							if (row6.anneenotification == null) {
								pstmtUpdate_tDBOutput_2.setNull(64, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(64, row6.anneenotification);
							}

							if (row6.libelleregionexecution == null) {
								pstmtUpdate_tDBOutput_2.setNull(65, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(65, row6.libelleregionexecution);
							}

							if (row6.procedure == null) {
								pstmtUpdate_tDBOutput_2.setNull(66, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(66, row6.procedure);
							}

							if (row6.libellearrondissementetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(67, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(67, row6.libellearrondissementetablissement);
							}

							if (row6.codearrondissementetablissement == null) {
								pstmtUpdate_tDBOutput_2.setNull(68, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(68, row6.codearrondissementetablissement);
							}

							if (row6.recordid == null) {
								pstmtUpdate_tDBOutput_2.setNull(69 + count_tDBOutput_2, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_2.setString(69 + count_tDBOutput_2, row6.recordid);
							}

							try {

								int processedCount_tDBOutput_2 = pstmtUpdate_tDBOutput_2.executeUpdate();
								updatedCount_tDBOutput_2 += processedCount_tDBOutput_2;
								rowsToCommitCount_tDBOutput_2 += processedCount_tDBOutput_2;
								nb_line_tDBOutput_2++;

							} catch (java.lang.Exception e) {
								globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

								whetherReject_tDBOutput_2 = true;
								nb_line_tDBOutput_2++;
								System.err.print(e.getMessage());
							}
						} else {
							if (row6.recordid == null) {
								pstmtInsert_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(1, row6.recordid);
							}

							if (row6.sirenetablissementvalide == null) {
								pstmtInsert_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(2, row6.sirenetablissementvalide);
							}

							if (row6.denominationsocialeetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(3, row6.denominationsocialeetablissement);
							}

							if (row6.libellecommuneacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(4, row6.libellecommuneacheteur);
							}

							if (row6.lieuexecutioncode == null) {
								pstmtInsert_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(5, row6.lieuexecutioncode);
							}

							if (row6.adresseetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(6, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(6, row6.adresseetablissement);
							}

							if (row6.codepostalacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(7, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(7, row6.codepostalacheteur);
							}

							if (row6.libelleregionacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(8, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(8, row6.libelleregionacheteur);
							}

							if (row6.departementacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(9, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(9, row6.departementacheteur);
							}

							if (row6.idmarche == null) {
								pstmtInsert_tDBOutput_2.setNull(10, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_2.setInt(10, row6.idmarche);
							}

							if (row6.dureemois == null) {
								pstmtInsert_tDBOutput_2.setNull(11, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_2.setInt(11, row6.dureemois);
							}

							if (row6.datenotification == null) {
								pstmtInsert_tDBOutput_2.setNull(12, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(12, row6.datenotification);
							}

							if (row6.montantcalcule == null) {
								pstmtInsert_tDBOutput_2.setNull(13, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_2.setFloat(13, row6.montantcalcule);
							}

							if (row6.libelledepartementacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(14, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(14, row6.libelledepartementacheteur);
							}

							if (row6.codecpv_division == null) {
								pstmtInsert_tDBOutput_2.setNull(15, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_2.setInt(15, row6.codecpv_division);
							}

							if (row6.lieuexecutionnom == null) {
								pstmtInsert_tDBOutput_2.setNull(16, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(16, row6.lieuexecutionnom);
							}

							if (row6.libellearrondissementacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(17, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(17, row6.libellearrondissementacheteur);
							}

							if (row6.populationcommuneetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(18, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(18, row6.populationcommuneetablissement);
							}

							if (row6.codecommuneacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(19, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(19, row6.codecommuneacheteur);
							}

							if (row6.superficiecommuneetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(20, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(20, row6.superficiecommuneetablissement);
							}

							if (row6.nicetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(21, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(21, row6.nicetablissement);
							}

							if (row6.geoloccommuneetablissement___ == null) {
								pstmtInsert_tDBOutput_2.setNull(22, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(22, row6.geoloccommuneetablissement___);
							}

							if (row6.objetmarche == null) {
								pstmtInsert_tDBOutput_2.setNull(23, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(23, row6.objetmarche);
							}

							if (row6.nature == null) {
								pstmtInsert_tDBOutput_2.setNull(24, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(24, row6.nature);
							}

							if (row6.nombretitulairesurmarchepresume == null) {
								pstmtInsert_tDBOutput_2.setNull(25, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(25, row6.nombretitulairesurmarchepresume);
							}

							if (row6.libelleregionetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(26, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(26, row6.libelleregionetablissement);
							}

							if (row6.id == null) {
								pstmtInsert_tDBOutput_2.setNull(27, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(27, row6.id);
							}

							if (row6.source == null) {
								pstmtInsert_tDBOutput_2.setNull(28, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(28, row6.source);
							}

							if (row6.dureemoisestimee == null) {
								pstmtInsert_tDBOutput_2.setNull(29, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(29, row6.dureemoisestimee);
							}

							if (row6.nomacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(30, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(30, row6.nomacheteur);
							}

							if (row6.referencecpv == null) {
								pstmtInsert_tDBOutput_2.setNull(31, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(31, row6.referencecpv);
							}

							if (row6.type == null) {
								pstmtInsert_tDBOutput_2.setNull(32, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(32, row6.type);
							}

							if (row6.sirenetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(33, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(33, row6.sirenetablissement);
							}

							if (row6.superficiecommuneacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(34, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_2.setFloat(34, row6.superficiecommuneacheteur);
							}

							if (row6.codearrondissementacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(35, java.sql.Types.INTEGER);
							} else {
								pstmtInsert_tDBOutput_2.setInt(35, row6.codearrondissementacheteur);
							}

							if (row6.distanceacheteuretablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(36, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(36, row6.distanceacheteuretablissement);
							}

							if (row6.communeetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(37, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(37, row6.communeetablissement);
							}

							if (row6.montant == null) {
								pstmtInsert_tDBOutput_2.setNull(38, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(38, row6.montant);
							}

							if (row6.departementetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(39, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(39, row6.departementetablissement);
							}

							if (row6.datepublicationdonnees == null) {
								pstmtInsert_tDBOutput_2.setNull(40, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(40, row6.datepublicationdonnees);
							}

							if (row6.sirenacheteurvalide == null) {
								pstmtInsert_tDBOutput_2.setNull(41, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(41, row6.sirenacheteurvalide);
							}

							if (row6.moisnotification == null) {
								pstmtInsert_tDBOutput_2.setNull(42, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(42, row6.moisnotification);
							}

							if (row6.coderegionetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(43, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(43, row6.coderegionetablissement);
							}

							if (row6.formeprix == null) {
								pstmtInsert_tDBOutput_2.setNull(44, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(44, row6.formeprix);
							}

							if (row6.siretetablissementvalide == null) {
								pstmtInsert_tDBOutput_2.setNull(45, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(45, row6.siretetablissementvalide);
							}

							if (row6.codedepartementexecution == null) {
								pstmtInsert_tDBOutput_2.setNull(46, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_2.setFloat(46, row6.codedepartementexecution);
							}

							if (row6.dist == null) {
								pstmtInsert_tDBOutput_2.setNull(47, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_2.setFloat(47, row6.dist);
							}

							if (row6.coderegionacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(48, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(48, row6.coderegionacheteur);
							}

							if (row6.lieuexecutiontypecode == null) {
								pstmtInsert_tDBOutput_2.setNull(49, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(49, row6.lieuexecutiontypecode);
							}

							if (row6.codecommuneetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(50, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(50, row6.codecommuneetablissement);
							}

							if (row6.populationcommuneacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(51, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_2.setFloat(51, row6.populationcommuneacheteur);
							}

							if (row6.idacheteur == null) {
								pstmtInsert_tDBOutput_2.setNull(52, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(52, row6.idacheteur);
							}

							if (row6.codecpv_original == null) {
								pstmtInsert_tDBOutput_2.setNull(53, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(53, row6.codecpv_original);
							}

							if (row6.siretetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(54, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(54, row6.siretetablissement);
							}

							if (row6.libelledepartementetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(55, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(55, row6.libelledepartementetablissement);
							}

							if (row6.typeidentifiantetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(56, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(56, row6.typeidentifiantetablissement);
							}

							if (row6.categorieetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(57, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(57, row6.categorieetablissement);
							}

							if (row6.coderegionexecution == null) {
								pstmtInsert_tDBOutput_2.setNull(58, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_2.setFloat(58, row6.coderegionexecution);
							}

							if (row6.codepostaletablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(59, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(59, row6.codepostaletablissement);
							}

							if (row6.natureobjetmarche == null) {
								pstmtInsert_tDBOutput_2.setNull(60, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(60, row6.natureobjetmarche);
							}

							if (row6.dureemoiscalculee == null) {
								pstmtInsert_tDBOutput_2.setNull(61, java.sql.Types.FLOAT);
							} else {
								pstmtInsert_tDBOutput_2.setFloat(61, row6.dureemoiscalculee);
							}

							if (row6.codetypeetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(62, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(62, row6.codetypeetablissement);
							}

							if (row6.geoloccommuneacheteur___ == null) {
								pstmtInsert_tDBOutput_2.setNull(63, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(63, row6.geoloccommuneacheteur___);
							}

							if (row6.codecpv == null) {
								pstmtInsert_tDBOutput_2.setNull(64, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(64, row6.codecpv);
							}

							if (row6.anneenotification == null) {
								pstmtInsert_tDBOutput_2.setNull(65, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(65, row6.anneenotification);
							}

							if (row6.libelleregionexecution == null) {
								pstmtInsert_tDBOutput_2.setNull(66, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(66, row6.libelleregionexecution);
							}

							if (row6.procedure == null) {
								pstmtInsert_tDBOutput_2.setNull(67, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(67, row6.procedure);
							}

							if (row6.libellearrondissementetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(68, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(68, row6.libellearrondissementetablissement);
							}

							if (row6.codearrondissementetablissement == null) {
								pstmtInsert_tDBOutput_2.setNull(69, java.sql.Types.VARCHAR);
							} else {
								pstmtInsert_tDBOutput_2.setString(69, row6.codearrondissementetablissement);
							}

							try {

								int processedCount_tDBOutput_2 = pstmtInsert_tDBOutput_2.executeUpdate();
								insertedCount_tDBOutput_2 += processedCount_tDBOutput_2;
								rowsToCommitCount_tDBOutput_2 += processedCount_tDBOutput_2;
								nb_line_tDBOutput_2++;

							} catch (java.lang.Exception e) {
								globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

								whetherReject_tDBOutput_2 = true;
								nb_line_tDBOutput_2++;
								System.err.print(e.getMessage());
							}
						}

						tos_count_tDBOutput_2++;

						/**
						 * [tDBOutput_2 main ] stop
						 */

						/**
						 * [tDBOutput_2 process_data_begin ] start
						 */

						currentComponent = "tDBOutput_2";

						/**
						 * [tDBOutput_2 process_data_begin ] stop
						 */

						/**
						 * [tDBOutput_2 process_data_end ] start
						 */

						currentComponent = "tDBOutput_2";

						/**
						 * [tDBOutput_2 process_data_end ] stop
						 */

						/**
						 * [tWarn_3 process_data_end ] start
						 */

						currentComponent = "tWarn_3";

						/**
						 * [tWarn_3 process_data_end ] stop
						 */

					} // End of branch "row1"

					/**
					 * [tFileInputJSON_3 process_data_end ] start
					 */

					currentComponent = "tFileInputJSON_3";

					/**
					 * [tFileInputJSON_3 process_data_end ] stop
					 */

					/**
					 * [tFileInputJSON_3 end ] start
					 */

					currentComponent = "tFileInputJSON_3";

				}
				globalMap.put("tFileInputJSON_3_NB_LINE", nb_line_tFileInputJSON_3);

				ok_Hash.put("tFileInputJSON_3", true);
				end_Hash.put("tFileInputJSON_3", System.currentTimeMillis());

				/**
				 * [tFileInputJSON_3 end ] stop
				 */

				/**
				 * [tWarn_3 end ] start
				 */

				currentComponent = "tWarn_3";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tWarn_3", true);
				end_Hash.put("tWarn_3", System.currentTimeMillis());

				/**
				 * [tWarn_3 end ] stop
				 */

				/**
				 * [tDBOutput_2 end ] start
				 */

				currentComponent = "tDBOutput_2";

				if (pstmtUpdate_tDBOutput_2 != null) {
					pstmtUpdate_tDBOutput_2.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_2");
				}
				if (pstmtInsert_tDBOutput_2 != null) {
					pstmtInsert_tDBOutput_2.close();
					resourceMap.remove("pstmtInsert_tDBOutput_2");
				}
				if (pstmt_tDBOutput_2 != null) {
					pstmt_tDBOutput_2.close();
					resourceMap.remove("pstmt_tDBOutput_2");
				}
				resourceMap.put("statementClosed_tDBOutput_2", true);

				nb_line_deleted_tDBOutput_2 = nb_line_deleted_tDBOutput_2 + deletedCount_tDBOutput_2;
				nb_line_update_tDBOutput_2 = nb_line_update_tDBOutput_2 + updatedCount_tDBOutput_2;
				nb_line_inserted_tDBOutput_2 = nb_line_inserted_tDBOutput_2 + insertedCount_tDBOutput_2;
				nb_line_rejected_tDBOutput_2 = nb_line_rejected_tDBOutput_2 + rejectedCount_tDBOutput_2;

				globalMap.put("tDBOutput_2_NB_LINE", nb_line_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_UPDATED", nb_line_update_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_DELETED", nb_line_deleted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputJSON_3:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
			}

			tDBCommit_2Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputJSON_3 finally ] start
				 */

				currentComponent = "tFileInputJSON_3";

				/**
				 * [tFileInputJSON_3 finally ] stop
				 */

				/**
				 * [tWarn_3 finally ] start
				 */

				currentComponent = "tWarn_3";

				/**
				 * [tWarn_3 finally ] stop
				 */

				/**
				 * [tDBOutput_2 finally ] start
				 */

				currentComponent = "tDBOutput_2";

				if (resourceMap.get("statementClosed_tDBOutput_2") == null) {
					java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_2 = null;
					if ((pstmtUpdateToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmtUpdate_tDBOutput_2")) != null) {
						pstmtUpdateToClose_tDBOutput_2.close();
					}
					java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_2 = null;
					if ((pstmtInsertToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmtInsert_tDBOutput_2")) != null) {
						pstmtInsertToClose_tDBOutput_2.close();
					}
					java.sql.PreparedStatement pstmtToClose_tDBOutput_2 = null;
					if ((pstmtToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_tDBOutput_2")) != null) {
						pstmtToClose_tDBOutput_2.close();
					}
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputJSON_3_SUBPROCESS_STATE", 1);
	}

	public void tDBCommit_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBCommit_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tDBCommit_2 begin ] start
				 */

				ok_Hash.put("tDBCommit_2", false);
				start_Hash.put("tDBCommit_2", System.currentTimeMillis());

				currentComponent = "tDBCommit_2";

				int tos_count_tDBCommit_2 = 0;

				/**
				 * [tDBCommit_2 begin ] stop
				 */

				/**
				 * [tDBCommit_2 main ] start
				 */

				currentComponent = "tDBCommit_2";

				java.sql.Connection conn_tDBCommit_2 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");
				if (conn_tDBCommit_2 != null && !conn_tDBCommit_2.isClosed()) {

					conn_tDBCommit_2.commit();

				}

				tos_count_tDBCommit_2++;

				/**
				 * [tDBCommit_2 main ] stop
				 */

				/**
				 * [tDBCommit_2 process_data_begin ] start
				 */

				currentComponent = "tDBCommit_2";

				/**
				 * [tDBCommit_2 process_data_begin ] stop
				 */

				/**
				 * [tDBCommit_2 process_data_end ] start
				 */

				currentComponent = "tDBCommit_2";

				/**
				 * [tDBCommit_2 process_data_end ] stop
				 */

				/**
				 * [tDBCommit_2 end ] start
				 */

				currentComponent = "tDBCommit_2";

				ok_Hash.put("tDBCommit_2", true);
				end_Hash.put("tDBCommit_2", System.currentTimeMillis());

				/**
				 * [tDBCommit_2 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBCommit_2 finally ] start
				 */

				currentComponent = "tDBCommit_2";

				/**
				 * [tDBCommit_2 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBCommit_2_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final public_market_extract public_market_extractClass = new public_market_extract();

		int exitCode = public_market_extractClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = public_market_extract.class.getClassLoader()
					.getResourceAsStream("cityviz/public_market_extract_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = public_market_extract.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
					context.setContextType("cityviz_staging_AdditionalParams", "id_String");
					if (context.getStringValue("cityviz_staging_AdditionalParams") == null) {
						context.cityviz_staging_AdditionalParams = null;
					} else {
						context.cityviz_staging_AdditionalParams = (String) context
								.getProperty("cityviz_staging_AdditionalParams");
					}
					context.setContextType("cityviz_staging_Database", "id_String");
					if (context.getStringValue("cityviz_staging_Database") == null) {
						context.cityviz_staging_Database = null;
					} else {
						context.cityviz_staging_Database = (String) context.getProperty("cityviz_staging_Database");
					}
					context.setContextType("cityviz_staging_Login", "id_String");
					if (context.getStringValue("cityviz_staging_Login") == null) {
						context.cityviz_staging_Login = null;
					} else {
						context.cityviz_staging_Login = (String) context.getProperty("cityviz_staging_Login");
					}
					context.setContextType("cityviz_staging_Password", "id_Password");
					if (context.getStringValue("cityviz_staging_Password") == null) {
						context.cityviz_staging_Password = null;
					} else {
						String pwd_cityviz_staging_Password_value = context.getProperty("cityviz_staging_Password");
						context.cityviz_staging_Password = null;
						if (pwd_cityviz_staging_Password_value != null) {
							if (context_param.containsKey("cityviz_staging_Password")) {// no need to decrypt if it come
																						// from program argument or
																						// parent job runtime
								context.cityviz_staging_Password = pwd_cityviz_staging_Password_value;
							} else if (!pwd_cityviz_staging_Password_value.isEmpty()) {
								try {
									context.cityviz_staging_Password = routines.system.PasswordEncryptUtil
											.decryptPassword(pwd_cityviz_staging_Password_value);
									context.put("cityviz_staging_Password", context.cityviz_staging_Password);
								} catch (java.lang.RuntimeException e) {
									// do nothing
								}
							}
						}
					}
					context.setContextType("cityviz_staging_Port", "id_String");
					if (context.getStringValue("cityviz_staging_Port") == null) {
						context.cityviz_staging_Port = null;
					} else {
						context.cityviz_staging_Port = (String) context.getProperty("cityviz_staging_Port");
					}
					context.setContextType("cityviz_staging_Schema", "id_String");
					if (context.getStringValue("cityviz_staging_Schema") == null) {
						context.cityviz_staging_Schema = null;
					} else {
						context.cityviz_staging_Schema = (String) context.getProperty("cityviz_staging_Schema");
					}
					context.setContextType("cityviz_staging_Server", "id_String");
					if (context.getStringValue("cityviz_staging_Server") == null) {
						context.cityviz_staging_Server = null;
					} else {
						context.cityviz_staging_Server = (String) context.getProperty("cityviz_staging_Server");
					}
					context.setContextType("api_result_filename", "id_String");
					if (context.getStringValue("api_result_filename") == null) {
						context.api_result_filename = null;
					} else {
						context.api_result_filename = (String) context.getProperty("api_result_filename");
					}
					context.setContextType("data_storage_path", "id_String");
					if (context.getStringValue("data_storage_path") == null) {
						context.data_storage_path = null;
					} else {
						context.data_storage_path = (String) context.getProperty("data_storage_path");
					}
					context.setContextType("param_file_path", "id_String");
					if (context.getStringValue("param_file_path") == null) {
						context.param_file_path = null;
					} else {
						context.param_file_path = (String) context.getProperty("param_file_path");
					}
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("cityviz_staging_AdditionalParams")) {
				context.cityviz_staging_AdditionalParams = (String) parentContextMap
						.get("cityviz_staging_AdditionalParams");
			}
			if (parentContextMap.containsKey("cityviz_staging_Database")) {
				context.cityviz_staging_Database = (String) parentContextMap.get("cityviz_staging_Database");
			}
			if (parentContextMap.containsKey("cityviz_staging_Login")) {
				context.cityviz_staging_Login = (String) parentContextMap.get("cityviz_staging_Login");
			}
			if (parentContextMap.containsKey("cityviz_staging_Password")) {
				context.cityviz_staging_Password = (java.lang.String) parentContextMap.get("cityviz_staging_Password");
			}
			if (parentContextMap.containsKey("cityviz_staging_Port")) {
				context.cityviz_staging_Port = (String) parentContextMap.get("cityviz_staging_Port");
			}
			if (parentContextMap.containsKey("cityviz_staging_Schema")) {
				context.cityviz_staging_Schema = (String) parentContextMap.get("cityviz_staging_Schema");
			}
			if (parentContextMap.containsKey("cityviz_staging_Server")) {
				context.cityviz_staging_Server = (String) parentContextMap.get("cityviz_staging_Server");
			}
			if (parentContextMap.containsKey("api_result_filename")) {
				context.api_result_filename = (String) parentContextMap.get("api_result_filename");
			}
			if (parentContextMap.containsKey("data_storage_path")) {
				context.data_storage_path = (String) parentContextMap.get("data_storage_path");
			}
			if (parentContextMap.containsKey("param_file_path")) {
				context.param_file_path = (String) parentContextMap.get("param_file_path");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		parametersToEncrypt.add("cityviz_staging_Password");
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tDBConnection_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBConnection_1) {
			globalMap.put("tDBConnection_1_SUBPROCESS_STATE", -1);

			e_tDBConnection_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : public_market_extract");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {
		closeSqlDbConnections();

	}

	private void closeSqlDbConnections() {
		try {
			Object obj_conn;
			obj_conn = globalMap.remove("conn_tDBConnection_1");
			if (null != obj_conn) {
				((java.sql.Connection) obj_conn).close();
			}
		} catch (java.lang.Exception e) {
		}
	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();
		connections.put("conn_tDBConnection_1", globalMap.get("conn_tDBConnection_1"));

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 279915 characters generated by Talend Open Studio for Data Integration on the
 * 6 mars 2022 à 12:55:33 CET
 ************************************************************************************************/