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

package cityviz.daily_loading_staging_1_0;

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
import routines.DataProcessing;
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
 * Job: daily_loading_staging Purpose: Chatgement quotidien de tous les nouveaux
 * marchés dans notre système<br>
 * Description: 1.0 : Charge les données historique d'open-data (environ 350k
 * records) dans la base de staging. Verif des doublons incluses <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class daily_loading_staging implements TalendJob {

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

			if (cityviz_dwh_AdditionalParams != null) {

				this.setProperty("cityviz_dwh_AdditionalParams", cityviz_dwh_AdditionalParams.toString());

			}

			if (cityviz_dwh_Database != null) {

				this.setProperty("cityviz_dwh_Database", cityviz_dwh_Database.toString());

			}

			if (cityviz_dwh_Login != null) {

				this.setProperty("cityviz_dwh_Login", cityviz_dwh_Login.toString());

			}

			if (cityviz_dwh_Password != null) {

				this.setProperty("cityviz_dwh_Password", cityviz_dwh_Password.toString());

			}

			if (cityviz_dwh_Port != null) {

				this.setProperty("cityviz_dwh_Port", cityviz_dwh_Port.toString());

			}

			if (cityviz_dwh_Schema != null) {

				this.setProperty("cityviz_dwh_Schema", cityviz_dwh_Schema.toString());

			}

			if (cityviz_dwh_Server != null) {

				this.setProperty("cityviz_dwh_Server", cityviz_dwh_Server.toString());

			}

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

			if (logs_path != null) {

				this.setProperty("logs_path", logs_path.toString());

			}

			if (param_file_path != null) {

				this.setProperty("param_file_path", param_file_path.toString());

			}

			if (start_value != null) {

				this.setProperty("start_value", start_value.toString());

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

		public String cityviz_dwh_AdditionalParams;

		public String getCityviz_dwh_AdditionalParams() {
			return this.cityviz_dwh_AdditionalParams;
		}

		public String cityviz_dwh_Database;

		public String getCityviz_dwh_Database() {
			return this.cityviz_dwh_Database;
		}

		public String cityviz_dwh_Login;

		public String getCityviz_dwh_Login() {
			return this.cityviz_dwh_Login;
		}

		public java.lang.String cityviz_dwh_Password;

		public java.lang.String getCityviz_dwh_Password() {
			return this.cityviz_dwh_Password;
		}

		public String cityviz_dwh_Port;

		public String getCityviz_dwh_Port() {
			return this.cityviz_dwh_Port;
		}

		public String cityviz_dwh_Schema;

		public String getCityviz_dwh_Schema() {
			return this.cityviz_dwh_Schema;
		}

		public String cityviz_dwh_Server;

		public String getCityviz_dwh_Server() {
			return this.cityviz_dwh_Server;
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

		public String logs_path;

		public String getLogs_path() {
			return this.logs_path;
		}

		public String param_file_path;

		public String getParam_file_path() {
			return this.param_file_path;
		}

		public Integer start_value;

		public Integer getStart_value() {
			return this.start_value;
		}
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "1.0";
	private final String jobName = "daily_loading_staging";
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

	LogCatcherUtils tLogCatcher_1 = new LogCatcherUtils();

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
					daily_loading_staging.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(daily_loading_staging.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
						tLogCatcher_1.addMessage("Java Exception", currentComponent, 6,
								e.getClass().getName() + ":" + e.getMessage(), 1);
						tLogCatcher_1Process(globalMap);
					}
				} catch (TalendException e) {
					// do nothing

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

	public void tDBConnection_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBConnection_2_onSubJobError(exception, errorComponent, globalMap);
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

	public void tFileFetch_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputJSON_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tWarn_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tWarn_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogCatcher_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tLogCatcher_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tLogCatcher_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tLogCatcher_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row12_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBConnection_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBConnection_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputJSON_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tLogCatcher_1_onSubJobError(Exception exception, String errorComponent,
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
				tDBConnection_2Process(globalMap);

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

	public void tDBConnection_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBConnection_2_SUBPROCESS_STATE", 0);

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
				 * [tDBConnection_2 begin ] start
				 */

				ok_Hash.put("tDBConnection_2", false);
				start_Hash.put("tDBConnection_2", System.currentTimeMillis());

				currentComponent = "tDBConnection_2";

				int tos_count_tDBConnection_2 = 0;

				String dbProperties_tDBConnection_2 = context.cityviz_dwh_AdditionalParams;
				String url_tDBConnection_2 = "jdbc:postgresql://" + context.cityviz_dwh_Server + ":"
						+ context.cityviz_dwh_Port + "/" + context.cityviz_dwh_Database;

				if (dbProperties_tDBConnection_2 != null && !"".equals(dbProperties_tDBConnection_2.trim())) {
					url_tDBConnection_2 = url_tDBConnection_2 + "?" + dbProperties_tDBConnection_2;
				}
				String dbUser_tDBConnection_2 = context.cityviz_dwh_Login;

				final String decryptedPassword_tDBConnection_2 = context.cityviz_dwh_Password;
				String dbPwd_tDBConnection_2 = decryptedPassword_tDBConnection_2;

				java.sql.Connection conn_tDBConnection_2 = null;

				java.util.Enumeration<java.sql.Driver> drivers_tDBConnection_2 = java.sql.DriverManager.getDrivers();
				java.util.Set<String> redShiftDriverNames_tDBConnection_2 = new java.util.HashSet<String>(
						java.util.Arrays.asList("com.amazon.redshift.jdbc.Driver", "com.amazon.redshift.jdbc41.Driver",
								"com.amazon.redshift.jdbc42.Driver"));
				while (drivers_tDBConnection_2.hasMoreElements()) {
					java.sql.Driver d_tDBConnection_2 = drivers_tDBConnection_2.nextElement();
					if (redShiftDriverNames_tDBConnection_2.contains(d_tDBConnection_2.getClass().getName())) {
						try {
							java.sql.DriverManager.deregisterDriver(d_tDBConnection_2);
							java.sql.DriverManager.registerDriver(d_tDBConnection_2);
						} catch (java.lang.Exception e_tDBConnection_2) {
							globalMap.put("tDBConnection_2_ERROR_MESSAGE", e_tDBConnection_2.getMessage());
							// do nothing
						}
					}
				}
				String driverClass_tDBConnection_2 = "org.postgresql.Driver";
				java.lang.Class jdbcclazz_tDBConnection_2 = java.lang.Class.forName(driverClass_tDBConnection_2);
				globalMap.put("driverClass_tDBConnection_2", driverClass_tDBConnection_2);

				conn_tDBConnection_2 = java.sql.DriverManager.getConnection(url_tDBConnection_2, dbUser_tDBConnection_2,
						dbPwd_tDBConnection_2);

				globalMap.put("conn_tDBConnection_2", conn_tDBConnection_2);
				if (null != conn_tDBConnection_2) {

					conn_tDBConnection_2.setAutoCommit(false);
				}

				globalMap.put("schema_" + "tDBConnection_2", context.cityviz_dwh_Schema);

				/**
				 * [tDBConnection_2 begin ] stop
				 */

				/**
				 * [tDBConnection_2 main ] start
				 */

				currentComponent = "tDBConnection_2";

				tos_count_tDBConnection_2++;

				/**
				 * [tDBConnection_2 main ] stop
				 */

				/**
				 * [tDBConnection_2 process_data_begin ] start
				 */

				currentComponent = "tDBConnection_2";

				/**
				 * [tDBConnection_2 process_data_begin ] stop
				 */

				/**
				 * [tDBConnection_2 process_data_end ] start
				 */

				currentComponent = "tDBConnection_2";

				/**
				 * [tDBConnection_2 process_data_end ] stop
				 */

				/**
				 * [tDBConnection_2 end ] start
				 */

				currentComponent = "tDBConnection_2";

				ok_Hash.put("tDBConnection_2", true);
				end_Hash.put("tDBConnection_2", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk3", 0, "ok");
				}
				tFileInputDelimited_1Process(globalMap);

				/**
				 * [tDBConnection_2 end ] stop
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
				 * [tDBConnection_2 finally ] start
				 */

				currentComponent = "tDBConnection_2";

				/**
				 * [tDBConnection_2 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBConnection_2_SUBPROCESS_STATE", 1);
	}

	public static class API_URLStruct implements routines.system.IPersistableRow<API_URLStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_daily_loading_staging = new byte[0];
		static byte[] commonByteArray_CITYVIZ_daily_loading_staging = new byte[0];

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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.URL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

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

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_daily_loading_staging = new byte[0];
		static byte[] commonByteArray_CITYVIZ_daily_loading_staging = new byte[0];

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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

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

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

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
		public int compareTo(row9Struct other) {

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

				row9Struct row9 = new row9Struct();
				API_URLStruct API_URL = new API_URLStruct();

				/**
				 * [tFileFetch_2 begin ] start
				 */

				ok_Hash.put("tFileFetch_2", false);
				start_Hash.put("tFileFetch_2", System.currentTimeMillis());

				currentComponent = "tFileFetch_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "API_URL");
				}

				int tos_count_tFileFetch_2 = 0;

				/**
				 * [tFileFetch_2 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row9");
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

						row9 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row9 = new row9Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							columnIndexWithD_tFileInputDelimited_1 = 0;

							row9.dataset = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 1;

							row9.q = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row9.rows = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row9.start = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row9.FR = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row9.geo_long = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							row9.geo_lat = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 7;

							row9.distance = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row9 = null;

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
// Start of branch "row9"
						if (row9 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row9"

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
								Var.urlFromParams = API.dailyAPI_Request(row9.dataset, row9.q, row9.rows,
										context.start_value, row9.FR, row9.geo_long, row9.geo_lat, row9.distance);// ###############################
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
								 * [tFileFetch_2 main ] start
								 */

								currentComponent = "tFileFetch_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "API_URL"

									);
								}

								java.io.InputStream retIS_tFileFetch_2 = null;

								java.net.URI uriToFetch_tFileFetch_2 = null;
								try {
									uriToFetch_tFileFetch_2 = new java.net.URI(API_URL.URL);
								} catch (Exception e) {
									globalMap.put("tFileFetch_2_ERROR_MESSAGE", e.getMessage());
									System.err.println(
											"URI is not correct or not encoded, please input a valid one or use 'Encode URI' option");
								}

								class SocketFactory_tFileFetch_2
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
													globalMap.put("tFileFetch_2_ERROR_MESSAGE", e.getMessage());
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
													globalMap.put("tFileFetch_2_ERROR_MESSAGE", e.getMessage());
													e.printStackTrace();
													trustManagers = new javax.net.ssl.TrustManager[] {};

												}
											}
											sslcontext.init(keyManagers, trustManagers,
													new java.security.SecureRandom());
										} catch (java.security.NoSuchAlgorithmException e) {
											globalMap.put("tFileFetch_2_ERROR_MESSAGE", e.getMessage());

											e.printStackTrace();
										} catch (java.security.KeyManagementException e) {
											globalMap.put("tFileFetch_2_ERROR_MESSAGE", e.getMessage());

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

								org.apache.commons.httpclient.protocol.Protocol myhttps_tFileFetch_2 = new org.apache.commons.httpclient.protocol.Protocol(
										"https", new SocketFactory_tFileFetch_2(), 443);
								org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",
										myhttps_tFileFetch_2);
								org.apache.commons.httpclient.HttpClient client_tFileFetch_2 = new org.apache.commons.httpclient.HttpClient();

								client_tFileFetch_2.getHttpConnectionManager().getParams().setConnectionTimeout(30000);

								client_tFileFetch_2.getParams()
										.setCookiePolicy(org.apache.commons.httpclient.cookie.CookiePolicy.DEFAULT);

								org.apache.commons.httpclient.HttpMethod method_tFileFetch_2 = new org.apache.commons.httpclient.methods.GetMethod(
										uriToFetch_tFileFetch_2.toString());

								boolean isContinue_tFileFetch_2 = true;
								int status_tFileFetch_2;
								String finalURL_tFileFetch_2 = uriToFetch_tFileFetch_2.toString();

								try { // B_01

									status_tFileFetch_2 = client_tFileFetch_2.executeMethod(method_tFileFetch_2);

									if (!(status_tFileFetch_2 >= 200 && status_tFileFetch_2 < 300)) {// Status code 2XX
																										// indicates
																										// success
										throw new java.lang.Exception(
												"Method failed: " + method_tFileFetch_2.getStatusLine());
									}

								} catch (java.lang.Exception e) {
									globalMap.put("tFileFetch_2_ERROR_MESSAGE", e.getMessage());

									throw (e);

								}

								if (isContinue_tFileFetch_2) {

									java.io.InputStream in_tFileFetch_2 = method_tFileFetch_2.getResponseBodyAsStream();
									String sDir_tFileFetch_2 = (context.data_storage_path).trim();
									String fileName_tFileFetch_2 = (context.api_result_filename).trim();
									// open directory
									java.net.URL url_tFileFetch_2 = new java.net.URL(finalURL_tFileFetch_2);
									String sURIPath_tFileFetch_2 = "";
									int iLastSlashIndex_tFileFetch_2 = 0;
									sURIPath_tFileFetch_2 = url_tFileFetch_2.getFile();
									iLastSlashIndex_tFileFetch_2 = sURIPath_tFileFetch_2.lastIndexOf("/");

									// if not input file name, get the name from URI
									if ("".equals(fileName_tFileFetch_2)) {
										if (iLastSlashIndex_tFileFetch_2 > 0
												&& (!sURIPath_tFileFetch_2.endsWith("/"))) {
											fileName_tFileFetch_2 = sURIPath_tFileFetch_2
													.substring(iLastSlashIndex_tFileFetch_2 + 1);
										} else {
											fileName_tFileFetch_2 = "defaultfilename.txt";
										}
									}
									java.io.File dir_tFileFetch_2 = new java.io.File(sDir_tFileFetch_2);

									// pretreatment
									try {
										java.io.File test_file_tFileFetch_2 = new java.io.File(dir_tFileFetch_2,
												fileName_tFileFetch_2);
										test_file_tFileFetch_2.getParentFile().mkdirs();

										if (test_file_tFileFetch_2.createNewFile()) {
											test_file_tFileFetch_2.delete();
										}
									} catch (java.lang.Exception e) {
										globalMap.put("tFileFetch_2_ERROR_MESSAGE", e.getMessage());

										fileName_tFileFetch_2 = "defaultfilename.txt";
									}
									java.io.File file_tFileFetch_2 = new java.io.File(dir_tFileFetch_2,
											fileName_tFileFetch_2);
									file_tFileFetch_2.getParentFile().mkdirs();
									java.io.FileOutputStream out_tFileFetch_2 = new java.io.FileOutputStream(
											file_tFileFetch_2);
									byte[] buffer_tFileFetch_2 = new byte[1024];
									int count_tFileFetch_2 = 0;

									while ((count_tFileFetch_2 = in_tFileFetch_2.read(buffer_tFileFetch_2)) > 0) {
										out_tFileFetch_2.write(buffer_tFileFetch_2, 0, count_tFileFetch_2);
									}
									// close opened object
									in_tFileFetch_2.close();
									out_tFileFetch_2.close();

									method_tFileFetch_2.releaseConnection();

								} // B_01
								globalMap.put("tFileFetch_2_INPUT_STREAM", retIS_tFileFetch_2);

								tos_count_tFileFetch_2++;

								/**
								 * [tFileFetch_2 main ] stop
								 */

								/**
								 * [tFileFetch_2 process_data_begin ] start
								 */

								currentComponent = "tFileFetch_2";

								/**
								 * [tFileFetch_2 process_data_begin ] stop
								 */

								/**
								 * [tFileFetch_2 process_data_end ] start
								 */

								currentComponent = "tFileFetch_2";

								/**
								 * [tFileFetch_2 process_data_end ] stop
								 */

							} // End of branch "API_URL"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row9"

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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row9");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tFileFetch_2 end ] start
				 */

				currentComponent = "tFileFetch_2";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "API_URL");
				}

				ok_Hash.put("tFileFetch_2", true);
				end_Hash.put("tFileFetch_2", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk4", 0, "ok");
				}
				tFileInputJSON_2Process(globalMap);

				/**
				 * [tFileFetch_2 end ] stop
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
				 * [tFileFetch_2 finally ] start
				 */

				currentComponent = "tFileFetch_2";

				/**
				 * [tFileFetch_2 finally ] stop
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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_daily_loading_staging = new byte[0];
		static byte[] commonByteArray_CITYVIZ_daily_loading_staging = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String recordid;

		public String getRecordid() {
			return this.recordid;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
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
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.idmarche = this.idmarche;

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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.idmarche = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.idmarche = readInteger(dis);

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

				writeString(this.denominationsocialeetablissement, dos);

				// Integer

				writeInteger(this.idmarche, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.recordid, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// Integer

				writeInteger(this.idmarche, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("recordid=" + recordid);
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",idmarche=" + String.valueOf(idmarche));
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

	public static class to_stgStruct implements routines.system.IPersistableRow<to_stgStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_daily_loading_staging = new byte[0];
		static byte[] commonByteArray_CITYVIZ_daily_loading_staging = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String recordid;

		public String getRecordid() {
			return this.recordid;
		}

		public java.util.Date datepublicationdonnees;

		public java.util.Date getDatepublicationdonnees() {
			return this.datepublicationdonnees;
		}

		public String procedure;

		public String getProcedure() {
			return this.procedure;
		}

		public String nature;

		public String getNature() {
			return this.nature;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		public java.util.Date datenotification;

		public java.util.Date getDatenotification() {
			return this.datenotification;
		}

		public Float montant;

		public Float getMontant() {
			return this.montant;
		}

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public String objetmarche;

		public String getObjetmarche() {
			return this.objetmarche;
		}

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public String codecommuneacheteur;

		public String getCodecommuneacheteur() {
			return this.codecommuneacheteur;
		}

		public String source;

		public String getSource() {
			return this.source;
		}

		public String lieuexecutionnom;

		public String getLieuexecutionnom() {
			return this.lieuexecutionnom;
		}

		public Integer codearrondissementetablissement;

		public Integer getCodearrondissementetablissement() {
			return this.codearrondissementetablissement;
		}

		public Integer libellearrondissementetablissement;

		public Integer getLibellearrondissementetablissement() {
			return this.libellearrondissementetablissement;
		}

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String codetypeetablissement;

		public String getCodetypeetablissement() {
			return this.codetypeetablissement;
		}

		public String nicetablissement;

		public String getNicetablissement() {
			return this.nicetablissement;
		}

		public String lieuexecutiontypecode;

		public String getLieuexecutiontypecode() {
			return this.lieuexecutiontypecode;
		}

		public String libelleregionetablissement;

		public String getLibelleregionetablissement() {
			return this.libelleregionetablissement;
		}

		public String categorieetablissement;

		public String getCategorieetablissement() {
			return this.categorieetablissement;
		}

		public String nomacheteur;

		public String getNomacheteur() {
			return this.nomacheteur;
		}

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public String libellecommuneacheteur;

		public String getLibellecommuneacheteur() {
			return this.libellecommuneacheteur;
		}

		public String libelledepartementetablissement;

		public String getLibelledepartementetablissement() {
			return this.libelledepartementetablissement;
		}

		public String dureemoisestimee;

		public String getDureemoisestimee() {
			return this.dureemoisestimee;
		}

		public String libelleregionacheteur;

		public String getLibelleregionacheteur() {
			return this.libelleregionacheteur;
		}

		public String coderegionacheteur;

		public String getCoderegionacheteur() {
			return this.coderegionacheteur;
		}

		public String siretetablissementvalide;

		public String getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public String codecommuneetablissement;

		public String getCodecommuneetablissement() {
			return this.codecommuneetablissement;
		}

		public String codepostalacheteur;

		public String getCodepostalacheteur() {
			return this.codepostalacheteur;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public String superficiecommuneacheteur;

		public String getSuperficiecommuneacheteur() {
			return this.superficiecommuneacheteur;
		}

		public Float populationcommuneacheteur;

		public Float getPopulationcommuneacheteur() {
			return this.populationcommuneacheteur;
		}

		public Float codedepartementexecution;

		public Float getCodedepartementexecution() {
			return this.codedepartementexecution;
		}

		public String sirenacheteurvalide;

		public String getSirenacheteurvalide() {
			return this.sirenacheteurvalide;
		}

		public Integer codearrondissementacheteur;

		public Integer getCodearrondissementacheteur() {
			return this.codearrondissementacheteur;
		}

		public String sirenetablissementvalide;

		public String getSirenetablissementvalide() {
			return this.sirenetablissementvalide;
		}

		public Float dureemoiscalculee;

		public Float getDureemoiscalculee() {
			return this.dureemoiscalculee;
		}

		public Float populationcommuneetablissement;

		public Float getPopulationcommuneetablissement() {
			return this.populationcommuneetablissement;
		}

		public Integer codecpv_division;

		public Integer getCodecpv_division() {
			return this.codecpv_division;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public Integer coderegionetablissement;

		public Integer getCoderegionetablissement() {
			return this.coderegionetablissement;
		}

		public String lieuexecutioncode;

		public String getLieuexecutioncode() {
			return this.lieuexecutioncode;
		}

		public String moisnotification;

		public String getMoisnotification() {
			return this.moisnotification;
		}

		public String codecpv_original;

		public String getCodecpv_original() {
			return this.codecpv_original;
		}

		public String typeidentifiantetablissement;

		public String getTypeidentifiantetablissement() {
			return this.typeidentifiantetablissement;
		}

		public Integer dureemois;

		public Integer getDureemois() {
			return this.dureemois;
		}

		public String libellearrondissementacheteur;

		public String getLibellearrondissementacheteur() {
			return this.libellearrondissementacheteur;
		}

		public Float nombretitulairesurmarchepresume;

		public Float getNombretitulairesurmarchepresume() {
			return this.nombretitulairesurmarchepresume;
		}

		public String adresseetablissement;

		public String getAdresseetablissement() {
			return this.adresseetablissement;
		}

		public Float superficiecommuneetablissement;

		public Float getSuperficiecommuneetablissement() {
			return this.superficiecommuneetablissement;
		}

		public Float distanceacheteuretablissement;

		public Float getDistanceacheteuretablissement() {
			return this.distanceacheteuretablissement;
		}

		public String natureobjetmarche;

		public String getNatureobjetmarche() {
			return this.natureobjetmarche;
		}

		public String formeprix;

		public String getFormeprix() {
			return this.formeprix;
		}

		public String anneenotification;

		public String getAnneenotification() {
			return this.anneenotification;
		}

		public String libelledepartementacheteur;

		public String getLibelledepartementacheteur() {
			return this.libelledepartementacheteur;
		}

		public String departementacheteur;

		public String getDepartementacheteur() {
			return this.departementacheteur;
		}

		public String codepostaletablissement;

		public String getCodepostaletablissement() {
			return this.codepostaletablissement;
		}

		public Float montantcalcule;

		public Float getMontantcalcule() {
			return this.montantcalcule;
		}

		public Float coderegionexecution;

		public Float getCoderegionexecution() {
			return this.coderegionexecution;
		}

		public String codecpv;

		public String getCodecpv() {
			return this.codecpv;
		}

		public String referencecpv;

		public String getReferencecpv() {
			return this.referencecpv;
		}

		public String libelleregionexecution;

		public String getLibelleregionexecution() {
			return this.libelleregionexecution;
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
			final to_stgStruct other = (to_stgStruct) obj;

			if (this.recordid == null) {
				if (other.recordid != null)
					return false;

			} else if (!this.recordid.equals(other.recordid))

				return false;

			return true;
		}

		public void copyDataTo(to_stgStruct other) {

			other.recordid = this.recordid;
			other.datepublicationdonnees = this.datepublicationdonnees;
			other.procedure = this.procedure;
			other.nature = this.nature;
			other.type = this.type;
			other.datenotification = this.datenotification;
			other.montant = this.montant;
			other.siretetablissement = this.siretetablissement;
			other.objetmarche = this.objetmarche;
			other.idacheteur = this.idacheteur;
			other.codecommuneacheteur = this.codecommuneacheteur;
			other.source = this.source;
			other.lieuexecutionnom = this.lieuexecutionnom;
			other.codearrondissementetablissement = this.codearrondissementetablissement;
			other.libellearrondissementetablissement = this.libellearrondissementetablissement;
			other.sirenetablissement = this.sirenetablissement;
			other.codetypeetablissement = this.codetypeetablissement;
			other.nicetablissement = this.nicetablissement;
			other.lieuexecutiontypecode = this.lieuexecutiontypecode;
			other.libelleregionetablissement = this.libelleregionetablissement;
			other.categorieetablissement = this.categorieetablissement;
			other.nomacheteur = this.nomacheteur;
			other.idmarche = this.idmarche;
			other.communeetablissement = this.communeetablissement;
			other.libellecommuneacheteur = this.libellecommuneacheteur;
			other.libelledepartementetablissement = this.libelledepartementetablissement;
			other.dureemoisestimee = this.dureemoisestimee;
			other.libelleregionacheteur = this.libelleregionacheteur;
			other.coderegionacheteur = this.coderegionacheteur;
			other.siretetablissementvalide = this.siretetablissementvalide;
			other.codecommuneetablissement = this.codecommuneetablissement;
			other.codepostalacheteur = this.codepostalacheteur;
			other.departementetablissement = this.departementetablissement;
			other.superficiecommuneacheteur = this.superficiecommuneacheteur;
			other.populationcommuneacheteur = this.populationcommuneacheteur;
			other.codedepartementexecution = this.codedepartementexecution;
			other.sirenacheteurvalide = this.sirenacheteurvalide;
			other.codearrondissementacheteur = this.codearrondissementacheteur;
			other.sirenetablissementvalide = this.sirenetablissementvalide;
			other.dureemoiscalculee = this.dureemoiscalculee;
			other.populationcommuneetablissement = this.populationcommuneetablissement;
			other.codecpv_division = this.codecpv_division;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.coderegionetablissement = this.coderegionetablissement;
			other.lieuexecutioncode = this.lieuexecutioncode;
			other.moisnotification = this.moisnotification;
			other.codecpv_original = this.codecpv_original;
			other.typeidentifiantetablissement = this.typeidentifiantetablissement;
			other.dureemois = this.dureemois;
			other.libellearrondissementacheteur = this.libellearrondissementacheteur;
			other.nombretitulairesurmarchepresume = this.nombretitulairesurmarchepresume;
			other.adresseetablissement = this.adresseetablissement;
			other.superficiecommuneetablissement = this.superficiecommuneetablissement;
			other.distanceacheteuretablissement = this.distanceacheteuretablissement;
			other.natureobjetmarche = this.natureobjetmarche;
			other.formeprix = this.formeprix;
			other.anneenotification = this.anneenotification;
			other.libelledepartementacheteur = this.libelledepartementacheteur;
			other.departementacheteur = this.departementacheteur;
			other.codepostaletablissement = this.codepostaletablissement;
			other.montantcalcule = this.montantcalcule;
			other.coderegionexecution = this.coderegionexecution;
			other.codecpv = this.codecpv;
			other.referencecpv = this.referencecpv;
			other.libelleregionexecution = this.libelleregionexecution;

		}

		public void copyKeysDataTo(to_stgStruct other) {

			other.recordid = this.recordid;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
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

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					this.datepublicationdonnees = readDate(dis);

					this.procedure = readString(dis);

					this.nature = readString(dis);

					this.type = readString(dis);

					this.datenotification = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montant = null;
					} else {
						this.montant = dis.readFloat();
					}

					this.siretetablissement = readString(dis);

					this.objetmarche = readString(dis);

					this.idacheteur = readString(dis);

					this.codecommuneacheteur = readString(dis);

					this.source = readString(dis);

					this.lieuexecutionnom = readString(dis);

					this.codearrondissementetablissement = readInteger(dis);

					this.libellearrondissementetablissement = readInteger(dis);

					this.sirenetablissement = readString(dis);

					this.codetypeetablissement = readString(dis);

					this.nicetablissement = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.libelleregionetablissement = readString(dis);

					this.categorieetablissement = readString(dis);

					this.nomacheteur = readString(dis);

					this.idmarche = readInteger(dis);

					this.communeetablissement = readString(dis);

					this.libellecommuneacheteur = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.dureemoisestimee = readString(dis);

					this.libelleregionacheteur = readString(dis);

					this.coderegionacheteur = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.codepostalacheteur = readString(dis);

					this.departementetablissement = readString(dis);

					this.superficiecommuneacheteur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneacheteur = null;
					} else {
						this.populationcommuneacheteur = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					this.sirenacheteurvalide = readString(dis);

					this.codearrondissementacheteur = readInteger(dis);

					this.sirenetablissementvalide = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.dureemoiscalculee = null;
					} else {
						this.dureemoiscalculee = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneetablissement = null;
					} else {
						this.populationcommuneetablissement = dis.readFloat();
					}

					this.codecpv_division = readInteger(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.coderegionetablissement = readInteger(dis);

					this.lieuexecutioncode = readString(dis);

					this.moisnotification = readString(dis);

					this.codecpv_original = readString(dis);

					this.typeidentifiantetablissement = readString(dis);

					this.dureemois = readInteger(dis);

					this.libellearrondissementacheteur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.nombretitulairesurmarchepresume = null;
					} else {
						this.nombretitulairesurmarchepresume = dis.readFloat();
					}

					this.adresseetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.superficiecommuneetablissement = null;
					} else {
						this.superficiecommuneetablissement = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.distanceacheteuretablissement = null;
					} else {
						this.distanceacheteuretablissement = dis.readFloat();
					}

					this.natureobjetmarche = readString(dis);

					this.formeprix = readString(dis);

					this.anneenotification = readString(dis);

					this.libelledepartementacheteur = readString(dis);

					this.departementacheteur = readString(dis);

					this.codepostaletablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montantcalcule = null;
					} else {
						this.montantcalcule = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.codecpv = readString(dis);

					this.referencecpv = readString(dis);

					this.libelleregionexecution = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					this.datepublicationdonnees = readDate(dis);

					this.procedure = readString(dis);

					this.nature = readString(dis);

					this.type = readString(dis);

					this.datenotification = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montant = null;
					} else {
						this.montant = dis.readFloat();
					}

					this.siretetablissement = readString(dis);

					this.objetmarche = readString(dis);

					this.idacheteur = readString(dis);

					this.codecommuneacheteur = readString(dis);

					this.source = readString(dis);

					this.lieuexecutionnom = readString(dis);

					this.codearrondissementetablissement = readInteger(dis);

					this.libellearrondissementetablissement = readInteger(dis);

					this.sirenetablissement = readString(dis);

					this.codetypeetablissement = readString(dis);

					this.nicetablissement = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.libelleregionetablissement = readString(dis);

					this.categorieetablissement = readString(dis);

					this.nomacheteur = readString(dis);

					this.idmarche = readInteger(dis);

					this.communeetablissement = readString(dis);

					this.libellecommuneacheteur = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.dureemoisestimee = readString(dis);

					this.libelleregionacheteur = readString(dis);

					this.coderegionacheteur = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.codepostalacheteur = readString(dis);

					this.departementetablissement = readString(dis);

					this.superficiecommuneacheteur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneacheteur = null;
					} else {
						this.populationcommuneacheteur = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					this.sirenacheteurvalide = readString(dis);

					this.codearrondissementacheteur = readInteger(dis);

					this.sirenetablissementvalide = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.dureemoiscalculee = null;
					} else {
						this.dureemoiscalculee = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneetablissement = null;
					} else {
						this.populationcommuneetablissement = dis.readFloat();
					}

					this.codecpv_division = readInteger(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.coderegionetablissement = readInteger(dis);

					this.lieuexecutioncode = readString(dis);

					this.moisnotification = readString(dis);

					this.codecpv_original = readString(dis);

					this.typeidentifiantetablissement = readString(dis);

					this.dureemois = readInteger(dis);

					this.libellearrondissementacheteur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.nombretitulairesurmarchepresume = null;
					} else {
						this.nombretitulairesurmarchepresume = dis.readFloat();
					}

					this.adresseetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.superficiecommuneetablissement = null;
					} else {
						this.superficiecommuneetablissement = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.distanceacheteuretablissement = null;
					} else {
						this.distanceacheteuretablissement = dis.readFloat();
					}

					this.natureobjetmarche = readString(dis);

					this.formeprix = readString(dis);

					this.anneenotification = readString(dis);

					this.libelledepartementacheteur = readString(dis);

					this.departementacheteur = readString(dis);

					this.codepostaletablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montantcalcule = null;
					} else {
						this.montantcalcule = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.codecpv = readString(dis);

					this.referencecpv = readString(dis);

					this.libelleregionexecution = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.recordid, dos);

				// java.util.Date

				writeDate(this.datepublicationdonnees, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.type, dos);

				// java.util.Date

				writeDate(this.datenotification, dos);

				// Float

				if (this.montant == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montant);
				}

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.codecommuneacheteur, dos);

				// String

				writeString(this.source, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// Integer

				writeInteger(this.codearrondissementetablissement, dos);

				// Integer

				writeInteger(this.libellearrondissementetablissement, dos);

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.codetypeetablissement, dos);

				// String

				writeString(this.nicetablissement, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.nomacheteur, dos);

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.libellecommuneacheteur, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.dureemoisestimee, dos);

				// String

				writeString(this.libelleregionacheteur, dos);

				// String

				writeString(this.coderegionacheteur, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.codepostalacheteur, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.superficiecommuneacheteur, dos);

				// Float

				if (this.populationcommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneacheteur);
				}

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// String

				writeString(this.sirenacheteurvalide, dos);

				// Integer

				writeInteger(this.codearrondissementacheteur, dos);

				// String

				writeString(this.sirenetablissementvalide, dos);

				// Float

				if (this.dureemoiscalculee == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dureemoiscalculee);
				}

				// Float

				if (this.populationcommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneetablissement);
				}

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// Integer

				writeInteger(this.coderegionetablissement, dos);

				// String

				writeString(this.lieuexecutioncode, dos);

				// String

				writeString(this.moisnotification, dos);

				// String

				writeString(this.codecpv_original, dos);

				// String

				writeString(this.typeidentifiantetablissement, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.libellearrondissementacheteur, dos);

				// Float

				if (this.nombretitulairesurmarchepresume == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.nombretitulairesurmarchepresume);
				}

				// String

				writeString(this.adresseetablissement, dos);

				// Float

				if (this.superficiecommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneetablissement);
				}

				// Float

				if (this.distanceacheteuretablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.distanceacheteuretablissement);
				}

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.anneenotification, dos);

				// String

				writeString(this.libelledepartementacheteur, dos);

				// String

				writeString(this.departementacheteur, dos);

				// String

				writeString(this.codepostaletablissement, dos);

				// Float

				if (this.montantcalcule == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalcule);
				}

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.referencecpv, dos);

				// String

				writeString(this.libelleregionexecution, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.recordid, dos);

				// java.util.Date

				writeDate(this.datepublicationdonnees, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.type, dos);

				// java.util.Date

				writeDate(this.datenotification, dos);

				// Float

				if (this.montant == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montant);
				}

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.codecommuneacheteur, dos);

				// String

				writeString(this.source, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// Integer

				writeInteger(this.codearrondissementetablissement, dos);

				// Integer

				writeInteger(this.libellearrondissementetablissement, dos);

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.codetypeetablissement, dos);

				// String

				writeString(this.nicetablissement, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.nomacheteur, dos);

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.libellecommuneacheteur, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.dureemoisestimee, dos);

				// String

				writeString(this.libelleregionacheteur, dos);

				// String

				writeString(this.coderegionacheteur, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.codepostalacheteur, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.superficiecommuneacheteur, dos);

				// Float

				if (this.populationcommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneacheteur);
				}

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// String

				writeString(this.sirenacheteurvalide, dos);

				// Integer

				writeInteger(this.codearrondissementacheteur, dos);

				// String

				writeString(this.sirenetablissementvalide, dos);

				// Float

				if (this.dureemoiscalculee == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dureemoiscalculee);
				}

				// Float

				if (this.populationcommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneetablissement);
				}

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// Integer

				writeInteger(this.coderegionetablissement, dos);

				// String

				writeString(this.lieuexecutioncode, dos);

				// String

				writeString(this.moisnotification, dos);

				// String

				writeString(this.codecpv_original, dos);

				// String

				writeString(this.typeidentifiantetablissement, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.libellearrondissementacheteur, dos);

				// Float

				if (this.nombretitulairesurmarchepresume == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.nombretitulairesurmarchepresume);
				}

				// String

				writeString(this.adresseetablissement, dos);

				// Float

				if (this.superficiecommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneetablissement);
				}

				// Float

				if (this.distanceacheteuretablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.distanceacheteuretablissement);
				}

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.anneenotification, dos);

				// String

				writeString(this.libelledepartementacheteur, dos);

				// String

				writeString(this.departementacheteur, dos);

				// String

				writeString(this.codepostaletablissement, dos);

				// Float

				if (this.montantcalcule == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalcule);
				}

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.referencecpv, dos);

				// String

				writeString(this.libelleregionexecution, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("recordid=" + recordid);
			sb.append(",datepublicationdonnees=" + String.valueOf(datepublicationdonnees));
			sb.append(",procedure=" + procedure);
			sb.append(",nature=" + nature);
			sb.append(",type=" + type);
			sb.append(",datenotification=" + String.valueOf(datenotification));
			sb.append(",montant=" + String.valueOf(montant));
			sb.append(",siretetablissement=" + siretetablissement);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",idacheteur=" + idacheteur);
			sb.append(",codecommuneacheteur=" + codecommuneacheteur);
			sb.append(",source=" + source);
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",codearrondissementetablissement=" + String.valueOf(codearrondissementetablissement));
			sb.append(",libellearrondissementetablissement=" + String.valueOf(libellearrondissementetablissement));
			sb.append(",sirenetablissement=" + sirenetablissement);
			sb.append(",codetypeetablissement=" + codetypeetablissement);
			sb.append(",nicetablissement=" + nicetablissement);
			sb.append(",lieuexecutiontypecode=" + lieuexecutiontypecode);
			sb.append(",libelleregionetablissement=" + libelleregionetablissement);
			sb.append(",categorieetablissement=" + categorieetablissement);
			sb.append(",nomacheteur=" + nomacheteur);
			sb.append(",idmarche=" + String.valueOf(idmarche));
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",libellecommuneacheteur=" + libellecommuneacheteur);
			sb.append(",libelledepartementetablissement=" + libelledepartementetablissement);
			sb.append(",dureemoisestimee=" + dureemoisestimee);
			sb.append(",libelleregionacheteur=" + libelleregionacheteur);
			sb.append(",coderegionacheteur=" + coderegionacheteur);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",codecommuneetablissement=" + codecommuneetablissement);
			sb.append(",codepostalacheteur=" + codepostalacheteur);
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",superficiecommuneacheteur=" + superficiecommuneacheteur);
			sb.append(",populationcommuneacheteur=" + String.valueOf(populationcommuneacheteur));
			sb.append(",codedepartementexecution=" + String.valueOf(codedepartementexecution));
			sb.append(",sirenacheteurvalide=" + sirenacheteurvalide);
			sb.append(",codearrondissementacheteur=" + String.valueOf(codearrondissementacheteur));
			sb.append(",sirenetablissementvalide=" + sirenetablissementvalide);
			sb.append(",dureemoiscalculee=" + String.valueOf(dureemoiscalculee));
			sb.append(",populationcommuneetablissement=" + String.valueOf(populationcommuneetablissement));
			sb.append(",codecpv_division=" + String.valueOf(codecpv_division));
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",coderegionetablissement=" + String.valueOf(coderegionetablissement));
			sb.append(",lieuexecutioncode=" + lieuexecutioncode);
			sb.append(",moisnotification=" + moisnotification);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",typeidentifiantetablissement=" + typeidentifiantetablissement);
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",libellearrondissementacheteur=" + libellearrondissementacheteur);
			sb.append(",nombretitulairesurmarchepresume=" + String.valueOf(nombretitulairesurmarchepresume));
			sb.append(",adresseetablissement=" + adresseetablissement);
			sb.append(",superficiecommuneetablissement=" + String.valueOf(superficiecommuneetablissement));
			sb.append(",distanceacheteuretablissement=" + String.valueOf(distanceacheteuretablissement));
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",formeprix=" + formeprix);
			sb.append(",anneenotification=" + anneenotification);
			sb.append(",libelledepartementacheteur=" + libelledepartementacheteur);
			sb.append(",departementacheteur=" + departementacheteur);
			sb.append(",codepostaletablissement=" + codepostaletablissement);
			sb.append(",montantcalcule=" + String.valueOf(montantcalcule));
			sb.append(",coderegionexecution=" + String.valueOf(coderegionexecution));
			sb.append(",codecpv=" + codecpv);
			sb.append(",referencecpv=" + referencecpv);
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(to_stgStruct other) {

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

	public static class doublons_stgStruct implements routines.system.IPersistableRow<doublons_stgStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_daily_loading_staging = new byte[0];
		static byte[] commonByteArray_CITYVIZ_daily_loading_staging = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String recordid;

		public String getRecordid() {
			return this.recordid;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
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
			final doublons_stgStruct other = (doublons_stgStruct) obj;

			if (this.recordid == null) {
				if (other.recordid != null)
					return false;

			} else if (!this.recordid.equals(other.recordid))

				return false;

			return true;
		}

		public void copyDataTo(doublons_stgStruct other) {

			other.recordid = this.recordid;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.idmarche = this.idmarche;

		}

		public void copyKeysDataTo(doublons_stgStruct other) {

			other.recordid = this.recordid;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.idmarche = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.idmarche = readInteger(dis);

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

				writeString(this.denominationsocialeetablissement, dos);

				// Integer

				writeInteger(this.idmarche, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.recordid, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// Integer

				writeInteger(this.idmarche, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("recordid=" + recordid);
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",idmarche=" + String.valueOf(idmarche));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(doublons_stgStruct other) {

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

	public static class row11Struct implements routines.system.IPersistableRow<row11Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_daily_loading_staging = new byte[0];
		static byte[] commonByteArray_CITYVIZ_daily_loading_staging = new byte[0];

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

		public java.util.Date datenotification;

		public java.util.Date getDatenotification() {
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

		public Float populationcommuneetablissement;

		public Float getPopulationcommuneetablissement() {
			return this.populationcommuneetablissement;
		}

		public String codecommuneacheteur;

		public String getCodecommuneacheteur() {
			return this.codecommuneacheteur;
		}

		public Float superficiecommuneetablissement;

		public Float getSuperficiecommuneetablissement() {
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

		public Float nombretitulairesurmarchepresume;

		public Float getNombretitulairesurmarchepresume() {
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

		public String superficiecommuneacheteur;

		public String getSuperficiecommuneacheteur() {
			return this.superficiecommuneacheteur;
		}

		public Integer codearrondissementacheteur;

		public Integer getCodearrondissementacheteur() {
			return this.codearrondissementacheteur;
		}

		public Float distanceacheteuretablissement;

		public Float getDistanceacheteuretablissement() {
			return this.distanceacheteuretablissement;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public Float montant;

		public Float getMontant() {
			return this.montant;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public java.util.Date datepublicationdonnees;

		public java.util.Date getDatepublicationdonnees() {
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

		public Integer coderegionetablissement;

		public Integer getCoderegionetablissement() {
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

		public Integer libellearrondissementetablissement;

		public Integer getLibellearrondissementetablissement() {
			return this.libellearrondissementetablissement;
		}

		public Integer codearrondissementetablissement;

		public Integer getCodearrondissementetablissement() {
			return this.codearrondissementetablissement;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

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

					this.datenotification = readDate(dis);

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

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneetablissement = null;
					} else {
						this.populationcommuneetablissement = dis.readFloat();
					}

					this.codecommuneacheteur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.superficiecommuneetablissement = null;
					} else {
						this.superficiecommuneetablissement = dis.readFloat();
					}

					this.nicetablissement = readString(dis);

					this.geoloccommuneetablissement___ = readString(dis);

					this.objetmarche = readString(dis);

					this.nature = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.nombretitulairesurmarchepresume = null;
					} else {
						this.nombretitulairesurmarchepresume = dis.readFloat();
					}

					this.libelleregionetablissement = readString(dis);

					this.id = readString(dis);

					this.source = readString(dis);

					this.dureemoisestimee = readString(dis);

					this.nomacheteur = readString(dis);

					this.referencecpv = readString(dis);

					this.type = readString(dis);

					this.sirenetablissement = readString(dis);

					this.superficiecommuneacheteur = readString(dis);

					this.codearrondissementacheteur = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.distanceacheteuretablissement = null;
					} else {
						this.distanceacheteuretablissement = dis.readFloat();
					}

					this.communeetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montant = null;
					} else {
						this.montant = dis.readFloat();
					}

					this.departementetablissement = readString(dis);

					this.datepublicationdonnees = readDate(dis);

					this.sirenacheteurvalide = readString(dis);

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readInteger(dis);

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

					this.libellearrondissementetablissement = readInteger(dis);

					this.codearrondissementetablissement = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

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

					this.datenotification = readDate(dis);

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

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneetablissement = null;
					} else {
						this.populationcommuneetablissement = dis.readFloat();
					}

					this.codecommuneacheteur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.superficiecommuneetablissement = null;
					} else {
						this.superficiecommuneetablissement = dis.readFloat();
					}

					this.nicetablissement = readString(dis);

					this.geoloccommuneetablissement___ = readString(dis);

					this.objetmarche = readString(dis);

					this.nature = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.nombretitulairesurmarchepresume = null;
					} else {
						this.nombretitulairesurmarchepresume = dis.readFloat();
					}

					this.libelleregionetablissement = readString(dis);

					this.id = readString(dis);

					this.source = readString(dis);

					this.dureemoisestimee = readString(dis);

					this.nomacheteur = readString(dis);

					this.referencecpv = readString(dis);

					this.type = readString(dis);

					this.sirenetablissement = readString(dis);

					this.superficiecommuneacheteur = readString(dis);

					this.codearrondissementacheteur = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.distanceacheteuretablissement = null;
					} else {
						this.distanceacheteuretablissement = dis.readFloat();
					}

					this.communeetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montant = null;
					} else {
						this.montant = dis.readFloat();
					}

					this.departementetablissement = readString(dis);

					this.datepublicationdonnees = readDate(dis);

					this.sirenacheteurvalide = readString(dis);

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readInteger(dis);

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

					this.libellearrondissementetablissement = readInteger(dis);

					this.codearrondissementetablissement = readInteger(dis);

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

				// java.util.Date

				writeDate(this.datenotification, dos);

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

				// Float

				if (this.populationcommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneetablissement);
				}

				// String

				writeString(this.codecommuneacheteur, dos);

				// Float

				if (this.superficiecommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneetablissement);
				}

				// String

				writeString(this.nicetablissement, dos);

				// String

				writeString(this.geoloccommuneetablissement___, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.nature, dos);

				// Float

				if (this.nombretitulairesurmarchepresume == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.nombretitulairesurmarchepresume);
				}

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

				// String

				writeString(this.superficiecommuneacheteur, dos);

				// Integer

				writeInteger(this.codearrondissementacheteur, dos);

				// Float

				if (this.distanceacheteuretablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.distanceacheteuretablissement);
				}

				// String

				writeString(this.communeetablissement, dos);

				// Float

				if (this.montant == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montant);
				}

				// String

				writeString(this.departementetablissement, dos);

				// java.util.Date

				writeDate(this.datepublicationdonnees, dos);

				// String

				writeString(this.sirenacheteurvalide, dos);

				// String

				writeString(this.moisnotification, dos);

				// Integer

				writeInteger(this.coderegionetablissement, dos);

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

				// Integer

				writeInteger(this.libellearrondissementetablissement, dos);

				// Integer

				writeInteger(this.codearrondissementetablissement, dos);

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

				// java.util.Date

				writeDate(this.datenotification, dos);

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

				// Float

				if (this.populationcommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneetablissement);
				}

				// String

				writeString(this.codecommuneacheteur, dos);

				// Float

				if (this.superficiecommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneetablissement);
				}

				// String

				writeString(this.nicetablissement, dos);

				// String

				writeString(this.geoloccommuneetablissement___, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.nature, dos);

				// Float

				if (this.nombretitulairesurmarchepresume == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.nombretitulairesurmarchepresume);
				}

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

				// String

				writeString(this.superficiecommuneacheteur, dos);

				// Integer

				writeInteger(this.codearrondissementacheteur, dos);

				// Float

				if (this.distanceacheteuretablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.distanceacheteuretablissement);
				}

				// String

				writeString(this.communeetablissement, dos);

				// Float

				if (this.montant == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montant);
				}

				// String

				writeString(this.departementetablissement, dos);

				// java.util.Date

				writeDate(this.datepublicationdonnees, dos);

				// String

				writeString(this.sirenacheteurvalide, dos);

				// String

				writeString(this.moisnotification, dos);

				// Integer

				writeInteger(this.coderegionetablissement, dos);

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

				// Integer

				writeInteger(this.libellearrondissementetablissement, dos);

				// Integer

				writeInteger(this.codearrondissementetablissement, dos);

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
			sb.append(",datenotification=" + String.valueOf(datenotification));
			sb.append(",montantcalcule=" + String.valueOf(montantcalcule));
			sb.append(",libelledepartementacheteur=" + libelledepartementacheteur);
			sb.append(",codecpv_division=" + String.valueOf(codecpv_division));
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",libellearrondissementacheteur=" + libellearrondissementacheteur);
			sb.append(",populationcommuneetablissement=" + String.valueOf(populationcommuneetablissement));
			sb.append(",codecommuneacheteur=" + codecommuneacheteur);
			sb.append(",superficiecommuneetablissement=" + String.valueOf(superficiecommuneetablissement));
			sb.append(",nicetablissement=" + nicetablissement);
			sb.append(",geoloccommuneetablissement___=" + geoloccommuneetablissement___);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",nature=" + nature);
			sb.append(",nombretitulairesurmarchepresume=" + String.valueOf(nombretitulairesurmarchepresume));
			sb.append(",libelleregionetablissement=" + libelleregionetablissement);
			sb.append(",id=" + id);
			sb.append(",source=" + source);
			sb.append(",dureemoisestimee=" + dureemoisestimee);
			sb.append(",nomacheteur=" + nomacheteur);
			sb.append(",referencecpv=" + referencecpv);
			sb.append(",type=" + type);
			sb.append(",sirenetablissement=" + sirenetablissement);
			sb.append(",superficiecommuneacheteur=" + superficiecommuneacheteur);
			sb.append(",codearrondissementacheteur=" + String.valueOf(codearrondissementacheteur));
			sb.append(",distanceacheteuretablissement=" + String.valueOf(distanceacheteuretablissement));
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",montant=" + String.valueOf(montant));
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",datepublicationdonnees=" + String.valueOf(datepublicationdonnees));
			sb.append(",sirenacheteurvalide=" + sirenacheteurvalide);
			sb.append(",moisnotification=" + moisnotification);
			sb.append(",coderegionetablissement=" + String.valueOf(coderegionetablissement));
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
			sb.append(",libellearrondissementetablissement=" + String.valueOf(libellearrondissementetablissement));
			sb.append(",codearrondissementetablissement=" + String.valueOf(codearrondissementetablissement));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row11Struct other) {

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

	public static class row10Struct implements routines.system.IPersistableRow<row10Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_daily_loading_staging = new byte[0];
		static byte[] commonByteArray_CITYVIZ_daily_loading_staging = new byte[0];
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

		public java.util.Date datenotification;

		public java.util.Date getDatenotification() {
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

		public Float populationcommuneetablissement;

		public Float getPopulationcommuneetablissement() {
			return this.populationcommuneetablissement;
		}

		public String codecommuneacheteur;

		public String getCodecommuneacheteur() {
			return this.codecommuneacheteur;
		}

		public Float superficiecommuneetablissement;

		public Float getSuperficiecommuneetablissement() {
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

		public Float nombretitulairesurmarchepresume;

		public Float getNombretitulairesurmarchepresume() {
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

		public String superficiecommuneacheteur;

		public String getSuperficiecommuneacheteur() {
			return this.superficiecommuneacheteur;
		}

		public Integer codearrondissementacheteur;

		public Integer getCodearrondissementacheteur() {
			return this.codearrondissementacheteur;
		}

		public Float distanceacheteuretablissement;

		public Float getDistanceacheteuretablissement() {
			return this.distanceacheteuretablissement;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public Float montant;

		public Float getMontant() {
			return this.montant;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public java.util.Date datepublicationdonnees;

		public java.util.Date getDatepublicationdonnees() {
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

		public Integer coderegionetablissement;

		public Integer getCoderegionetablissement() {
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

		public Integer libellearrondissementetablissement;

		public Integer getLibellearrondissementetablissement() {
			return this.libellearrondissementetablissement;
		}

		public Integer codearrondissementetablissement;

		public Integer getCodearrondissementetablissement() {
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
			final row10Struct other = (row10Struct) obj;

			if (this.recordid == null) {
				if (other.recordid != null)
					return false;

			} else if (!this.recordid.equals(other.recordid))

				return false;

			return true;
		}

		public void copyDataTo(row10Struct other) {

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

		public void copyKeysDataTo(row10Struct other) {

			other.recordid = this.recordid;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

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

					this.datenotification = readDate(dis);

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

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneetablissement = null;
					} else {
						this.populationcommuneetablissement = dis.readFloat();
					}

					this.codecommuneacheteur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.superficiecommuneetablissement = null;
					} else {
						this.superficiecommuneetablissement = dis.readFloat();
					}

					this.nicetablissement = readString(dis);

					this.geoloccommuneetablissement___ = readString(dis);

					this.objetmarche = readString(dis);

					this.nature = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.nombretitulairesurmarchepresume = null;
					} else {
						this.nombretitulairesurmarchepresume = dis.readFloat();
					}

					this.libelleregionetablissement = readString(dis);

					this.id = readString(dis);

					this.source = readString(dis);

					this.dureemoisestimee = readString(dis);

					this.nomacheteur = readString(dis);

					this.referencecpv = readString(dis);

					this.type = readString(dis);

					this.sirenetablissement = readString(dis);

					this.superficiecommuneacheteur = readString(dis);

					this.codearrondissementacheteur = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.distanceacheteuretablissement = null;
					} else {
						this.distanceacheteuretablissement = dis.readFloat();
					}

					this.communeetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montant = null;
					} else {
						this.montant = dis.readFloat();
					}

					this.departementetablissement = readString(dis);

					this.datepublicationdonnees = readDate(dis);

					this.sirenacheteurvalide = readString(dis);

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readInteger(dis);

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

					this.libellearrondissementetablissement = readInteger(dis);

					this.codearrondissementetablissement = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

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

					this.datenotification = readDate(dis);

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

					length = dis.readByte();
					if (length == -1) {
						this.populationcommuneetablissement = null;
					} else {
						this.populationcommuneetablissement = dis.readFloat();
					}

					this.codecommuneacheteur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.superficiecommuneetablissement = null;
					} else {
						this.superficiecommuneetablissement = dis.readFloat();
					}

					this.nicetablissement = readString(dis);

					this.geoloccommuneetablissement___ = readString(dis);

					this.objetmarche = readString(dis);

					this.nature = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.nombretitulairesurmarchepresume = null;
					} else {
						this.nombretitulairesurmarchepresume = dis.readFloat();
					}

					this.libelleregionetablissement = readString(dis);

					this.id = readString(dis);

					this.source = readString(dis);

					this.dureemoisestimee = readString(dis);

					this.nomacheteur = readString(dis);

					this.referencecpv = readString(dis);

					this.type = readString(dis);

					this.sirenetablissement = readString(dis);

					this.superficiecommuneacheteur = readString(dis);

					this.codearrondissementacheteur = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.distanceacheteuretablissement = null;
					} else {
						this.distanceacheteuretablissement = dis.readFloat();
					}

					this.communeetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montant = null;
					} else {
						this.montant = dis.readFloat();
					}

					this.departementetablissement = readString(dis);

					this.datepublicationdonnees = readDate(dis);

					this.sirenacheteurvalide = readString(dis);

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readInteger(dis);

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

					this.libellearrondissementetablissement = readInteger(dis);

					this.codearrondissementetablissement = readInteger(dis);

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

				// java.util.Date

				writeDate(this.datenotification, dos);

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

				// Float

				if (this.populationcommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneetablissement);
				}

				// String

				writeString(this.codecommuneacheteur, dos);

				// Float

				if (this.superficiecommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneetablissement);
				}

				// String

				writeString(this.nicetablissement, dos);

				// String

				writeString(this.geoloccommuneetablissement___, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.nature, dos);

				// Float

				if (this.nombretitulairesurmarchepresume == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.nombretitulairesurmarchepresume);
				}

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

				// String

				writeString(this.superficiecommuneacheteur, dos);

				// Integer

				writeInteger(this.codearrondissementacheteur, dos);

				// Float

				if (this.distanceacheteuretablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.distanceacheteuretablissement);
				}

				// String

				writeString(this.communeetablissement, dos);

				// Float

				if (this.montant == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montant);
				}

				// String

				writeString(this.departementetablissement, dos);

				// java.util.Date

				writeDate(this.datepublicationdonnees, dos);

				// String

				writeString(this.sirenacheteurvalide, dos);

				// String

				writeString(this.moisnotification, dos);

				// Integer

				writeInteger(this.coderegionetablissement, dos);

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

				// Integer

				writeInteger(this.libellearrondissementetablissement, dos);

				// Integer

				writeInteger(this.codearrondissementetablissement, dos);

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

				// java.util.Date

				writeDate(this.datenotification, dos);

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

				// Float

				if (this.populationcommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneetablissement);
				}

				// String

				writeString(this.codecommuneacheteur, dos);

				// Float

				if (this.superficiecommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneetablissement);
				}

				// String

				writeString(this.nicetablissement, dos);

				// String

				writeString(this.geoloccommuneetablissement___, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.nature, dos);

				// Float

				if (this.nombretitulairesurmarchepresume == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.nombretitulairesurmarchepresume);
				}

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

				// String

				writeString(this.superficiecommuneacheteur, dos);

				// Integer

				writeInteger(this.codearrondissementacheteur, dos);

				// Float

				if (this.distanceacheteuretablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.distanceacheteuretablissement);
				}

				// String

				writeString(this.communeetablissement, dos);

				// Float

				if (this.montant == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montant);
				}

				// String

				writeString(this.departementetablissement, dos);

				// java.util.Date

				writeDate(this.datepublicationdonnees, dos);

				// String

				writeString(this.sirenacheteurvalide, dos);

				// String

				writeString(this.moisnotification, dos);

				// Integer

				writeInteger(this.coderegionetablissement, dos);

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

				// Integer

				writeInteger(this.libellearrondissementetablissement, dos);

				// Integer

				writeInteger(this.codearrondissementetablissement, dos);

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
			sb.append(",datenotification=" + String.valueOf(datenotification));
			sb.append(",montantcalcule=" + String.valueOf(montantcalcule));
			sb.append(",libelledepartementacheteur=" + libelledepartementacheteur);
			sb.append(",codecpv_division=" + String.valueOf(codecpv_division));
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",libellearrondissementacheteur=" + libellearrondissementacheteur);
			sb.append(",populationcommuneetablissement=" + String.valueOf(populationcommuneetablissement));
			sb.append(",codecommuneacheteur=" + codecommuneacheteur);
			sb.append(",superficiecommuneetablissement=" + String.valueOf(superficiecommuneetablissement));
			sb.append(",nicetablissement=" + nicetablissement);
			sb.append(",geoloccommuneetablissement___=" + geoloccommuneetablissement___);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",nature=" + nature);
			sb.append(",nombretitulairesurmarchepresume=" + String.valueOf(nombretitulairesurmarchepresume));
			sb.append(",libelleregionetablissement=" + libelleregionetablissement);
			sb.append(",id=" + id);
			sb.append(",source=" + source);
			sb.append(",dureemoisestimee=" + dureemoisestimee);
			sb.append(",nomacheteur=" + nomacheteur);
			sb.append(",referencecpv=" + referencecpv);
			sb.append(",type=" + type);
			sb.append(",sirenetablissement=" + sirenetablissement);
			sb.append(",superficiecommuneacheteur=" + superficiecommuneacheteur);
			sb.append(",codearrondissementacheteur=" + String.valueOf(codearrondissementacheteur));
			sb.append(",distanceacheteuretablissement=" + String.valueOf(distanceacheteuretablissement));
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",montant=" + String.valueOf(montant));
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",datepublicationdonnees=" + String.valueOf(datepublicationdonnees));
			sb.append(",sirenacheteurvalide=" + sirenacheteurvalide);
			sb.append(",moisnotification=" + moisnotification);
			sb.append(",coderegionetablissement=" + String.valueOf(coderegionetablissement));
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
			sb.append(",libellearrondissementetablissement=" + String.valueOf(libellearrondissementetablissement));
			sb.append(",codearrondissementetablissement=" + String.valueOf(codearrondissementetablissement));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row10Struct other) {

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

	public void tFileInputJSON_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputJSON_2_SUBPROCESS_STATE", 0);

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

				row10Struct row10 = new row10Struct();
				row10Struct row11 = row10;
				to_stgStruct to_stg = new to_stgStruct();
				doublons_stgStruct doublons_stg = new doublons_stgStruct();
				doublons_stgStruct row1 = doublons_stg;

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "to_stg");
				}

				int tos_count_tDBOutput_2 = 0;

				String dbschema_tDBOutput_2 = null;
				dbschema_tDBOutput_2 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_2 = null;
				if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
					tableName_tDBOutput_2 = ("marche_v2");
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "\".\"" + ("marche_v2");
				}

				int updateKeyCount_tDBOutput_2 = 1;
				if (updateKeyCount_tDBOutput_2 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_2 == 65 && true) {
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
						if (table_tDBOutput_2.equals(("marche_v2")) && (schema_tDBOutput_2.equals(dbschema_tDBOutput_2)
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
								+ "\"(\"recordid\" VARCHAR(40)  ,\"datepublicationdonnees\" TIMESTAMP(10)  ,\"procedure\" VARCHAR(255)  ,\"nature\" VARCHAR(255)  ,\"type\" VARCHAR(255)  ,\"datenotification\" TIMESTAMP(255)  ,\"montant\" FLOAT4 ,\"siretetablissement\" VARCHAR(18)  ,\"objetmarche\" VARCHAR(512)  ,\"idacheteur\" VARCHAR(255)  ,\"codecommuneacheteur\" VARCHAR(50)  ,\"source\" VARCHAR(255)  ,\"lieuexecutionnom\" VARCHAR(255)  ,\"codearrondissementetablissement\" INT4 ,\"libellearrondissementetablissement\" INT4 ,\"sirenetablissement\" VARCHAR(255)  ,\"codetypeetablissement\" VARCHAR(5)  ,\"nicetablissement\" VARCHAR(255)  ,\"lieuexecutiontypecode\" VARCHAR(255)  ,\"libelleregionetablissement\" VARCHAR(255)  ,\"categorieetablissement\" VARCHAR(255)  ,\"nomacheteur\" VARCHAR(255)  ,\"idmarche\" INT4 ,\"communeetablissement\" VARCHAR(29)  ,\"libellecommuneacheteur\" VARCHAR(32)  ,\"libelledepartementetablissement\" VARCHAR(23)  ,\"dureemoisestimee\" VARCHAR(5)  ,\"libelleregionacheteur\" VARCHAR(255)  ,\"coderegionacheteur\" VARCHAR(255)  ,\"siretetablissementvalide\" VARCHAR(11)  ,\"codecommuneetablissement\" VARCHAR(255)  ,\"codepostalacheteur\" VARCHAR(255)  ,\"departementetablissement\" VARCHAR(255)  ,\"superficiecommuneacheteur\" VARCHAR(255)  ,\"populationcommuneacheteur\" FLOAT4 ,\"codedepartementexecution\" FLOAT4 ,\"sirenacheteurvalide\" VARCHAR(5)  ,\"codearrondissementacheteur\" INT4 ,\"sirenetablissementvalide\" VARCHAR(255)  ,\"dureemoiscalculee\" FLOAT4 ,\"populationcommuneetablissement\" FLOAT4 ,\"codecpv_division\" INT4 ,\"denominationsocialeetablissement\" VARCHAR(255)  ,\"coderegionetablissement\" INT4 ,\"lieuexecutioncode\" VARCHAR(255)  ,\"moisnotification\" VARCHAR(255)  ,\"codecpv_original\" VARCHAR(255)  ,\"typeidentifiantetablissement\" VARCHAR(255)  ,\"dureemois\" INT4 ,\"libellearrondissementacheteur\" VARCHAR(255)  ,\"nombretitulairesurmarchepresume\" FLOAT4 ,\"adresseetablissement\" VARCHAR(255)  ,\"superficiecommuneetablissement\" FLOAT4 ,\"distanceacheteuretablissement\" FLOAT4 ,\"natureobjetmarche\" VARCHAR(255)  ,\"formeprix\" VARCHAR(255)  ,\"anneenotification\" VARCHAR(255)  ,\"libelledepartementacheteur\" VARCHAR(55)  ,\"departementacheteur\" VARCHAR(255)  ,\"codepostaletablissement\" VARCHAR(255)  ,\"montantcalcule\" FLOAT4 ,\"coderegionexecution\" FLOAT4 ,\"codecpv\" VARCHAR(255)  ,\"referencecpv\" VARCHAR(255)  ,\"libelleregionexecution\" VARCHAR(255)  ,primary key(\"recordid\"))");
					}
				}
				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_2 + "\" WHERE \"recordid\" = ?");
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);
				String insert_tDBOutput_2 = "INSERT INTO \"" + tableName_tDBOutput_2
						+ "\" (\"recordid\",\"datepublicationdonnees\",\"procedure\",\"nature\",\"type\",\"datenotification\",\"montant\",\"siretetablissement\",\"objetmarche\",\"idacheteur\",\"codecommuneacheteur\",\"source\",\"lieuexecutionnom\",\"codearrondissementetablissement\",\"libellearrondissementetablissement\",\"sirenetablissement\",\"codetypeetablissement\",\"nicetablissement\",\"lieuexecutiontypecode\",\"libelleregionetablissement\",\"categorieetablissement\",\"nomacheteur\",\"idmarche\",\"communeetablissement\",\"libellecommuneacheteur\",\"libelledepartementetablissement\",\"dureemoisestimee\",\"libelleregionacheteur\",\"coderegionacheteur\",\"siretetablissementvalide\",\"codecommuneetablissement\",\"codepostalacheteur\",\"departementetablissement\",\"superficiecommuneacheteur\",\"populationcommuneacheteur\",\"codedepartementexecution\",\"sirenacheteurvalide\",\"codearrondissementacheteur\",\"sirenetablissementvalide\",\"dureemoiscalculee\",\"populationcommuneetablissement\",\"codecpv_division\",\"denominationsocialeetablissement\",\"coderegionetablissement\",\"lieuexecutioncode\",\"moisnotification\",\"codecpv_original\",\"typeidentifiantetablissement\",\"dureemois\",\"libellearrondissementacheteur\",\"nombretitulairesurmarchepresume\",\"adresseetablissement\",\"superficiecommuneetablissement\",\"distanceacheteuretablissement\",\"natureobjetmarche\",\"formeprix\",\"anneenotification\",\"libelledepartementacheteur\",\"departementacheteur\",\"codepostaletablissement\",\"montantcalcule\",\"coderegionexecution\",\"codecpv\",\"referencecpv\",\"libelleregionexecution\") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_2 = conn_tDBOutput_2
						.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmtInsert_tDBOutput_2", pstmtInsert_tDBOutput_2);
				String update_tDBOutput_2 = "UPDATE \"" + tableName_tDBOutput_2
						+ "\" SET \"datepublicationdonnees\" = ?,\"procedure\" = ?,\"nature\" = ?,\"type\" = ?,\"datenotification\" = ?,\"montant\" = ?,\"siretetablissement\" = ?,\"objetmarche\" = ?,\"idacheteur\" = ?,\"codecommuneacheteur\" = ?,\"source\" = ?,\"lieuexecutionnom\" = ?,\"codearrondissementetablissement\" = ?,\"libellearrondissementetablissement\" = ?,\"sirenetablissement\" = ?,\"codetypeetablissement\" = ?,\"nicetablissement\" = ?,\"lieuexecutiontypecode\" = ?,\"libelleregionetablissement\" = ?,\"categorieetablissement\" = ?,\"nomacheteur\" = ?,\"idmarche\" = ?,\"communeetablissement\" = ?,\"libellecommuneacheteur\" = ?,\"libelledepartementetablissement\" = ?,\"dureemoisestimee\" = ?,\"libelleregionacheteur\" = ?,\"coderegionacheteur\" = ?,\"siretetablissementvalide\" = ?,\"codecommuneetablissement\" = ?,\"codepostalacheteur\" = ?,\"departementetablissement\" = ?,\"superficiecommuneacheteur\" = ?,\"populationcommuneacheteur\" = ?,\"codedepartementexecution\" = ?,\"sirenacheteurvalide\" = ?,\"codearrondissementacheteur\" = ?,\"sirenetablissementvalide\" = ?,\"dureemoiscalculee\" = ?,\"populationcommuneetablissement\" = ?,\"codecpv_division\" = ?,\"denominationsocialeetablissement\" = ?,\"coderegionetablissement\" = ?,\"lieuexecutioncode\" = ?,\"moisnotification\" = ?,\"codecpv_original\" = ?,\"typeidentifiantetablissement\" = ?,\"dureemois\" = ?,\"libellearrondissementacheteur\" = ?,\"nombretitulairesurmarchepresume\" = ?,\"adresseetablissement\" = ?,\"superficiecommuneetablissement\" = ?,\"distanceacheteuretablissement\" = ?,\"natureobjetmarche\" = ?,\"formeprix\" = ?,\"anneenotification\" = ?,\"libelledepartementacheteur\" = ?,\"departementacheteur\" = ?,\"codepostaletablissement\" = ?,\"montantcalcule\" = ?,\"coderegionexecution\" = ?,\"codecpv\" = ?,\"referencecpv\" = ?,\"libelleregionexecution\" = ? WHERE \"recordid\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_2 = conn_tDBOutput_2
						.prepareStatement(update_tDBOutput_2);
				resourceMap.put("pstmtUpdate_tDBOutput_2", pstmtUpdate_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tWarn_1 begin ] start
				 */

				ok_Hash.put("tWarn_1", false);
				start_Hash.put("tWarn_1", System.currentTimeMillis());

				currentComponent = "tWarn_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tWarn_1 = 0;

				/**
				 * [tWarn_1 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_3", false);
				start_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "doublons_stg");
				}

				int tos_count_tFileOutputDelimited_3 = 0;

				String fileName_tFileOutputDelimited_3 = "";
				fileName_tFileOutputDelimited_3 = (new java.io.File(
						context.logs_path + "/daily_staging_loading/stg_doublons.csv")).getAbsolutePath().replace("\\",
								"/");
				String fullName_tFileOutputDelimited_3 = null;
				String extension_tFileOutputDelimited_3 = null;
				String directory_tFileOutputDelimited_3 = null;
				if ((fileName_tFileOutputDelimited_3.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_3.lastIndexOf(".") < fileName_tFileOutputDelimited_3
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3;
						extension_tFileOutputDelimited_3 = "";
					} else {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
								fileName_tFileOutputDelimited_3.lastIndexOf("."));
						extension_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3
								.substring(fileName_tFileOutputDelimited_3.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
							fileName_tFileOutputDelimited_3.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_3.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
								fileName_tFileOutputDelimited_3.lastIndexOf("."));
						extension_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3
								.substring(fileName_tFileOutputDelimited_3.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3;
						extension_tFileOutputDelimited_3 = "";
					}
					directory_tFileOutputDelimited_3 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_3 = true;
				java.io.File filetFileOutputDelimited_3 = new java.io.File(fileName_tFileOutputDelimited_3);
				globalMap.put("tFileOutputDelimited_3_FILE_NAME", fileName_tFileOutputDelimited_3);
				if (filetFileOutputDelimited_3.exists()) {
					isFileGenerated_tFileOutputDelimited_3 = false;
				}
				int nb_line_tFileOutputDelimited_3 = 0;
				int splitedFileNo_tFileOutputDelimited_3 = 0;
				int currentRow_tFileOutputDelimited_3 = 0;

				final String OUT_DELIM_tFileOutputDelimited_3 = /** Start field tFileOutputDelimited_3:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_3:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_3 = /**
																		 * Start field
																		 * tFileOutputDelimited_3:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_3:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_3 != null && directory_tFileOutputDelimited_3.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_3 = new java.io.File(directory_tFileOutputDelimited_3);
					if (!dir_tFileOutputDelimited_3.exists()) {
						dir_tFileOutputDelimited_3.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_3 = null;

				outtFileOutputDelimited_3 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_3, true), "ISO-8859-15"));
				if (filetFileOutputDelimited_3.length() == 0) {
					outtFileOutputDelimited_3.write("recordid");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("denominationsocialeetablissement");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("idmarche");
					outtFileOutputDelimited_3.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_3", outtFileOutputDelimited_3);
				resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);

				/**
				 * [tFileOutputDelimited_3 begin ] stop
				 */

				/**
				 * [tMap_4 begin ] start
				 */

				ok_Hash.put("tMap_4", false);
				start_Hash.put("tMap_4", System.currentTimeMillis());

				currentComponent = "tMap_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row11");
				}

				int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct> tHash_Lookup_row12 = null;

				row12Struct row12HashKey = new row12Struct();
				row12Struct row12Default = new row12Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				to_stgStruct to_stg_tmp = new to_stgStruct();
				doublons_stgStruct doublons_stg_tmp = new doublons_stgStruct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
				 */

				/**
				 * [tWarn_5 begin ] start
				 */

				ok_Hash.put("tWarn_5", false);
				start_Hash.put("tWarn_5", System.currentTimeMillis());

				currentComponent = "tWarn_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row10");
				}

				int tos_count_tWarn_5 = 0;

				/**
				 * [tWarn_5 begin ] stop
				 */

				/**
				 * [tFileInputJSON_2 begin ] start
				 */

				ok_Hash.put("tFileInputJSON_2", false);
				start_Hash.put("tFileInputJSON_2", System.currentTimeMillis());

				currentComponent = "tFileInputJSON_2";

				int tos_count_tFileInputJSON_2 = 0;

				class JsonPathCache_tFileInputJSON_2 {
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

				int nb_line_tFileInputJSON_2 = 0;

				JsonPathCache_tFileInputJSON_2 jsonPathCache_tFileInputJSON_2 = new JsonPathCache_tFileInputJSON_2();

				String loopPath_tFileInputJSON_2 = "$.records[*]";
				java.util.List<Object> resultset_tFileInputJSON_2 = new java.util.ArrayList<Object>();

				java.io.InputStream is_tFileInputJSON_2 = null;
				com.jayway.jsonpath.ParseContext parseContext_tFileInputJSON_2 = com.jayway.jsonpath.JsonPath
						.using(com.jayway.jsonpath.Configuration.defaultConfiguration());
				Object filenameOrStream_tFileInputJSON_2 = null;
				try {
					filenameOrStream_tFileInputJSON_2 = context.data_storage_path + "/" + context.api_result_filename;
				} catch (java.lang.Exception e_tFileInputJSON_2) {
					globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());

					globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
					System.err.println(e_tFileInputJSON_2.getMessage());
				}

				com.jayway.jsonpath.ReadContext document_tFileInputJSON_2 = null;
				try {
					if (filenameOrStream_tFileInputJSON_2 instanceof java.io.InputStream) {
						is_tFileInputJSON_2 = (java.io.InputStream) filenameOrStream_tFileInputJSON_2;
					} else {

						is_tFileInputJSON_2 = new java.io.FileInputStream((String) filenameOrStream_tFileInputJSON_2);

					}

					document_tFileInputJSON_2 = parseContext_tFileInputJSON_2.parse(is_tFileInputJSON_2, "UTF-8");
					com.jayway.jsonpath.JsonPath compiledLoopPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
							.getCompiledJsonPath(loopPath_tFileInputJSON_2);
					Object result_tFileInputJSON_2 = document_tFileInputJSON_2.read(compiledLoopPath_tFileInputJSON_2,
							net.minidev.json.JSONObject.class);
					if (result_tFileInputJSON_2 instanceof net.minidev.json.JSONArray) {
						resultset_tFileInputJSON_2 = (net.minidev.json.JSONArray) result_tFileInputJSON_2;
					} else {
						resultset_tFileInputJSON_2.add(result_tFileInputJSON_2);
					}
				} catch (java.lang.Exception e_tFileInputJSON_2) {
					globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
					globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
					System.err.println(e_tFileInputJSON_2.getMessage());
				} finally {
					if (is_tFileInputJSON_2 != null) {
						is_tFileInputJSON_2.close();
					}
				}

				String jsonPath_tFileInputJSON_2 = null;
				com.jayway.jsonpath.JsonPath compiledJsonPath_tFileInputJSON_2 = null;

				Object value_tFileInputJSON_2 = null;
				Object root_tFileInputJSON_2 = null;
				for (Object row_tFileInputJSON_2 : resultset_tFileInputJSON_2) {
					nb_line_tFileInputJSON_2++;
					row10 = null;
					boolean whetherReject_tFileInputJSON_2 = false;
					row10 = new row10Struct();

					try {
						jsonPath_tFileInputJSON_2 = "recordid";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.recordid = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.recordid =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.sirenetablissementvalide";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.sirenetablissementvalide = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.sirenetablissementvalide =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.denominationsocialeetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.denominationsocialeetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.denominationsocialeetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.libellecommuneacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.libellecommuneacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.libellecommuneacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.lieuexecutioncode";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.lieuexecutioncode = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.lieuexecutioncode =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.adresseetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.adresseetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.adresseetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codepostalacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.codepostalacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codepostalacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.libelleregionacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.libelleregionacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.libelleregionacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.departementacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.departementacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.departementacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.idmarche";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.idmarche = ParserUtils.parseTo_Integer(value_tFileInputJSON_2.toString());
							} else {
								row10.idmarche =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.idmarche =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.dureemois";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.dureemois = ParserUtils.parseTo_Integer(value_tFileInputJSON_2.toString());
							} else {
								row10.dureemois =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.dureemois =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.datenotification";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.datenotification = ParserUtils.parseTo_Date(value_tFileInputJSON_2.toString(),
										"yyyy-MM-dd");
							} else {
								row10.datenotification =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.datenotification =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.montantcalcule";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.montantcalcule = ParserUtils.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.montantcalcule =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.montantcalcule =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.libelledepartementacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.libelledepartementacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.libelledepartementacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codecpv_division";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.codecpv_division = ParserUtils.parseTo_Integer(value_tFileInputJSON_2.toString());
							} else {
								row10.codecpv_division =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codecpv_division =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.lieuexecutionnom";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.lieuexecutionnom = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.lieuexecutionnom =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.libellearrondissementacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.libellearrondissementacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.libellearrondissementacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.populationcommuneetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.populationcommuneetablissement = ParserUtils
										.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.populationcommuneetablissement =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.populationcommuneetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codecommuneacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.codecommuneacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codecommuneacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.superficiecommuneetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.superficiecommuneetablissement = ParserUtils
										.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.superficiecommuneetablissement =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.superficiecommuneetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.nicetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.nicetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.nicetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.geoloccommuneetablissement__";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.geoloccommuneetablissement___ = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.geoloccommuneetablissement___ =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.objetmarche";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.objetmarche = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.objetmarche =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.nature";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.nature = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.nature =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.nombretitulairesurmarchepresume";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.nombretitulairesurmarchepresume = ParserUtils
										.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.nombretitulairesurmarchepresume =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.nombretitulairesurmarchepresume =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.libelleregionetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.libelleregionetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.libelleregionetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.id";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.id = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.id =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.source";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.source = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.source =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.dureemoisestimee";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.dureemoisestimee = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.dureemoisestimee =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.nomacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.nomacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.nomacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.referencecpv";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.referencecpv = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.referencecpv =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.type";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.type = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.type =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.sirenetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.sirenetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.sirenetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.superficiecommuneacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.superficiecommuneacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.superficiecommuneacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codearrondissementacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.codearrondissementacheteur = ParserUtils
										.parseTo_Integer(value_tFileInputJSON_2.toString());
							} else {
								row10.codearrondissementacheteur =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codearrondissementacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.distanceacheteuretablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.distanceacheteuretablissement = ParserUtils
										.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.distanceacheteuretablissement =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.distanceacheteuretablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.communeetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.communeetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.communeetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.montant";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.montant = ParserUtils.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.montant =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.montant =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.departementetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.departementetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.departementetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.datepublicationdonnees";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.datepublicationdonnees = ParserUtils
										.parseTo_Date(value_tFileInputJSON_2.toString(), "yyyy-MM-dd");
							} else {
								row10.datepublicationdonnees =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.datepublicationdonnees =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.sirenacheteurvalide";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.sirenacheteurvalide = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.sirenacheteurvalide =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.moisnotification";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.moisnotification = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.moisnotification =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.coderegionetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.coderegionetablissement = ParserUtils
										.parseTo_Integer(value_tFileInputJSON_2.toString());
							} else {
								row10.coderegionetablissement =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.coderegionetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.formeprix";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.formeprix = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.formeprix =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.siretetablissementvalide";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.siretetablissementvalide = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.siretetablissementvalide =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codedepartementexecution";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.codedepartementexecution = ParserUtils
										.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.codedepartementexecution =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codedepartementexecution =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.dist";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.dist = ParserUtils.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.dist =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.dist =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.coderegionacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.coderegionacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.coderegionacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.lieuexecutiontypecode";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.lieuexecutiontypecode = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.lieuexecutiontypecode =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codecommuneetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.codecommuneetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codecommuneetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.populationcommuneacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.populationcommuneacheteur = ParserUtils
										.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.populationcommuneacheteur =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.populationcommuneacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.idacheteur";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.idacheteur = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.idacheteur =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codecpv_original";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.codecpv_original = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codecpv_original =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.siretetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.siretetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.siretetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.libelledepartementetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.libelledepartementetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.libelledepartementetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.typeidentifiantetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.typeidentifiantetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.typeidentifiantetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.categorieetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.categorieetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.categorieetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.coderegionexecution";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.coderegionexecution = ParserUtils
										.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.coderegionexecution =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.coderegionexecution =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codepostaletablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.codepostaletablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codepostaletablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.natureobjetmarche";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.natureobjetmarche = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.natureobjetmarche =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.dureemoiscalculee";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.dureemoiscalculee = ParserUtils.parseTo_Float(value_tFileInputJSON_2.toString());
							} else {
								row10.dureemoiscalculee =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.dureemoiscalculee =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codetypeetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.codetypeetablissement = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codetypeetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.geoloccommuneacheteur__";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.geoloccommuneacheteur___ = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.geoloccommuneacheteur___ =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codecpv";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.codecpv = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codecpv =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.anneenotification";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.anneenotification = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.anneenotification =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.libelleregionexecution";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.libelleregionexecution = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.libelleregionexecution =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.procedure";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							row10.procedure = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.procedure =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.libellearrondissementetablissemnt";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.libellearrondissementetablissement = ParserUtils
										.parseTo_Integer(value_tFileInputJSON_2.toString());
							} else {
								row10.libellearrondissementetablissement =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.libellearrondissementetablissement =

									null;
						}
						jsonPath_tFileInputJSON_2 = "fields.codearrondissementetablissement";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);

							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row10.codearrondissementetablissement = ParserUtils
										.parseTo_Integer(value_tFileInputJSON_2.toString());
							} else {
								row10.codearrondissementetablissement =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row10.codearrondissementetablissement =

									null;
						}
					} catch (java.lang.Exception e_tFileInputJSON_2) {
						globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
						whetherReject_tFileInputJSON_2 = true;
						System.err.println(e_tFileInputJSON_2.getMessage());
						row10 = null;
						globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
					}
//}

					/**
					 * [tFileInputJSON_2 begin ] stop
					 */

					/**
					 * [tFileInputJSON_2 main ] start
					 */

					currentComponent = "tFileInputJSON_2";

					tos_count_tFileInputJSON_2++;

					/**
					 * [tFileInputJSON_2 main ] stop
					 */

					/**
					 * [tFileInputJSON_2 process_data_begin ] start
					 */

					currentComponent = "tFileInputJSON_2";

					/**
					 * [tFileInputJSON_2 process_data_begin ] stop
					 */
// Start of branch "row10"
					if (row10 != null) {

						/**
						 * [tWarn_5 main ] start
						 */

						currentComponent = "tWarn_5";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row10"

							);
						}

						try {

							resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_5", "", Thread.currentThread().getId() + "",
									"WARN", "", "Extracting record : " + row10.datepublicationdonnees + " => "
											+ row10.denominationsocialeetablissement,
									"", "");
							tLogCatcher_1.addMessage("tWarn", "tWarn_5", 4, "Extracting record : "
									+ row10.datepublicationdonnees + " => " + row10.denominationsocialeetablissement,
									42);
							tLogCatcher_1Process(globalMap);
							globalMap.put("tWarn_5_WARN_MESSAGES", "Extracting record : " + row10.datepublicationdonnees
									+ " => " + row10.denominationsocialeetablissement);
							globalMap.put("tWarn_5_WARN_PRIORITY", 4);
							globalMap.put("tWarn_5_WARN_CODE", 42);

						} catch (Exception e_tWarn_5) {
							globalMap.put("tWarn_5_ERROR_MESSAGE", e_tWarn_5.getMessage());
							logIgnoredError(
									String.format("tWarn_5 - tWarn failed to log message due to internal error: %s",
											e_tWarn_5),
									e_tWarn_5);
						}

						row11 = row10;

						tos_count_tWarn_5++;

						/**
						 * [tWarn_5 main ] stop
						 */

						/**
						 * [tWarn_5 process_data_begin ] start
						 */

						currentComponent = "tWarn_5";

						/**
						 * [tWarn_5 process_data_begin ] stop
						 */

						/**
						 * [tMap_4 main ] start
						 */

						currentComponent = "tMap_4";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row11"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_4 = false;
						boolean mainRowRejected_tMap_4 = false;

						///////////////////////////////////////////////
						// Starting Lookup Table "row12"
						///////////////////////////////////////////////

						boolean forceLooprow12 = false;

						row12Struct row12ObjectFromLookup = null;

						if (!rejectedInnerJoin_tMap_4) { // G_TM_M_020

							hasCasePrimitiveKeyWithNull_tMap_4 = false;

							row12HashKey.recordid = row11.recordid;

							row12HashKey.hashCodeDirty = true;

							tDBInput_2Process(globalMap);

							tHash_Lookup_row12 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct>) globalMap
									.get("tHash_Lookup_row12"));

							tHash_Lookup_row12.initGet();

							tHash_Lookup_row12.lookup(row12HashKey);

							if (!tHash_Lookup_row12.hasNext()) { // G_TM_M_090

								rejectedInnerJoin_tMap_4 = true;

							} // G_TM_M_090

						} // G_TM_M_020

						if (tHash_Lookup_row12 != null && tHash_Lookup_row12.getCount(row12HashKey) > 1) { // G 071

							// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
							// 'row12' and it contains more one result from keys : row12.recordid = '" +
							// row12HashKey.recordid + "'");
						} // G 071

						row12Struct row12 = null;

						row12Struct fromLookup_row12 = null;
						row12 = row12Default;

						if (tHash_Lookup_row12 != null && tHash_Lookup_row12.hasNext()) { // G 099

							fromLookup_row12 = tHash_Lookup_row12.next();

						} // G 099

						if (fromLookup_row12 != null) {
							row12 = fromLookup_row12;
						}

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
							// ###############################
							// # Output tables

							to_stg = null;
							doublons_stg = null;

							if (!rejectedInnerJoin_tMap_4) {

// # Output table : 'to_stg'
								to_stg_tmp.recordid = row11.recordid;
								to_stg_tmp.datepublicationdonnees = row11.datepublicationdonnees;
								to_stg_tmp.procedure = row11.procedure;
								to_stg_tmp.nature = row11.nature;
								to_stg_tmp.type = row11.type;
								to_stg_tmp.datenotification = row11.datenotification;
								to_stg_tmp.montant = row11.montant;
								to_stg_tmp.siretetablissement = row11.siretetablissement;
								to_stg_tmp.objetmarche = row11.objetmarche;
								to_stg_tmp.idacheteur = row11.idacheteur;
								to_stg_tmp.codecommuneacheteur = row11.codecommuneacheteur;
								to_stg_tmp.source = row11.source;
								to_stg_tmp.lieuexecutionnom = row11.lieuexecutionnom;
								to_stg_tmp.codearrondissementetablissement = row11.codearrondissementetablissement;
								to_stg_tmp.libellearrondissementetablissement = row11.libellearrondissementetablissement;
								to_stg_tmp.sirenetablissement = row11.sirenetablissement;
								to_stg_tmp.codetypeetablissement = row11.codetypeetablissement;
								to_stg_tmp.nicetablissement = row11.nicetablissement;
								to_stg_tmp.lieuexecutiontypecode = row11.lieuexecutiontypecode;
								to_stg_tmp.libelleregionetablissement = row11.libelleregionetablissement;
								to_stg_tmp.categorieetablissement = row11.categorieetablissement;
								to_stg_tmp.nomacheteur = row11.nomacheteur;
								to_stg_tmp.idmarche = row11.idmarche;
								to_stg_tmp.communeetablissement = row11.communeetablissement;
								to_stg_tmp.libellecommuneacheteur = row11.libellecommuneacheteur;
								to_stg_tmp.libelledepartementetablissement = row11.libelledepartementetablissement;
								to_stg_tmp.dureemoisestimee = row11.dureemoisestimee;
								to_stg_tmp.libelleregionacheteur = row11.libelleregionacheteur;
								to_stg_tmp.coderegionacheteur = row11.coderegionacheteur;
								to_stg_tmp.siretetablissementvalide = row11.siretetablissementvalide;
								to_stg_tmp.codecommuneetablissement = row11.codecommuneacheteur;
								to_stg_tmp.codepostalacheteur = row11.codepostalacheteur;
								to_stg_tmp.departementetablissement = row11.departementetablissement;
								to_stg_tmp.superficiecommuneacheteur = row11.superficiecommuneacheteur;
								to_stg_tmp.populationcommuneacheteur = row11.populationcommuneacheteur;
								to_stg_tmp.codedepartementexecution = row11.codedepartementexecution;
								to_stg_tmp.sirenacheteurvalide = row11.sirenacheteurvalide;
								to_stg_tmp.codearrondissementacheteur = row11.codearrondissementacheteur;
								to_stg_tmp.sirenetablissementvalide = row11.sirenetablissementvalide;
								to_stg_tmp.dureemoiscalculee = row11.dureemoiscalculee;
								to_stg_tmp.populationcommuneetablissement = row11.populationcommuneetablissement;
								to_stg_tmp.codecpv_division = row11.codecpv_division;
								to_stg_tmp.denominationsocialeetablissement = row11.denominationsocialeetablissement;
								to_stg_tmp.coderegionetablissement = row11.coderegionetablissement;
								to_stg_tmp.lieuexecutioncode = row11.lieuexecutioncode;
								to_stg_tmp.moisnotification = row11.moisnotification;
								to_stg_tmp.codecpv_original = row11.codecpv_original;
								to_stg_tmp.typeidentifiantetablissement = row11.typeidentifiantetablissement;
								to_stg_tmp.dureemois = row11.dureemois;
								to_stg_tmp.libellearrondissementacheteur = row11.libellearrondissementacheteur;
								to_stg_tmp.nombretitulairesurmarchepresume = row11.nombretitulairesurmarchepresume;
								to_stg_tmp.adresseetablissement = row11.adresseetablissement;
								to_stg_tmp.superficiecommuneetablissement = row11.superficiecommuneetablissement;
								to_stg_tmp.distanceacheteuretablissement = row11.distanceacheteuretablissement;
								to_stg_tmp.natureobjetmarche = row11.natureobjetmarche;
								to_stg_tmp.formeprix = row11.formeprix;
								to_stg_tmp.anneenotification = row11.anneenotification;
								to_stg_tmp.libelledepartementacheteur = row11.libelledepartementacheteur;
								to_stg_tmp.departementacheteur = row11.departementacheteur;
								to_stg_tmp.codepostaletablissement = row11.codepostaletablissement;
								to_stg_tmp.montantcalcule = row11.montantcalcule;
								to_stg_tmp.coderegionexecution = row11.coderegionexecution;
								to_stg_tmp.codecpv = row11.codecpv;
								to_stg_tmp.referencecpv = row11.referencecpv;
								to_stg_tmp.libelleregionexecution = row11.libelleregionexecution;
								to_stg = to_stg_tmp;
							} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'doublons_stg'
// # Filter conditions 
							if (rejectedInnerJoin_tMap_4) {
								doublons_stg_tmp.recordid = row11.recordid;
								doublons_stg_tmp.denominationsocialeetablissement = row11.denominationsocialeetablissement;
								doublons_stg_tmp.idmarche = row11.idmarche;
								doublons_stg = doublons_stg_tmp;
							} // closing filter/reject
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_4 = false;

						tos_count_tMap_4++;

						/**
						 * [tMap_4 main ] stop
						 */

						/**
						 * [tMap_4 process_data_begin ] start
						 */

						currentComponent = "tMap_4";

						/**
						 * [tMap_4 process_data_begin ] stop
						 */
// Start of branch "to_stg"
						if (to_stg != null) {

							/**
							 * [tDBOutput_2 main ] start
							 */

							currentComponent = "tDBOutput_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "to_stg"

								);
							}

							whetherReject_tDBOutput_2 = false;
							if (to_stg.recordid == null) {
								pstmt_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_2.setString(1, to_stg.recordid);
							}

							int checkCount_tDBOutput_2 = -1;
							try (java.sql.ResultSet rs_tDBOutput_2 = pstmt_tDBOutput_2.executeQuery()) {
								while (rs_tDBOutput_2.next()) {
									checkCount_tDBOutput_2 = rs_tDBOutput_2.getInt(1);
								}
							}
							if (checkCount_tDBOutput_2 > 0) {
								if (to_stg.datepublicationdonnees != null) {
									pstmtUpdate_tDBOutput_2.setTimestamp(1,
											new java.sql.Timestamp(to_stg.datepublicationdonnees.getTime()));
								} else {
									pstmtUpdate_tDBOutput_2.setNull(1, java.sql.Types.TIMESTAMP);
								}

								if (to_stg.procedure == null) {
									pstmtUpdate_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(2, to_stg.procedure);
								}

								if (to_stg.nature == null) {
									pstmtUpdate_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(3, to_stg.nature);
								}

								if (to_stg.type == null) {
									pstmtUpdate_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(4, to_stg.type);
								}

								if (to_stg.datenotification != null) {
									pstmtUpdate_tDBOutput_2.setTimestamp(5,
											new java.sql.Timestamp(to_stg.datenotification.getTime()));
								} else {
									pstmtUpdate_tDBOutput_2.setNull(5, java.sql.Types.TIMESTAMP);
								}

								if (to_stg.montant == null) {
									pstmtUpdate_tDBOutput_2.setNull(6, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(6, to_stg.montant);
								}

								if (to_stg.siretetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(7, to_stg.siretetablissement);
								}

								if (to_stg.objetmarche == null) {
									pstmtUpdate_tDBOutput_2.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(8, to_stg.objetmarche);
								}

								if (to_stg.idacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(9, to_stg.idacheteur);
								}

								if (to_stg.codecommuneacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(10, to_stg.codecommuneacheteur);
								}

								if (to_stg.source == null) {
									pstmtUpdate_tDBOutput_2.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(11, to_stg.source);
								}

								if (to_stg.lieuexecutionnom == null) {
									pstmtUpdate_tDBOutput_2.setNull(12, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(12, to_stg.lieuexecutionnom);
								}

								if (to_stg.codearrondissementetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(13, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(13, to_stg.codearrondissementetablissement);
								}

								if (to_stg.libellearrondissementetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(14, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(14, to_stg.libellearrondissementetablissement);
								}

								if (to_stg.sirenetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(15, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(15, to_stg.sirenetablissement);
								}

								if (to_stg.codetypeetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(16, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(16, to_stg.codetypeetablissement);
								}

								if (to_stg.nicetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(17, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(17, to_stg.nicetablissement);
								}

								if (to_stg.lieuexecutiontypecode == null) {
									pstmtUpdate_tDBOutput_2.setNull(18, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(18, to_stg.lieuexecutiontypecode);
								}

								if (to_stg.libelleregionetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(19, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(19, to_stg.libelleregionetablissement);
								}

								if (to_stg.categorieetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(20, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(20, to_stg.categorieetablissement);
								}

								if (to_stg.nomacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(21, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(21, to_stg.nomacheteur);
								}

								if (to_stg.idmarche == null) {
									pstmtUpdate_tDBOutput_2.setNull(22, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(22, to_stg.idmarche);
								}

								if (to_stg.communeetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(23, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(23, to_stg.communeetablissement);
								}

								if (to_stg.libellecommuneacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(24, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(24, to_stg.libellecommuneacheteur);
								}

								if (to_stg.libelledepartementetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(25, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(25, to_stg.libelledepartementetablissement);
								}

								if (to_stg.dureemoisestimee == null) {
									pstmtUpdate_tDBOutput_2.setNull(26, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(26, to_stg.dureemoisestimee);
								}

								if (to_stg.libelleregionacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(27, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(27, to_stg.libelleregionacheteur);
								}

								if (to_stg.coderegionacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(28, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(28, to_stg.coderegionacheteur);
								}

								if (to_stg.siretetablissementvalide == null) {
									pstmtUpdate_tDBOutput_2.setNull(29, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(29, to_stg.siretetablissementvalide);
								}

								if (to_stg.codecommuneetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(30, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(30, to_stg.codecommuneetablissement);
								}

								if (to_stg.codepostalacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(31, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(31, to_stg.codepostalacheteur);
								}

								if (to_stg.departementetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(32, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(32, to_stg.departementetablissement);
								}

								if (to_stg.superficiecommuneacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(33, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(33, to_stg.superficiecommuneacheteur);
								}

								if (to_stg.populationcommuneacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(34, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(34, to_stg.populationcommuneacheteur);
								}

								if (to_stg.codedepartementexecution == null) {
									pstmtUpdate_tDBOutput_2.setNull(35, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(35, to_stg.codedepartementexecution);
								}

								if (to_stg.sirenacheteurvalide == null) {
									pstmtUpdate_tDBOutput_2.setNull(36, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(36, to_stg.sirenacheteurvalide);
								}

								if (to_stg.codearrondissementacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(37, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(37, to_stg.codearrondissementacheteur);
								}

								if (to_stg.sirenetablissementvalide == null) {
									pstmtUpdate_tDBOutput_2.setNull(38, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(38, to_stg.sirenetablissementvalide);
								}

								if (to_stg.dureemoiscalculee == null) {
									pstmtUpdate_tDBOutput_2.setNull(39, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(39, to_stg.dureemoiscalculee);
								}

								if (to_stg.populationcommuneetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(40, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(40, to_stg.populationcommuneetablissement);
								}

								if (to_stg.codecpv_division == null) {
									pstmtUpdate_tDBOutput_2.setNull(41, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(41, to_stg.codecpv_division);
								}

								if (to_stg.denominationsocialeetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(42, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(42, to_stg.denominationsocialeetablissement);
								}

								if (to_stg.coderegionetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(43, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(43, to_stg.coderegionetablissement);
								}

								if (to_stg.lieuexecutioncode == null) {
									pstmtUpdate_tDBOutput_2.setNull(44, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(44, to_stg.lieuexecutioncode);
								}

								if (to_stg.moisnotification == null) {
									pstmtUpdate_tDBOutput_2.setNull(45, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(45, to_stg.moisnotification);
								}

								if (to_stg.codecpv_original == null) {
									pstmtUpdate_tDBOutput_2.setNull(46, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(46, to_stg.codecpv_original);
								}

								if (to_stg.typeidentifiantetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(47, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(47, to_stg.typeidentifiantetablissement);
								}

								if (to_stg.dureemois == null) {
									pstmtUpdate_tDBOutput_2.setNull(48, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(48, to_stg.dureemois);
								}

								if (to_stg.libellearrondissementacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(49, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(49, to_stg.libellearrondissementacheteur);
								}

								if (to_stg.nombretitulairesurmarchepresume == null) {
									pstmtUpdate_tDBOutput_2.setNull(50, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(50, to_stg.nombretitulairesurmarchepresume);
								}

								if (to_stg.adresseetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(51, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(51, to_stg.adresseetablissement);
								}

								if (to_stg.superficiecommuneetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(52, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(52, to_stg.superficiecommuneetablissement);
								}

								if (to_stg.distanceacheteuretablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(53, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(53, to_stg.distanceacheteuretablissement);
								}

								if (to_stg.natureobjetmarche == null) {
									pstmtUpdate_tDBOutput_2.setNull(54, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(54, to_stg.natureobjetmarche);
								}

								if (to_stg.formeprix == null) {
									pstmtUpdate_tDBOutput_2.setNull(55, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(55, to_stg.formeprix);
								}

								if (to_stg.anneenotification == null) {
									pstmtUpdate_tDBOutput_2.setNull(56, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(56, to_stg.anneenotification);
								}

								if (to_stg.libelledepartementacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(57, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(57, to_stg.libelledepartementacheteur);
								}

								if (to_stg.departementacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(58, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(58, to_stg.departementacheteur);
								}

								if (to_stg.codepostaletablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(59, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(59, to_stg.codepostaletablissement);
								}

								if (to_stg.montantcalcule == null) {
									pstmtUpdate_tDBOutput_2.setNull(60, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(60, to_stg.montantcalcule);
								}

								if (to_stg.coderegionexecution == null) {
									pstmtUpdate_tDBOutput_2.setNull(61, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(61, to_stg.coderegionexecution);
								}

								if (to_stg.codecpv == null) {
									pstmtUpdate_tDBOutput_2.setNull(62, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(62, to_stg.codecpv);
								}

								if (to_stg.referencecpv == null) {
									pstmtUpdate_tDBOutput_2.setNull(63, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(63, to_stg.referencecpv);
								}

								if (to_stg.libelleregionexecution == null) {
									pstmtUpdate_tDBOutput_2.setNull(64, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(64, to_stg.libelleregionexecution);
								}

								if (to_stg.recordid == null) {
									pstmtUpdate_tDBOutput_2.setNull(65 + count_tDBOutput_2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(65 + count_tDBOutput_2, to_stg.recordid);
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
								if (to_stg.recordid == null) {
									pstmtInsert_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(1, to_stg.recordid);
								}

								if (to_stg.datepublicationdonnees != null) {
									pstmtInsert_tDBOutput_2.setTimestamp(2,
											new java.sql.Timestamp(to_stg.datepublicationdonnees.getTime()));
								} else {
									pstmtInsert_tDBOutput_2.setNull(2, java.sql.Types.TIMESTAMP);
								}

								if (to_stg.procedure == null) {
									pstmtInsert_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(3, to_stg.procedure);
								}

								if (to_stg.nature == null) {
									pstmtInsert_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(4, to_stg.nature);
								}

								if (to_stg.type == null) {
									pstmtInsert_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(5, to_stg.type);
								}

								if (to_stg.datenotification != null) {
									pstmtInsert_tDBOutput_2.setTimestamp(6,
											new java.sql.Timestamp(to_stg.datenotification.getTime()));
								} else {
									pstmtInsert_tDBOutput_2.setNull(6, java.sql.Types.TIMESTAMP);
								}

								if (to_stg.montant == null) {
									pstmtInsert_tDBOutput_2.setNull(7, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(7, to_stg.montant);
								}

								if (to_stg.siretetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(8, to_stg.siretetablissement);
								}

								if (to_stg.objetmarche == null) {
									pstmtInsert_tDBOutput_2.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(9, to_stg.objetmarche);
								}

								if (to_stg.idacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(10, to_stg.idacheteur);
								}

								if (to_stg.codecommuneacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(11, to_stg.codecommuneacheteur);
								}

								if (to_stg.source == null) {
									pstmtInsert_tDBOutput_2.setNull(12, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(12, to_stg.source);
								}

								if (to_stg.lieuexecutionnom == null) {
									pstmtInsert_tDBOutput_2.setNull(13, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(13, to_stg.lieuexecutionnom);
								}

								if (to_stg.codearrondissementetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(14, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_2.setInt(14, to_stg.codearrondissementetablissement);
								}

								if (to_stg.libellearrondissementetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(15, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_2.setInt(15, to_stg.libellearrondissementetablissement);
								}

								if (to_stg.sirenetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(16, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(16, to_stg.sirenetablissement);
								}

								if (to_stg.codetypeetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(17, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(17, to_stg.codetypeetablissement);
								}

								if (to_stg.nicetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(18, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(18, to_stg.nicetablissement);
								}

								if (to_stg.lieuexecutiontypecode == null) {
									pstmtInsert_tDBOutput_2.setNull(19, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(19, to_stg.lieuexecutiontypecode);
								}

								if (to_stg.libelleregionetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(20, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(20, to_stg.libelleregionetablissement);
								}

								if (to_stg.categorieetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(21, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(21, to_stg.categorieetablissement);
								}

								if (to_stg.nomacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(22, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(22, to_stg.nomacheteur);
								}

								if (to_stg.idmarche == null) {
									pstmtInsert_tDBOutput_2.setNull(23, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_2.setInt(23, to_stg.idmarche);
								}

								if (to_stg.communeetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(24, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(24, to_stg.communeetablissement);
								}

								if (to_stg.libellecommuneacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(25, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(25, to_stg.libellecommuneacheteur);
								}

								if (to_stg.libelledepartementetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(26, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(26, to_stg.libelledepartementetablissement);
								}

								if (to_stg.dureemoisestimee == null) {
									pstmtInsert_tDBOutput_2.setNull(27, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(27, to_stg.dureemoisestimee);
								}

								if (to_stg.libelleregionacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(28, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(28, to_stg.libelleregionacheteur);
								}

								if (to_stg.coderegionacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(29, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(29, to_stg.coderegionacheteur);
								}

								if (to_stg.siretetablissementvalide == null) {
									pstmtInsert_tDBOutput_2.setNull(30, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(30, to_stg.siretetablissementvalide);
								}

								if (to_stg.codecommuneetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(31, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(31, to_stg.codecommuneetablissement);
								}

								if (to_stg.codepostalacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(32, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(32, to_stg.codepostalacheteur);
								}

								if (to_stg.departementetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(33, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(33, to_stg.departementetablissement);
								}

								if (to_stg.superficiecommuneacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(34, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(34, to_stg.superficiecommuneacheteur);
								}

								if (to_stg.populationcommuneacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(35, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(35, to_stg.populationcommuneacheteur);
								}

								if (to_stg.codedepartementexecution == null) {
									pstmtInsert_tDBOutput_2.setNull(36, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(36, to_stg.codedepartementexecution);
								}

								if (to_stg.sirenacheteurvalide == null) {
									pstmtInsert_tDBOutput_2.setNull(37, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(37, to_stg.sirenacheteurvalide);
								}

								if (to_stg.codearrondissementacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(38, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_2.setInt(38, to_stg.codearrondissementacheteur);
								}

								if (to_stg.sirenetablissementvalide == null) {
									pstmtInsert_tDBOutput_2.setNull(39, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(39, to_stg.sirenetablissementvalide);
								}

								if (to_stg.dureemoiscalculee == null) {
									pstmtInsert_tDBOutput_2.setNull(40, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(40, to_stg.dureemoiscalculee);
								}

								if (to_stg.populationcommuneetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(41, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(41, to_stg.populationcommuneetablissement);
								}

								if (to_stg.codecpv_division == null) {
									pstmtInsert_tDBOutput_2.setNull(42, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_2.setInt(42, to_stg.codecpv_division);
								}

								if (to_stg.denominationsocialeetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(43, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(43, to_stg.denominationsocialeetablissement);
								}

								if (to_stg.coderegionetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(44, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_2.setInt(44, to_stg.coderegionetablissement);
								}

								if (to_stg.lieuexecutioncode == null) {
									pstmtInsert_tDBOutput_2.setNull(45, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(45, to_stg.lieuexecutioncode);
								}

								if (to_stg.moisnotification == null) {
									pstmtInsert_tDBOutput_2.setNull(46, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(46, to_stg.moisnotification);
								}

								if (to_stg.codecpv_original == null) {
									pstmtInsert_tDBOutput_2.setNull(47, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(47, to_stg.codecpv_original);
								}

								if (to_stg.typeidentifiantetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(48, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(48, to_stg.typeidentifiantetablissement);
								}

								if (to_stg.dureemois == null) {
									pstmtInsert_tDBOutput_2.setNull(49, java.sql.Types.INTEGER);
								} else {
									pstmtInsert_tDBOutput_2.setInt(49, to_stg.dureemois);
								}

								if (to_stg.libellearrondissementacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(50, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(50, to_stg.libellearrondissementacheteur);
								}

								if (to_stg.nombretitulairesurmarchepresume == null) {
									pstmtInsert_tDBOutput_2.setNull(51, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(51, to_stg.nombretitulairesurmarchepresume);
								}

								if (to_stg.adresseetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(52, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(52, to_stg.adresseetablissement);
								}

								if (to_stg.superficiecommuneetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(53, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(53, to_stg.superficiecommuneetablissement);
								}

								if (to_stg.distanceacheteuretablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(54, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(54, to_stg.distanceacheteuretablissement);
								}

								if (to_stg.natureobjetmarche == null) {
									pstmtInsert_tDBOutput_2.setNull(55, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(55, to_stg.natureobjetmarche);
								}

								if (to_stg.formeprix == null) {
									pstmtInsert_tDBOutput_2.setNull(56, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(56, to_stg.formeprix);
								}

								if (to_stg.anneenotification == null) {
									pstmtInsert_tDBOutput_2.setNull(57, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(57, to_stg.anneenotification);
								}

								if (to_stg.libelledepartementacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(58, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(58, to_stg.libelledepartementacheteur);
								}

								if (to_stg.departementacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(59, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(59, to_stg.departementacheteur);
								}

								if (to_stg.codepostaletablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(60, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(60, to_stg.codepostaletablissement);
								}

								if (to_stg.montantcalcule == null) {
									pstmtInsert_tDBOutput_2.setNull(61, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(61, to_stg.montantcalcule);
								}

								if (to_stg.coderegionexecution == null) {
									pstmtInsert_tDBOutput_2.setNull(62, java.sql.Types.FLOAT);
								} else {
									pstmtInsert_tDBOutput_2.setFloat(62, to_stg.coderegionexecution);
								}

								if (to_stg.codecpv == null) {
									pstmtInsert_tDBOutput_2.setNull(63, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(63, to_stg.codecpv);
								}

								if (to_stg.referencecpv == null) {
									pstmtInsert_tDBOutput_2.setNull(64, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(64, to_stg.referencecpv);
								}

								if (to_stg.libelleregionexecution == null) {
									pstmtInsert_tDBOutput_2.setNull(65, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(65, to_stg.libelleregionexecution);
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

						} // End of branch "to_stg"

// Start of branch "doublons_stg"
						if (doublons_stg != null) {

							/**
							 * [tFileOutputDelimited_3 main ] start
							 */

							currentComponent = "tFileOutputDelimited_3";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "doublons_stg"

								);
							}

							StringBuilder sb_tFileOutputDelimited_3 = new StringBuilder();
							if (doublons_stg.recordid != null) {
								sb_tFileOutputDelimited_3.append(doublons_stg.recordid);
							}
							sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
							if (doublons_stg.denominationsocialeetablissement != null) {
								sb_tFileOutputDelimited_3.append(doublons_stg.denominationsocialeetablissement);
							}
							sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
							if (doublons_stg.idmarche != null) {
								sb_tFileOutputDelimited_3.append(doublons_stg.idmarche);
							}
							sb_tFileOutputDelimited_3.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_3);

							nb_line_tFileOutputDelimited_3++;
							resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);

							outtFileOutputDelimited_3.write(sb_tFileOutputDelimited_3.toString());

							row1 = doublons_stg;

							tos_count_tFileOutputDelimited_3++;

							/**
							 * [tFileOutputDelimited_3 main ] stop
							 */

							/**
							 * [tFileOutputDelimited_3 process_data_begin ] start
							 */

							currentComponent = "tFileOutputDelimited_3";

							/**
							 * [tFileOutputDelimited_3 process_data_begin ] stop
							 */

							/**
							 * [tWarn_1 main ] start
							 */

							currentComponent = "tWarn_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							try {

								resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_1", "",
										Thread.currentThread().getId() + "", "WARN", "",
										"ATTENTION : tentative d'insertion de doublons dans la base de staging. Voir fichier de logs dans 'daily_staging_loading",
										"", "");
								tLogCatcher_1.addMessage("tWarn", "tWarn_1", 4,
										"ATTENTION : tentative d'insertion de doublons dans la base de staging. Voir fichier de logs dans 'daily_staging_loading",
										42);
								tLogCatcher_1Process(globalMap);
								globalMap.put("tWarn_1_WARN_MESSAGES",
										"ATTENTION : tentative d'insertion de doublons dans la base de staging. Voir fichier de logs dans 'daily_staging_loading");
								globalMap.put("tWarn_1_WARN_PRIORITY", 4);
								globalMap.put("tWarn_1_WARN_CODE", 42);

							} catch (Exception e_tWarn_1) {
								globalMap.put("tWarn_1_ERROR_MESSAGE", e_tWarn_1.getMessage());
								logIgnoredError(
										String.format("tWarn_1 - tWarn failed to log message due to internal error: %s",
												e_tWarn_1),
										e_tWarn_1);
							}

							tos_count_tWarn_1++;

							/**
							 * [tWarn_1 main ] stop
							 */

							/**
							 * [tWarn_1 process_data_begin ] start
							 */

							currentComponent = "tWarn_1";

							/**
							 * [tWarn_1 process_data_begin ] stop
							 */

							/**
							 * [tWarn_1 process_data_end ] start
							 */

							currentComponent = "tWarn_1";

							/**
							 * [tWarn_1 process_data_end ] stop
							 */

							/**
							 * [tFileOutputDelimited_3 process_data_end ] start
							 */

							currentComponent = "tFileOutputDelimited_3";

							/**
							 * [tFileOutputDelimited_3 process_data_end ] stop
							 */

						} // End of branch "doublons_stg"

						/**
						 * [tMap_4 process_data_end ] start
						 */

						currentComponent = "tMap_4";

						/**
						 * [tMap_4 process_data_end ] stop
						 */

						/**
						 * [tWarn_5 process_data_end ] start
						 */

						currentComponent = "tWarn_5";

						/**
						 * [tWarn_5 process_data_end ] stop
						 */

					} // End of branch "row10"

					/**
					 * [tFileInputJSON_2 process_data_end ] start
					 */

					currentComponent = "tFileInputJSON_2";

					/**
					 * [tFileInputJSON_2 process_data_end ] stop
					 */

					/**
					 * [tFileInputJSON_2 end ] start
					 */

					currentComponent = "tFileInputJSON_2";

				}
				globalMap.put("tFileInputJSON_2_NB_LINE", nb_line_tFileInputJSON_2);

				ok_Hash.put("tFileInputJSON_2", true);
				end_Hash.put("tFileInputJSON_2", System.currentTimeMillis());

				/**
				 * [tFileInputJSON_2 end ] stop
				 */

				/**
				 * [tWarn_5 end ] start
				 */

				currentComponent = "tWarn_5";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row10");
				}

				ok_Hash.put("tWarn_5", true);
				end_Hash.put("tWarn_5", System.currentTimeMillis());

				/**
				 * [tWarn_5 end ] stop
				 */

				/**
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row12 != null) {
					tHash_Lookup_row12.endGet();
				}
				globalMap.remove("tHash_Lookup_row12");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row11");
				}

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "to_stg");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 end ] start
				 */

				currentComponent = "tFileOutputDelimited_3";

				if (outtFileOutputDelimited_3 != null) {
					outtFileOutputDelimited_3.flush();
					outtFileOutputDelimited_3.close();
				}

				globalMap.put("tFileOutputDelimited_3_NB_LINE", nb_line_tFileOutputDelimited_3);
				globalMap.put("tFileOutputDelimited_3_FILE_NAME", fileName_tFileOutputDelimited_3);

				resourceMap.put("finish_tFileOutputDelimited_3", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "doublons_stg");
				}

				ok_Hash.put("tFileOutputDelimited_3", true);
				end_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_3 end ] stop
				 */

				/**
				 * [tWarn_1 end ] start
				 */

				currentComponent = "tWarn_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tWarn_1", true);
				end_Hash.put("tWarn_1", System.currentTimeMillis());

				/**
				 * [tWarn_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_4"
			globalMap.remove("tHash_Lookup_row12");

			try {

				/**
				 * [tFileInputJSON_2 finally ] start
				 */

				currentComponent = "tFileInputJSON_2";

				/**
				 * [tFileInputJSON_2 finally ] stop
				 */

				/**
				 * [tWarn_5 finally ] start
				 */

				currentComponent = "tWarn_5";

				/**
				 * [tWarn_5 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
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

				/**
				 * [tFileOutputDelimited_3 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_3";

				if (resourceMap.get("finish_tFileOutputDelimited_3") == null) {

					java.io.Writer outtFileOutputDelimited_3 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_3");
					if (outtFileOutputDelimited_3 != null) {
						outtFileOutputDelimited_3.flush();
						outtFileOutputDelimited_3.close();
					}

				}

				/**
				 * [tFileOutputDelimited_3 finally ] stop
				 */

				/**
				 * [tWarn_1 finally ] start
				 */

				currentComponent = "tWarn_1";

				/**
				 * [tWarn_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputJSON_2_SUBPROCESS_STATE", 1);
	}

	public static class row12Struct implements routines.system.IPersistableComparableLookupRow<row12Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_daily_loading_staging = new byte[0];
		static byte[] commonByteArray_CITYVIZ_daily_loading_staging = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String recordid;

		public String getRecordid() {
			return this.recordid;
		}

		public java.util.Date datepublicationdonnees;

		public java.util.Date getDatepublicationdonnees() {
			return this.datepublicationdonnees;
		}

		public String procedure;

		public String getProcedure() {
			return this.procedure;
		}

		public String nature;

		public String getNature() {
			return this.nature;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		public java.util.Date datenotification;

		public java.util.Date getDatenotification() {
			return this.datenotification;
		}

		public Float montant;

		public Float getMontant() {
			return this.montant;
		}

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public String objetmarche;

		public String getObjetmarche() {
			return this.objetmarche;
		}

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public String codecommuneacheteur;

		public String getCodecommuneacheteur() {
			return this.codecommuneacheteur;
		}

		public String source;

		public String getSource() {
			return this.source;
		}

		public String lieuexecutionnom;

		public String getLieuexecutionnom() {
			return this.lieuexecutionnom;
		}

		public Integer codearrondissementetablissement;

		public Integer getCodearrondissementetablissement() {
			return this.codearrondissementetablissement;
		}

		public Integer libellearrondissementetablissement;

		public Integer getLibellearrondissementetablissement() {
			return this.libellearrondissementetablissement;
		}

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String codetypeetablissement;

		public String getCodetypeetablissement() {
			return this.codetypeetablissement;
		}

		public String nicetablissement;

		public String getNicetablissement() {
			return this.nicetablissement;
		}

		public String lieuexecutiontypecode;

		public String getLieuexecutiontypecode() {
			return this.lieuexecutiontypecode;
		}

		public String libelleregionetablissement;

		public String getLibelleregionetablissement() {
			return this.libelleregionetablissement;
		}

		public String categorieetablissement;

		public String getCategorieetablissement() {
			return this.categorieetablissement;
		}

		public String nomacheteur;

		public String getNomacheteur() {
			return this.nomacheteur;
		}

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public String libellecommuneacheteur;

		public String getLibellecommuneacheteur() {
			return this.libellecommuneacheteur;
		}

		public String libelledepartementetablissement;

		public String getLibelledepartementetablissement() {
			return this.libelledepartementetablissement;
		}

		public String dureemoisestimee;

		public String getDureemoisestimee() {
			return this.dureemoisestimee;
		}

		public String libelleregionacheteur;

		public String getLibelleregionacheteur() {
			return this.libelleregionacheteur;
		}

		public String coderegionacheteur;

		public String getCoderegionacheteur() {
			return this.coderegionacheteur;
		}

		public String siretetablissementvalide;

		public String getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public String codecommuneetablissement;

		public String getCodecommuneetablissement() {
			return this.codecommuneetablissement;
		}

		public String codepostalacheteur;

		public String getCodepostalacheteur() {
			return this.codepostalacheteur;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public String superficiecommuneacheteur;

		public String getSuperficiecommuneacheteur() {
			return this.superficiecommuneacheteur;
		}

		public Float populationcommuneacheteur;

		public Float getPopulationcommuneacheteur() {
			return this.populationcommuneacheteur;
		}

		public Float codedepartementexecution;

		public Float getCodedepartementexecution() {
			return this.codedepartementexecution;
		}

		public String sirenacheteurvalide;

		public String getSirenacheteurvalide() {
			return this.sirenacheteurvalide;
		}

		public Integer codearrondissementacheteur;

		public Integer getCodearrondissementacheteur() {
			return this.codearrondissementacheteur;
		}

		public String sirenetablissementvalide;

		public String getSirenetablissementvalide() {
			return this.sirenetablissementvalide;
		}

		public Float dureemoiscalculee;

		public Float getDureemoiscalculee() {
			return this.dureemoiscalculee;
		}

		public Float populationcommuneetablissement;

		public Float getPopulationcommuneetablissement() {
			return this.populationcommuneetablissement;
		}

		public Integer codecpv_division;

		public Integer getCodecpv_division() {
			return this.codecpv_division;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public Integer coderegionetablissement;

		public Integer getCoderegionetablissement() {
			return this.coderegionetablissement;
		}

		public String lieuexecutioncode;

		public String getLieuexecutioncode() {
			return this.lieuexecutioncode;
		}

		public String moisnotification;

		public String getMoisnotification() {
			return this.moisnotification;
		}

		public String codecpv_original;

		public String getCodecpv_original() {
			return this.codecpv_original;
		}

		public String typeidentifiantetablissement;

		public String getTypeidentifiantetablissement() {
			return this.typeidentifiantetablissement;
		}

		public Integer dureemois;

		public Integer getDureemois() {
			return this.dureemois;
		}

		public String libellearrondissementacheteur;

		public String getLibellearrondissementacheteur() {
			return this.libellearrondissementacheteur;
		}

		public Float nombretitulairesurmarchepresume;

		public Float getNombretitulairesurmarchepresume() {
			return this.nombretitulairesurmarchepresume;
		}

		public String adresseetablissement;

		public String getAdresseetablissement() {
			return this.adresseetablissement;
		}

		public Float superficiecommuneetablissement;

		public Float getSuperficiecommuneetablissement() {
			return this.superficiecommuneetablissement;
		}

		public Float distanceacheteuretablissement;

		public Float getDistanceacheteuretablissement() {
			return this.distanceacheteuretablissement;
		}

		public String natureobjetmarche;

		public String getNatureobjetmarche() {
			return this.natureobjetmarche;
		}

		public String formeprix;

		public String getFormeprix() {
			return this.formeprix;
		}

		public String anneenotification;

		public String getAnneenotification() {
			return this.anneenotification;
		}

		public String libelledepartementacheteur;

		public String getLibelledepartementacheteur() {
			return this.libelledepartementacheteur;
		}

		public String departementacheteur;

		public String getDepartementacheteur() {
			return this.departementacheteur;
		}

		public String codepostaletablissement;

		public String getCodepostaletablissement() {
			return this.codepostaletablissement;
		}

		public Float montantcalcule;

		public Float getMontantcalcule() {
			return this.montantcalcule;
		}

		public Float coderegionexecution;

		public Float getCoderegionexecution() {
			return this.coderegionexecution;
		}

		public String codecpv;

		public String getCodecpv() {
			return this.codecpv;
		}

		public String referencecpv;

		public String getReferencecpv() {
			return this.referencecpv;
		}

		public String libelleregionexecution;

		public String getLibelleregionexecution() {
			return this.libelleregionexecution;
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
			final row12Struct other = (row12Struct) obj;

			if (this.recordid == null) {
				if (other.recordid != null)
					return false;

			} else if (!this.recordid.equals(other.recordid))

				return false;

			return true;
		}

		public void copyDataTo(row12Struct other) {

			other.recordid = this.recordid;
			other.datepublicationdonnees = this.datepublicationdonnees;
			other.procedure = this.procedure;
			other.nature = this.nature;
			other.type = this.type;
			other.datenotification = this.datenotification;
			other.montant = this.montant;
			other.siretetablissement = this.siretetablissement;
			other.objetmarche = this.objetmarche;
			other.idacheteur = this.idacheteur;
			other.codecommuneacheteur = this.codecommuneacheteur;
			other.source = this.source;
			other.lieuexecutionnom = this.lieuexecutionnom;
			other.codearrondissementetablissement = this.codearrondissementetablissement;
			other.libellearrondissementetablissement = this.libellearrondissementetablissement;
			other.sirenetablissement = this.sirenetablissement;
			other.codetypeetablissement = this.codetypeetablissement;
			other.nicetablissement = this.nicetablissement;
			other.lieuexecutiontypecode = this.lieuexecutiontypecode;
			other.libelleregionetablissement = this.libelleregionetablissement;
			other.categorieetablissement = this.categorieetablissement;
			other.nomacheteur = this.nomacheteur;
			other.idmarche = this.idmarche;
			other.communeetablissement = this.communeetablissement;
			other.libellecommuneacheteur = this.libellecommuneacheteur;
			other.libelledepartementetablissement = this.libelledepartementetablissement;
			other.dureemoisestimee = this.dureemoisestimee;
			other.libelleregionacheteur = this.libelleregionacheteur;
			other.coderegionacheteur = this.coderegionacheteur;
			other.siretetablissementvalide = this.siretetablissementvalide;
			other.codecommuneetablissement = this.codecommuneetablissement;
			other.codepostalacheteur = this.codepostalacheteur;
			other.departementetablissement = this.departementetablissement;
			other.superficiecommuneacheteur = this.superficiecommuneacheteur;
			other.populationcommuneacheteur = this.populationcommuneacheteur;
			other.codedepartementexecution = this.codedepartementexecution;
			other.sirenacheteurvalide = this.sirenacheteurvalide;
			other.codearrondissementacheteur = this.codearrondissementacheteur;
			other.sirenetablissementvalide = this.sirenetablissementvalide;
			other.dureemoiscalculee = this.dureemoiscalculee;
			other.populationcommuneetablissement = this.populationcommuneetablissement;
			other.codecpv_division = this.codecpv_division;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.coderegionetablissement = this.coderegionetablissement;
			other.lieuexecutioncode = this.lieuexecutioncode;
			other.moisnotification = this.moisnotification;
			other.codecpv_original = this.codecpv_original;
			other.typeidentifiantetablissement = this.typeidentifiantetablissement;
			other.dureemois = this.dureemois;
			other.libellearrondissementacheteur = this.libellearrondissementacheteur;
			other.nombretitulairesurmarchepresume = this.nombretitulairesurmarchepresume;
			other.adresseetablissement = this.adresseetablissement;
			other.superficiecommuneetablissement = this.superficiecommuneetablissement;
			other.distanceacheteuretablissement = this.distanceacheteuretablissement;
			other.natureobjetmarche = this.natureobjetmarche;
			other.formeprix = this.formeprix;
			other.anneenotification = this.anneenotification;
			other.libelledepartementacheteur = this.libelledepartementacheteur;
			other.departementacheteur = this.departementacheteur;
			other.codepostaletablissement = this.codepostaletablissement;
			other.montantcalcule = this.montantcalcule;
			other.coderegionexecution = this.coderegionexecution;
			other.codecpv = this.codecpv;
			other.referencecpv = this.referencecpv;
			other.libelleregionexecution = this.libelleregionexecution;

		}

		public void copyKeysDataTo(row12Struct other) {

			other.recordid = this.recordid;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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

		private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
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

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.recordid = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.recordid = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.recordid, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.recordid, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.datepublicationdonnees = readDate(dis, ois);

				this.procedure = readString(dis, ois);

				this.nature = readString(dis, ois);

				this.type = readString(dis, ois);

				this.datenotification = readDate(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.montant = null;
				} else {
					this.montant = dis.readFloat();
				}

				this.siretetablissement = readString(dis, ois);

				this.objetmarche = readString(dis, ois);

				this.idacheteur = readString(dis, ois);

				this.codecommuneacheteur = readString(dis, ois);

				this.source = readString(dis, ois);

				this.lieuexecutionnom = readString(dis, ois);

				this.codearrondissementetablissement = readInteger(dis, ois);

				this.libellearrondissementetablissement = readInteger(dis, ois);

				this.sirenetablissement = readString(dis, ois);

				this.codetypeetablissement = readString(dis, ois);

				this.nicetablissement = readString(dis, ois);

				this.lieuexecutiontypecode = readString(dis, ois);

				this.libelleregionetablissement = readString(dis, ois);

				this.categorieetablissement = readString(dis, ois);

				this.nomacheteur = readString(dis, ois);

				this.idmarche = readInteger(dis, ois);

				this.communeetablissement = readString(dis, ois);

				this.libellecommuneacheteur = readString(dis, ois);

				this.libelledepartementetablissement = readString(dis, ois);

				this.dureemoisestimee = readString(dis, ois);

				this.libelleregionacheteur = readString(dis, ois);

				this.coderegionacheteur = readString(dis, ois);

				this.siretetablissementvalide = readString(dis, ois);

				this.codecommuneetablissement = readString(dis, ois);

				this.codepostalacheteur = readString(dis, ois);

				this.departementetablissement = readString(dis, ois);

				this.superficiecommuneacheteur = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.populationcommuneacheteur = null;
				} else {
					this.populationcommuneacheteur = dis.readFloat();
				}

				length = dis.readByte();
				if (length == -1) {
					this.codedepartementexecution = null;
				} else {
					this.codedepartementexecution = dis.readFloat();
				}

				this.sirenacheteurvalide = readString(dis, ois);

				this.codearrondissementacheteur = readInteger(dis, ois);

				this.sirenetablissementvalide = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.dureemoiscalculee = null;
				} else {
					this.dureemoiscalculee = dis.readFloat();
				}

				length = dis.readByte();
				if (length == -1) {
					this.populationcommuneetablissement = null;
				} else {
					this.populationcommuneetablissement = dis.readFloat();
				}

				this.codecpv_division = readInteger(dis, ois);

				this.denominationsocialeetablissement = readString(dis, ois);

				this.coderegionetablissement = readInteger(dis, ois);

				this.lieuexecutioncode = readString(dis, ois);

				this.moisnotification = readString(dis, ois);

				this.codecpv_original = readString(dis, ois);

				this.typeidentifiantetablissement = readString(dis, ois);

				this.dureemois = readInteger(dis, ois);

				this.libellearrondissementacheteur = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.nombretitulairesurmarchepresume = null;
				} else {
					this.nombretitulairesurmarchepresume = dis.readFloat();
				}

				this.adresseetablissement = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.superficiecommuneetablissement = null;
				} else {
					this.superficiecommuneetablissement = dis.readFloat();
				}

				length = dis.readByte();
				if (length == -1) {
					this.distanceacheteuretablissement = null;
				} else {
					this.distanceacheteuretablissement = dis.readFloat();
				}

				this.natureobjetmarche = readString(dis, ois);

				this.formeprix = readString(dis, ois);

				this.anneenotification = readString(dis, ois);

				this.libelledepartementacheteur = readString(dis, ois);

				this.departementacheteur = readString(dis, ois);

				this.codepostaletablissement = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.montantcalcule = null;
				} else {
					this.montantcalcule = dis.readFloat();
				}

				length = dis.readByte();
				if (length == -1) {
					this.coderegionexecution = null;
				} else {
					this.coderegionexecution = dis.readFloat();
				}

				this.codecpv = readString(dis, ois);

				this.referencecpv = readString(dis, ois);

				this.libelleregionexecution = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.datepublicationdonnees = readDate(dis, objectIn);

				this.procedure = readString(dis, objectIn);

				this.nature = readString(dis, objectIn);

				this.type = readString(dis, objectIn);

				this.datenotification = readDate(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.montant = null;
				} else {
					this.montant = objectIn.readFloat();
				}

				this.siretetablissement = readString(dis, objectIn);

				this.objetmarche = readString(dis, objectIn);

				this.idacheteur = readString(dis, objectIn);

				this.codecommuneacheteur = readString(dis, objectIn);

				this.source = readString(dis, objectIn);

				this.lieuexecutionnom = readString(dis, objectIn);

				this.codearrondissementetablissement = readInteger(dis, objectIn);

				this.libellearrondissementetablissement = readInteger(dis, objectIn);

				this.sirenetablissement = readString(dis, objectIn);

				this.codetypeetablissement = readString(dis, objectIn);

				this.nicetablissement = readString(dis, objectIn);

				this.lieuexecutiontypecode = readString(dis, objectIn);

				this.libelleregionetablissement = readString(dis, objectIn);

				this.categorieetablissement = readString(dis, objectIn);

				this.nomacheteur = readString(dis, objectIn);

				this.idmarche = readInteger(dis, objectIn);

				this.communeetablissement = readString(dis, objectIn);

				this.libellecommuneacheteur = readString(dis, objectIn);

				this.libelledepartementetablissement = readString(dis, objectIn);

				this.dureemoisestimee = readString(dis, objectIn);

				this.libelleregionacheteur = readString(dis, objectIn);

				this.coderegionacheteur = readString(dis, objectIn);

				this.siretetablissementvalide = readString(dis, objectIn);

				this.codecommuneetablissement = readString(dis, objectIn);

				this.codepostalacheteur = readString(dis, objectIn);

				this.departementetablissement = readString(dis, objectIn);

				this.superficiecommuneacheteur = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.populationcommuneacheteur = null;
				} else {
					this.populationcommuneacheteur = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.codedepartementexecution = null;
				} else {
					this.codedepartementexecution = objectIn.readFloat();
				}

				this.sirenacheteurvalide = readString(dis, objectIn);

				this.codearrondissementacheteur = readInteger(dis, objectIn);

				this.sirenetablissementvalide = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.dureemoiscalculee = null;
				} else {
					this.dureemoiscalculee = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.populationcommuneetablissement = null;
				} else {
					this.populationcommuneetablissement = objectIn.readFloat();
				}

				this.codecpv_division = readInteger(dis, objectIn);

				this.denominationsocialeetablissement = readString(dis, objectIn);

				this.coderegionetablissement = readInteger(dis, objectIn);

				this.lieuexecutioncode = readString(dis, objectIn);

				this.moisnotification = readString(dis, objectIn);

				this.codecpv_original = readString(dis, objectIn);

				this.typeidentifiantetablissement = readString(dis, objectIn);

				this.dureemois = readInteger(dis, objectIn);

				this.libellearrondissementacheteur = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.nombretitulairesurmarchepresume = null;
				} else {
					this.nombretitulairesurmarchepresume = objectIn.readFloat();
				}

				this.adresseetablissement = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.superficiecommuneetablissement = null;
				} else {
					this.superficiecommuneetablissement = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.distanceacheteuretablissement = null;
				} else {
					this.distanceacheteuretablissement = objectIn.readFloat();
				}

				this.natureobjetmarche = readString(dis, objectIn);

				this.formeprix = readString(dis, objectIn);

				this.anneenotification = readString(dis, objectIn);

				this.libelledepartementacheteur = readString(dis, objectIn);

				this.departementacheteur = readString(dis, objectIn);

				this.codepostaletablissement = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.montantcalcule = null;
				} else {
					this.montantcalcule = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.coderegionexecution = null;
				} else {
					this.coderegionexecution = objectIn.readFloat();
				}

				this.codecpv = readString(dis, objectIn);

				this.referencecpv = readString(dis, objectIn);

				this.libelleregionexecution = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeDate(this.datepublicationdonnees, dos, oos);

				writeString(this.procedure, dos, oos);

				writeString(this.nature, dos, oos);

				writeString(this.type, dos, oos);

				writeDate(this.datenotification, dos, oos);

				if (this.montant == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montant);
				}

				writeString(this.siretetablissement, dos, oos);

				writeString(this.objetmarche, dos, oos);

				writeString(this.idacheteur, dos, oos);

				writeString(this.codecommuneacheteur, dos, oos);

				writeString(this.source, dos, oos);

				writeString(this.lieuexecutionnom, dos, oos);

				writeInteger(this.codearrondissementetablissement, dos, oos);

				writeInteger(this.libellearrondissementetablissement, dos, oos);

				writeString(this.sirenetablissement, dos, oos);

				writeString(this.codetypeetablissement, dos, oos);

				writeString(this.nicetablissement, dos, oos);

				writeString(this.lieuexecutiontypecode, dos, oos);

				writeString(this.libelleregionetablissement, dos, oos);

				writeString(this.categorieetablissement, dos, oos);

				writeString(this.nomacheteur, dos, oos);

				writeInteger(this.idmarche, dos, oos);

				writeString(this.communeetablissement, dos, oos);

				writeString(this.libellecommuneacheteur, dos, oos);

				writeString(this.libelledepartementetablissement, dos, oos);

				writeString(this.dureemoisestimee, dos, oos);

				writeString(this.libelleregionacheteur, dos, oos);

				writeString(this.coderegionacheteur, dos, oos);

				writeString(this.siretetablissementvalide, dos, oos);

				writeString(this.codecommuneetablissement, dos, oos);

				writeString(this.codepostalacheteur, dos, oos);

				writeString(this.departementetablissement, dos, oos);

				writeString(this.superficiecommuneacheteur, dos, oos);

				if (this.populationcommuneacheteur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneacheteur);
				}

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				writeString(this.sirenacheteurvalide, dos, oos);

				writeInteger(this.codearrondissementacheteur, dos, oos);

				writeString(this.sirenetablissementvalide, dos, oos);

				if (this.dureemoiscalculee == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.dureemoiscalculee);
				}

				if (this.populationcommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.populationcommuneetablissement);
				}

				writeInteger(this.codecpv_division, dos, oos);

				writeString(this.denominationsocialeetablissement, dos, oos);

				writeInteger(this.coderegionetablissement, dos, oos);

				writeString(this.lieuexecutioncode, dos, oos);

				writeString(this.moisnotification, dos, oos);

				writeString(this.codecpv_original, dos, oos);

				writeString(this.typeidentifiantetablissement, dos, oos);

				writeInteger(this.dureemois, dos, oos);

				writeString(this.libellearrondissementacheteur, dos, oos);

				if (this.nombretitulairesurmarchepresume == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.nombretitulairesurmarchepresume);
				}

				writeString(this.adresseetablissement, dos, oos);

				if (this.superficiecommuneetablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.superficiecommuneetablissement);
				}

				if (this.distanceacheteuretablissement == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.distanceacheteuretablissement);
				}

				writeString(this.natureobjetmarche, dos, oos);

				writeString(this.formeprix, dos, oos);

				writeString(this.anneenotification, dos, oos);

				writeString(this.libelledepartementacheteur, dos, oos);

				writeString(this.departementacheteur, dos, oos);

				writeString(this.codepostaletablissement, dos, oos);

				if (this.montantcalcule == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalcule);
				}

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				writeString(this.codecpv, dos, oos);

				writeString(this.referencecpv, dos, oos);

				writeString(this.libelleregionexecution, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeDate(this.datepublicationdonnees, dos, objectOut);

				writeString(this.procedure, dos, objectOut);

				writeString(this.nature, dos, objectOut);

				writeString(this.type, dos, objectOut);

				writeDate(this.datenotification, dos, objectOut);

				if (this.montant == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.montant);
				}

				writeString(this.siretetablissement, dos, objectOut);

				writeString(this.objetmarche, dos, objectOut);

				writeString(this.idacheteur, dos, objectOut);

				writeString(this.codecommuneacheteur, dos, objectOut);

				writeString(this.source, dos, objectOut);

				writeString(this.lieuexecutionnom, dos, objectOut);

				writeInteger(this.codearrondissementetablissement, dos, objectOut);

				writeInteger(this.libellearrondissementetablissement, dos, objectOut);

				writeString(this.sirenetablissement, dos, objectOut);

				writeString(this.codetypeetablissement, dos, objectOut);

				writeString(this.nicetablissement, dos, objectOut);

				writeString(this.lieuexecutiontypecode, dos, objectOut);

				writeString(this.libelleregionetablissement, dos, objectOut);

				writeString(this.categorieetablissement, dos, objectOut);

				writeString(this.nomacheteur, dos, objectOut);

				writeInteger(this.idmarche, dos, objectOut);

				writeString(this.communeetablissement, dos, objectOut);

				writeString(this.libellecommuneacheteur, dos, objectOut);

				writeString(this.libelledepartementetablissement, dos, objectOut);

				writeString(this.dureemoisestimee, dos, objectOut);

				writeString(this.libelleregionacheteur, dos, objectOut);

				writeString(this.coderegionacheteur, dos, objectOut);

				writeString(this.siretetablissementvalide, dos, objectOut);

				writeString(this.codecommuneetablissement, dos, objectOut);

				writeString(this.codepostalacheteur, dos, objectOut);

				writeString(this.departementetablissement, dos, objectOut);

				writeString(this.superficiecommuneacheteur, dos, objectOut);

				if (this.populationcommuneacheteur == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.populationcommuneacheteur);
				}

				if (this.codedepartementexecution == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.codedepartementexecution);
				}

				writeString(this.sirenacheteurvalide, dos, objectOut);

				writeInteger(this.codearrondissementacheteur, dos, objectOut);

				writeString(this.sirenetablissementvalide, dos, objectOut);

				if (this.dureemoiscalculee == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.dureemoiscalculee);
				}

				if (this.populationcommuneetablissement == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.populationcommuneetablissement);
				}

				writeInteger(this.codecpv_division, dos, objectOut);

				writeString(this.denominationsocialeetablissement, dos, objectOut);

				writeInteger(this.coderegionetablissement, dos, objectOut);

				writeString(this.lieuexecutioncode, dos, objectOut);

				writeString(this.moisnotification, dos, objectOut);

				writeString(this.codecpv_original, dos, objectOut);

				writeString(this.typeidentifiantetablissement, dos, objectOut);

				writeInteger(this.dureemois, dos, objectOut);

				writeString(this.libellearrondissementacheteur, dos, objectOut);

				if (this.nombretitulairesurmarchepresume == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.nombretitulairesurmarchepresume);
				}

				writeString(this.adresseetablissement, dos, objectOut);

				if (this.superficiecommuneetablissement == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.superficiecommuneetablissement);
				}

				if (this.distanceacheteuretablissement == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.distanceacheteuretablissement);
				}

				writeString(this.natureobjetmarche, dos, objectOut);

				writeString(this.formeprix, dos, objectOut);

				writeString(this.anneenotification, dos, objectOut);

				writeString(this.libelledepartementacheteur, dos, objectOut);

				writeString(this.departementacheteur, dos, objectOut);

				writeString(this.codepostaletablissement, dos, objectOut);

				if (this.montantcalcule == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.montantcalcule);
				}

				if (this.coderegionexecution == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.coderegionexecution);
				}

				writeString(this.codecpv, dos, objectOut);

				writeString(this.referencecpv, dos, objectOut);

				writeString(this.libelleregionexecution, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("recordid=" + recordid);
			sb.append(",datepublicationdonnees=" + String.valueOf(datepublicationdonnees));
			sb.append(",procedure=" + procedure);
			sb.append(",nature=" + nature);
			sb.append(",type=" + type);
			sb.append(",datenotification=" + String.valueOf(datenotification));
			sb.append(",montant=" + String.valueOf(montant));
			sb.append(",siretetablissement=" + siretetablissement);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",idacheteur=" + idacheteur);
			sb.append(",codecommuneacheteur=" + codecommuneacheteur);
			sb.append(",source=" + source);
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",codearrondissementetablissement=" + String.valueOf(codearrondissementetablissement));
			sb.append(",libellearrondissementetablissement=" + String.valueOf(libellearrondissementetablissement));
			sb.append(",sirenetablissement=" + sirenetablissement);
			sb.append(",codetypeetablissement=" + codetypeetablissement);
			sb.append(",nicetablissement=" + nicetablissement);
			sb.append(",lieuexecutiontypecode=" + lieuexecutiontypecode);
			sb.append(",libelleregionetablissement=" + libelleregionetablissement);
			sb.append(",categorieetablissement=" + categorieetablissement);
			sb.append(",nomacheteur=" + nomacheteur);
			sb.append(",idmarche=" + String.valueOf(idmarche));
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",libellecommuneacheteur=" + libellecommuneacheteur);
			sb.append(",libelledepartementetablissement=" + libelledepartementetablissement);
			sb.append(",dureemoisestimee=" + dureemoisestimee);
			sb.append(",libelleregionacheteur=" + libelleregionacheteur);
			sb.append(",coderegionacheteur=" + coderegionacheteur);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",codecommuneetablissement=" + codecommuneetablissement);
			sb.append(",codepostalacheteur=" + codepostalacheteur);
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",superficiecommuneacheteur=" + superficiecommuneacheteur);
			sb.append(",populationcommuneacheteur=" + String.valueOf(populationcommuneacheteur));
			sb.append(",codedepartementexecution=" + String.valueOf(codedepartementexecution));
			sb.append(",sirenacheteurvalide=" + sirenacheteurvalide);
			sb.append(",codearrondissementacheteur=" + String.valueOf(codearrondissementacheteur));
			sb.append(",sirenetablissementvalide=" + sirenetablissementvalide);
			sb.append(",dureemoiscalculee=" + String.valueOf(dureemoiscalculee));
			sb.append(",populationcommuneetablissement=" + String.valueOf(populationcommuneetablissement));
			sb.append(",codecpv_division=" + String.valueOf(codecpv_division));
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",coderegionetablissement=" + String.valueOf(coderegionetablissement));
			sb.append(",lieuexecutioncode=" + lieuexecutioncode);
			sb.append(",moisnotification=" + moisnotification);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",typeidentifiantetablissement=" + typeidentifiantetablissement);
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",libellearrondissementacheteur=" + libellearrondissementacheteur);
			sb.append(",nombretitulairesurmarchepresume=" + String.valueOf(nombretitulairesurmarchepresume));
			sb.append(",adresseetablissement=" + adresseetablissement);
			sb.append(",superficiecommuneetablissement=" + String.valueOf(superficiecommuneetablissement));
			sb.append(",distanceacheteuretablissement=" + String.valueOf(distanceacheteuretablissement));
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",formeprix=" + formeprix);
			sb.append(",anneenotification=" + anneenotification);
			sb.append(",libelledepartementacheteur=" + libelledepartementacheteur);
			sb.append(",departementacheteur=" + departementacheteur);
			sb.append(",codepostaletablissement=" + codepostaletablissement);
			sb.append(",montantcalcule=" + String.valueOf(montantcalcule));
			sb.append(",coderegionexecution=" + String.valueOf(coderegionexecution));
			sb.append(",codecpv=" + codecpv);
			sb.append(",referencecpv=" + referencecpv);
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row12Struct other) {

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

	public void tDBInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 0);

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

				row12Struct row12 = new row12Struct();

				/**
				 * [tAdvancedHash_row12 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row12", false);
				start_Hash.put("tAdvancedHash_row12", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row12";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row12");
				}

				int tos_count_tAdvancedHash_row12 = 0;

				// connection name:row12
				// source node:tDBInput_2 - inputs:() outputs:(row12,row12) | target
				// node:tAdvancedHash_row12 - inputs:(row12) outputs:()
				// linked node: tMap_4 - inputs:(row11,row12) outputs:(to_stg,doublons_stg)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row12 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct> tHash_Lookup_row12 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row12Struct>getLookup(matchingModeEnum_row12);

				globalMap.put("tHash_Lookup_row12", tHash_Lookup_row12);

				/**
				 * [tAdvancedHash_row12 begin ] stop
				 */

				/**
				 * [tDBInput_2 begin ] start
				 */

				ok_Hash.put("tDBInput_2", false);
				start_Hash.put("tDBInput_2", System.currentTimeMillis());

				currentComponent = "tDBInput_2";

				int tos_count_tDBInput_2 = 0;

				int nb_line_tDBInput_2 = 0;
				java.sql.Connection conn_tDBInput_2 = null;
				conn_tDBInput_2 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

				String dbquery_tDBInput_2 = "select * FROM stg.marche_v2";

				globalMap.put("tDBInput_2_QUERY", dbquery_tDBInput_2);
				java.sql.ResultSet rs_tDBInput_2 = null;

				try {
					rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
					java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
					int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

					String tmpContent_tDBInput_2 = null;

					while (rs_tDBInput_2.next()) {
						nb_line_tDBInput_2++;

						if (colQtyInRs_tDBInput_2 < 1) {
							row12.recordid = null;
						} else {

							row12.recordid = routines.system.JDBCUtil.getString(rs_tDBInput_2, 1, false);
						}
						if (colQtyInRs_tDBInput_2 < 2) {
							row12.datepublicationdonnees = null;
						} else {

							row12.datepublicationdonnees = routines.system.JDBCUtil.getDate(rs_tDBInput_2, 2);
						}
						if (colQtyInRs_tDBInput_2 < 3) {
							row12.procedure = null;
						} else {

							row12.procedure = routines.system.JDBCUtil.getString(rs_tDBInput_2, 3, false);
						}
						if (colQtyInRs_tDBInput_2 < 4) {
							row12.nature = null;
						} else {

							row12.nature = routines.system.JDBCUtil.getString(rs_tDBInput_2, 4, false);
						}
						if (colQtyInRs_tDBInput_2 < 5) {
							row12.type = null;
						} else {

							row12.type = routines.system.JDBCUtil.getString(rs_tDBInput_2, 5, false);
						}
						if (colQtyInRs_tDBInput_2 < 6) {
							row12.datenotification = null;
						} else {

							row12.datenotification = routines.system.JDBCUtil.getDate(rs_tDBInput_2, 6);
						}
						if (colQtyInRs_tDBInput_2 < 7) {
							row12.montant = null;
						} else {

							row12.montant = rs_tDBInput_2.getFloat(7);
							if (rs_tDBInput_2.wasNull()) {
								row12.montant = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 8) {
							row12.siretetablissement = null;
						} else {

							row12.siretetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 8, false);
						}
						if (colQtyInRs_tDBInput_2 < 9) {
							row12.objetmarche = null;
						} else {

							row12.objetmarche = routines.system.JDBCUtil.getString(rs_tDBInput_2, 9, false);
						}
						if (colQtyInRs_tDBInput_2 < 10) {
							row12.idacheteur = null;
						} else {

							row12.idacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 10, false);
						}
						if (colQtyInRs_tDBInput_2 < 11) {
							row12.codecommuneacheteur = null;
						} else {

							row12.codecommuneacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 11, false);
						}
						if (colQtyInRs_tDBInput_2 < 12) {
							row12.source = null;
						} else {

							row12.source = routines.system.JDBCUtil.getString(rs_tDBInput_2, 12, false);
						}
						if (colQtyInRs_tDBInput_2 < 13) {
							row12.lieuexecutionnom = null;
						} else {

							row12.lieuexecutionnom = routines.system.JDBCUtil.getString(rs_tDBInput_2, 13, false);
						}
						if (colQtyInRs_tDBInput_2 < 14) {
							row12.codearrondissementetablissement = null;
						} else {

							row12.codearrondissementetablissement = rs_tDBInput_2.getInt(14);
							if (rs_tDBInput_2.wasNull()) {
								row12.codearrondissementetablissement = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 15) {
							row12.libellearrondissementetablissement = null;
						} else {

							row12.libellearrondissementetablissement = rs_tDBInput_2.getInt(15);
							if (rs_tDBInput_2.wasNull()) {
								row12.libellearrondissementetablissement = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 16) {
							row12.sirenetablissement = null;
						} else {

							row12.sirenetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 16, false);
						}
						if (colQtyInRs_tDBInput_2 < 17) {
							row12.codetypeetablissement = null;
						} else {

							row12.codetypeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 17, false);
						}
						if (colQtyInRs_tDBInput_2 < 18) {
							row12.nicetablissement = null;
						} else {

							row12.nicetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 18, false);
						}
						if (colQtyInRs_tDBInput_2 < 19) {
							row12.lieuexecutiontypecode = null;
						} else {

							row12.lieuexecutiontypecode = routines.system.JDBCUtil.getString(rs_tDBInput_2, 19, false);
						}
						if (colQtyInRs_tDBInput_2 < 20) {
							row12.libelleregionetablissement = null;
						} else {

							row12.libelleregionetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 20,
									false);
						}
						if (colQtyInRs_tDBInput_2 < 21) {
							row12.categorieetablissement = null;
						} else {

							row12.categorieetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 21, false);
						}
						if (colQtyInRs_tDBInput_2 < 22) {
							row12.nomacheteur = null;
						} else {

							row12.nomacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 22, false);
						}
						if (colQtyInRs_tDBInput_2 < 23) {
							row12.idmarche = null;
						} else {

							row12.idmarche = rs_tDBInput_2.getInt(23);
							if (rs_tDBInput_2.wasNull()) {
								row12.idmarche = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 24) {
							row12.communeetablissement = null;
						} else {

							row12.communeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 24, false);
						}
						if (colQtyInRs_tDBInput_2 < 25) {
							row12.libellecommuneacheteur = null;
						} else {

							row12.libellecommuneacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 25, false);
						}
						if (colQtyInRs_tDBInput_2 < 26) {
							row12.libelledepartementetablissement = null;
						} else {

							row12.libelledepartementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2,
									26, false);
						}
						if (colQtyInRs_tDBInput_2 < 27) {
							row12.dureemoisestimee = null;
						} else {

							row12.dureemoisestimee = routines.system.JDBCUtil.getString(rs_tDBInput_2, 27, false);
						}
						if (colQtyInRs_tDBInput_2 < 28) {
							row12.libelleregionacheteur = null;
						} else {

							row12.libelleregionacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 28, false);
						}
						if (colQtyInRs_tDBInput_2 < 29) {
							row12.coderegionacheteur = null;
						} else {

							row12.coderegionacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 29, false);
						}
						if (colQtyInRs_tDBInput_2 < 30) {
							row12.siretetablissementvalide = null;
						} else {

							row12.siretetablissementvalide = routines.system.JDBCUtil.getString(rs_tDBInput_2, 30,
									false);
						}
						if (colQtyInRs_tDBInput_2 < 31) {
							row12.codecommuneetablissement = null;
						} else {

							row12.codecommuneetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 31,
									false);
						}
						if (colQtyInRs_tDBInput_2 < 32) {
							row12.codepostalacheteur = null;
						} else {

							row12.codepostalacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 32, false);
						}
						if (colQtyInRs_tDBInput_2 < 33) {
							row12.departementetablissement = null;
						} else {

							row12.departementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 33,
									false);
						}
						if (colQtyInRs_tDBInput_2 < 34) {
							row12.superficiecommuneacheteur = null;
						} else {

							row12.superficiecommuneacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 34,
									false);
						}
						if (colQtyInRs_tDBInput_2 < 35) {
							row12.populationcommuneacheteur = null;
						} else {

							row12.populationcommuneacheteur = rs_tDBInput_2.getFloat(35);
							if (rs_tDBInput_2.wasNull()) {
								row12.populationcommuneacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 36) {
							row12.codedepartementexecution = null;
						} else {

							row12.codedepartementexecution = rs_tDBInput_2.getFloat(36);
							if (rs_tDBInput_2.wasNull()) {
								row12.codedepartementexecution = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 37) {
							row12.sirenacheteurvalide = null;
						} else {

							row12.sirenacheteurvalide = routines.system.JDBCUtil.getString(rs_tDBInput_2, 37, false);
						}
						if (colQtyInRs_tDBInput_2 < 38) {
							row12.codearrondissementacheteur = null;
						} else {

							row12.codearrondissementacheteur = rs_tDBInput_2.getInt(38);
							if (rs_tDBInput_2.wasNull()) {
								row12.codearrondissementacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 39) {
							row12.sirenetablissementvalide = null;
						} else {

							row12.sirenetablissementvalide = routines.system.JDBCUtil.getString(rs_tDBInput_2, 39,
									false);
						}
						if (colQtyInRs_tDBInput_2 < 40) {
							row12.dureemoiscalculee = null;
						} else {

							row12.dureemoiscalculee = rs_tDBInput_2.getFloat(40);
							if (rs_tDBInput_2.wasNull()) {
								row12.dureemoiscalculee = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 41) {
							row12.populationcommuneetablissement = null;
						} else {

							row12.populationcommuneetablissement = rs_tDBInput_2.getFloat(41);
							if (rs_tDBInput_2.wasNull()) {
								row12.populationcommuneetablissement = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 42) {
							row12.codecpv_division = null;
						} else {

							row12.codecpv_division = rs_tDBInput_2.getInt(42);
							if (rs_tDBInput_2.wasNull()) {
								row12.codecpv_division = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 43) {
							row12.denominationsocialeetablissement = null;
						} else {

							row12.denominationsocialeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2,
									43, false);
						}
						if (colQtyInRs_tDBInput_2 < 44) {
							row12.coderegionetablissement = null;
						} else {

							row12.coderegionetablissement = rs_tDBInput_2.getInt(44);
							if (rs_tDBInput_2.wasNull()) {
								row12.coderegionetablissement = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 45) {
							row12.lieuexecutioncode = null;
						} else {

							row12.lieuexecutioncode = routines.system.JDBCUtil.getString(rs_tDBInput_2, 45, false);
						}
						if (colQtyInRs_tDBInput_2 < 46) {
							row12.moisnotification = null;
						} else {

							row12.moisnotification = routines.system.JDBCUtil.getString(rs_tDBInput_2, 46, false);
						}
						if (colQtyInRs_tDBInput_2 < 47) {
							row12.codecpv_original = null;
						} else {

							row12.codecpv_original = routines.system.JDBCUtil.getString(rs_tDBInput_2, 47, false);
						}
						if (colQtyInRs_tDBInput_2 < 48) {
							row12.typeidentifiantetablissement = null;
						} else {

							row12.typeidentifiantetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 48,
									false);
						}
						if (colQtyInRs_tDBInput_2 < 49) {
							row12.dureemois = null;
						} else {

							row12.dureemois = rs_tDBInput_2.getInt(49);
							if (rs_tDBInput_2.wasNull()) {
								row12.dureemois = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 50) {
							row12.libellearrondissementacheteur = null;
						} else {

							row12.libellearrondissementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 50,
									false);
						}
						if (colQtyInRs_tDBInput_2 < 51) {
							row12.nombretitulairesurmarchepresume = null;
						} else {

							row12.nombretitulairesurmarchepresume = rs_tDBInput_2.getFloat(51);
							if (rs_tDBInput_2.wasNull()) {
								row12.nombretitulairesurmarchepresume = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 52) {
							row12.adresseetablissement = null;
						} else {

							row12.adresseetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 52, false);
						}
						if (colQtyInRs_tDBInput_2 < 53) {
							row12.superficiecommuneetablissement = null;
						} else {

							row12.superficiecommuneetablissement = rs_tDBInput_2.getFloat(53);
							if (rs_tDBInput_2.wasNull()) {
								row12.superficiecommuneetablissement = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 54) {
							row12.distanceacheteuretablissement = null;
						} else {

							row12.distanceacheteuretablissement = rs_tDBInput_2.getFloat(54);
							if (rs_tDBInput_2.wasNull()) {
								row12.distanceacheteuretablissement = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 55) {
							row12.natureobjetmarche = null;
						} else {

							row12.natureobjetmarche = routines.system.JDBCUtil.getString(rs_tDBInput_2, 55, false);
						}
						if (colQtyInRs_tDBInput_2 < 56) {
							row12.formeprix = null;
						} else {

							row12.formeprix = routines.system.JDBCUtil.getString(rs_tDBInput_2, 56, false);
						}
						if (colQtyInRs_tDBInput_2 < 57) {
							row12.anneenotification = null;
						} else {

							row12.anneenotification = routines.system.JDBCUtil.getString(rs_tDBInput_2, 57, false);
						}
						if (colQtyInRs_tDBInput_2 < 58) {
							row12.libelledepartementacheteur = null;
						} else {

							row12.libelledepartementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 58,
									false);
						}
						if (colQtyInRs_tDBInput_2 < 59) {
							row12.departementacheteur = null;
						} else {

							row12.departementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_2, 59, false);
						}
						if (colQtyInRs_tDBInput_2 < 60) {
							row12.codepostaletablissement = null;
						} else {

							row12.codepostaletablissement = routines.system.JDBCUtil.getString(rs_tDBInput_2, 60,
									false);
						}
						if (colQtyInRs_tDBInput_2 < 61) {
							row12.montantcalcule = null;
						} else {

							row12.montantcalcule = rs_tDBInput_2.getFloat(61);
							if (rs_tDBInput_2.wasNull()) {
								row12.montantcalcule = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 62) {
							row12.coderegionexecution = null;
						} else {

							row12.coderegionexecution = rs_tDBInput_2.getFloat(62);
							if (rs_tDBInput_2.wasNull()) {
								row12.coderegionexecution = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 63) {
							row12.codecpv = null;
						} else {

							row12.codecpv = routines.system.JDBCUtil.getString(rs_tDBInput_2, 63, false);
						}
						if (colQtyInRs_tDBInput_2 < 64) {
							row12.referencecpv = null;
						} else {

							row12.referencecpv = routines.system.JDBCUtil.getString(rs_tDBInput_2, 64, false);
						}
						if (colQtyInRs_tDBInput_2 < 65) {
							row12.libelleregionexecution = null;
						} else {

							row12.libelleregionexecution = routines.system.JDBCUtil.getString(rs_tDBInput_2, 65, false);
						}

						/**
						 * [tDBInput_2 begin ] stop
						 */

						/**
						 * [tDBInput_2 main ] start
						 */

						currentComponent = "tDBInput_2";

						tos_count_tDBInput_2++;

						/**
						 * [tDBInput_2 main ] stop
						 */

						/**
						 * [tDBInput_2 process_data_begin ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row12 main ] start
						 */

						currentComponent = "tAdvancedHash_row12";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row12"

							);
						}

						row12Struct row12_HashRow = new row12Struct();

						row12_HashRow.recordid = row12.recordid;

						row12_HashRow.datepublicationdonnees = row12.datepublicationdonnees;

						row12_HashRow.procedure = row12.procedure;

						row12_HashRow.nature = row12.nature;

						row12_HashRow.type = row12.type;

						row12_HashRow.datenotification = row12.datenotification;

						row12_HashRow.montant = row12.montant;

						row12_HashRow.siretetablissement = row12.siretetablissement;

						row12_HashRow.objetmarche = row12.objetmarche;

						row12_HashRow.idacheteur = row12.idacheteur;

						row12_HashRow.codecommuneacheteur = row12.codecommuneacheteur;

						row12_HashRow.source = row12.source;

						row12_HashRow.lieuexecutionnom = row12.lieuexecutionnom;

						row12_HashRow.codearrondissementetablissement = row12.codearrondissementetablissement;

						row12_HashRow.libellearrondissementetablissement = row12.libellearrondissementetablissement;

						row12_HashRow.sirenetablissement = row12.sirenetablissement;

						row12_HashRow.codetypeetablissement = row12.codetypeetablissement;

						row12_HashRow.nicetablissement = row12.nicetablissement;

						row12_HashRow.lieuexecutiontypecode = row12.lieuexecutiontypecode;

						row12_HashRow.libelleregionetablissement = row12.libelleregionetablissement;

						row12_HashRow.categorieetablissement = row12.categorieetablissement;

						row12_HashRow.nomacheteur = row12.nomacheteur;

						row12_HashRow.idmarche = row12.idmarche;

						row12_HashRow.communeetablissement = row12.communeetablissement;

						row12_HashRow.libellecommuneacheteur = row12.libellecommuneacheteur;

						row12_HashRow.libelledepartementetablissement = row12.libelledepartementetablissement;

						row12_HashRow.dureemoisestimee = row12.dureemoisestimee;

						row12_HashRow.libelleregionacheteur = row12.libelleregionacheteur;

						row12_HashRow.coderegionacheteur = row12.coderegionacheteur;

						row12_HashRow.siretetablissementvalide = row12.siretetablissementvalide;

						row12_HashRow.codecommuneetablissement = row12.codecommuneetablissement;

						row12_HashRow.codepostalacheteur = row12.codepostalacheteur;

						row12_HashRow.departementetablissement = row12.departementetablissement;

						row12_HashRow.superficiecommuneacheteur = row12.superficiecommuneacheteur;

						row12_HashRow.populationcommuneacheteur = row12.populationcommuneacheteur;

						row12_HashRow.codedepartementexecution = row12.codedepartementexecution;

						row12_HashRow.sirenacheteurvalide = row12.sirenacheteurvalide;

						row12_HashRow.codearrondissementacheteur = row12.codearrondissementacheteur;

						row12_HashRow.sirenetablissementvalide = row12.sirenetablissementvalide;

						row12_HashRow.dureemoiscalculee = row12.dureemoiscalculee;

						row12_HashRow.populationcommuneetablissement = row12.populationcommuneetablissement;

						row12_HashRow.codecpv_division = row12.codecpv_division;

						row12_HashRow.denominationsocialeetablissement = row12.denominationsocialeetablissement;

						row12_HashRow.coderegionetablissement = row12.coderegionetablissement;

						row12_HashRow.lieuexecutioncode = row12.lieuexecutioncode;

						row12_HashRow.moisnotification = row12.moisnotification;

						row12_HashRow.codecpv_original = row12.codecpv_original;

						row12_HashRow.typeidentifiantetablissement = row12.typeidentifiantetablissement;

						row12_HashRow.dureemois = row12.dureemois;

						row12_HashRow.libellearrondissementacheteur = row12.libellearrondissementacheteur;

						row12_HashRow.nombretitulairesurmarchepresume = row12.nombretitulairesurmarchepresume;

						row12_HashRow.adresseetablissement = row12.adresseetablissement;

						row12_HashRow.superficiecommuneetablissement = row12.superficiecommuneetablissement;

						row12_HashRow.distanceacheteuretablissement = row12.distanceacheteuretablissement;

						row12_HashRow.natureobjetmarche = row12.natureobjetmarche;

						row12_HashRow.formeprix = row12.formeprix;

						row12_HashRow.anneenotification = row12.anneenotification;

						row12_HashRow.libelledepartementacheteur = row12.libelledepartementacheteur;

						row12_HashRow.departementacheteur = row12.departementacheteur;

						row12_HashRow.codepostaletablissement = row12.codepostaletablissement;

						row12_HashRow.montantcalcule = row12.montantcalcule;

						row12_HashRow.coderegionexecution = row12.coderegionexecution;

						row12_HashRow.codecpv = row12.codecpv;

						row12_HashRow.referencecpv = row12.referencecpv;

						row12_HashRow.libelleregionexecution = row12.libelleregionexecution;

						tHash_Lookup_row12.put(row12_HashRow);

						tos_count_tAdvancedHash_row12++;

						/**
						 * [tAdvancedHash_row12 main ] stop
						 */

						/**
						 * [tAdvancedHash_row12 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row12";

						/**
						 * [tAdvancedHash_row12 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row12 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row12";

						/**
						 * [tAdvancedHash_row12 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 process_data_end ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 end ] start
						 */

						currentComponent = "tDBInput_2";

					}
				} finally {
					if (rs_tDBInput_2 != null) {
						rs_tDBInput_2.close();
					}
					if (stmt_tDBInput_2 != null) {
						stmt_tDBInput_2.close();
					}
				}
				globalMap.put("tDBInput_2_NB_LINE", nb_line_tDBInput_2);

				ok_Hash.put("tDBInput_2", true);
				end_Hash.put("tDBInput_2", System.currentTimeMillis());

				/**
				 * [tDBInput_2 end ] stop
				 */

				/**
				 * [tAdvancedHash_row12 end ] start
				 */

				currentComponent = "tAdvancedHash_row12";

				tHash_Lookup_row12.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row12");
				}

				ok_Hash.put("tAdvancedHash_row12", true);
				end_Hash.put("tAdvancedHash_row12", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row12 end ] stop
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
				 * [tDBInput_2 finally ] start
				 */

				currentComponent = "tDBInput_2";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row12 finally ] start
				 */

				currentComponent = "tAdvancedHash_row12";

				/**
				 * [tAdvancedHash_row12 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}

	public static class logsStruct implements routines.system.IPersistableRow<logsStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_daily_loading_staging = new byte[0];
		static byte[] commonByteArray_CITYVIZ_daily_loading_staging = new byte[0];

		public String message;

		public String getMessage() {
			return this.message;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.message = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.message = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.message, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.message, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("message=" + message);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(logsStruct other) {

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

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_daily_loading_staging = new byte[0];
		static byte[] commonByteArray_CITYVIZ_daily_loading_staging = new byte[0];

		public java.util.Date moment;

		public java.util.Date getMoment() {
			return this.moment;
		}

		public String pid;

		public String getPid() {
			return this.pid;
		}

		public String root_pid;

		public String getRoot_pid() {
			return this.root_pid;
		}

		public String father_pid;

		public String getFather_pid() {
			return this.father_pid;
		}

		public String project;

		public String getProject() {
			return this.project;
		}

		public String job;

		public String getJob() {
			return this.job;
		}

		public String context;

		public String getContext() {
			return this.context;
		}

		public Integer priority;

		public Integer getPriority() {
			return this.priority;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		public String origin;

		public String getOrigin() {
			return this.origin;
		}

		public String message;

		public String getMessage() {
			return this.message;
		}

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_daily_loading_staging.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_daily_loading_staging.length == 0) {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_daily_loading_staging = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_daily_loading_staging, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_daily_loading_staging, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.root_pid = readString(dis);

					this.father_pid = readString(dis);

					this.project = readString(dis);

					this.job = readString(dis);

					this.context = readString(dis);

					this.priority = readInteger(dis);

					this.type = readString(dis);

					this.origin = readString(dis);

					this.message = readString(dis);

					this.code = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_daily_loading_staging) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.root_pid = readString(dis);

					this.father_pid = readString(dis);

					this.project = readString(dis);

					this.job = readString(dis);

					this.context = readString(dis);

					this.priority = readInteger(dis);

					this.type = readString(dis);

					this.origin = readString(dis);

					this.message = readString(dis);

					this.code = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.root_pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.context, dos);

				// Integer

				writeInteger(this.priority, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message, dos);

				// Integer

				writeInteger(this.code, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.root_pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.context, dos);

				// Integer

				writeInteger(this.priority, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message, dos);

				// Integer

				writeInteger(this.code, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("moment=" + String.valueOf(moment));
			sb.append(",pid=" + pid);
			sb.append(",root_pid=" + root_pid);
			sb.append(",father_pid=" + father_pid);
			sb.append(",project=" + project);
			sb.append(",job=" + job);
			sb.append(",context=" + context);
			sb.append(",priority=" + String.valueOf(priority));
			sb.append(",type=" + type);
			sb.append(",origin=" + origin);
			sb.append(",message=" + message);
			sb.append(",code=" + String.valueOf(code));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

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

	public void tLogCatcher_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tLogCatcher_1_SUBPROCESS_STATE", 0);

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

				row4Struct row4 = new row4Struct();
				logsStruct logs = new logsStruct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "logs");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_1 = "|";
				java.io.PrintStream consoleOut_tLogRow_1 = null;

				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_3 begin ] start
				 */

				ok_Hash.put("tMap_3", false);
				start_Hash.put("tMap_3", System.currentTimeMillis());

				currentComponent = "tMap_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tMap_3 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_3__Struct {
				}
				Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
				logsStruct logs_tmp = new logsStruct();
// ###############################

				/**
				 * [tMap_3 begin ] stop
				 */

				/**
				 * [tLogCatcher_1 begin ] start
				 */

				ok_Hash.put("tLogCatcher_1", false);
				start_Hash.put("tLogCatcher_1", System.currentTimeMillis());

				currentComponent = "tLogCatcher_1";

				int tos_count_tLogCatcher_1 = 0;

				try {
					for (LogCatcherUtils.LogCatcherMessage lcm : tLogCatcher_1.getMessages()) {
						row4.type = lcm.getType();
						row4.origin = (lcm.getOrigin() == null || lcm.getOrigin().length() < 1 ? null
								: lcm.getOrigin());
						row4.priority = lcm.getPriority();
						row4.message = lcm.getMessage();
						row4.code = lcm.getCode();

						row4.moment = java.util.Calendar.getInstance().getTime();

						row4.pid = pid;
						row4.root_pid = rootPid;
						row4.father_pid = fatherPid;

						row4.project = projectName;
						row4.job = jobName;
						row4.context = contextStr;

						/**
						 * [tLogCatcher_1 begin ] stop
						 */

						/**
						 * [tLogCatcher_1 main ] start
						 */

						currentComponent = "tLogCatcher_1";

						tos_count_tLogCatcher_1++;

						/**
						 * [tLogCatcher_1 main ] stop
						 */

						/**
						 * [tLogCatcher_1 process_data_begin ] start
						 */

						currentComponent = "tLogCatcher_1";

						/**
						 * [tLogCatcher_1 process_data_begin ] stop
						 */

						/**
						 * [tMap_3 main ] start
						 */

						currentComponent = "tMap_3";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row4"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_3 = false;
						boolean mainRowRejected_tMap_3 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
							// ###############################
							// # Output tables

							logs = null;

// # Output table : 'logs'
							logs_tmp.message = row4.message;
							logs = logs_tmp;
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_3 = false;

						tos_count_tMap_3++;

						/**
						 * [tMap_3 main ] stop
						 */

						/**
						 * [tMap_3 process_data_begin ] start
						 */

						currentComponent = "tMap_3";

						/**
						 * [tMap_3 process_data_begin ] stop
						 */
// Start of branch "logs"
						if (logs != null) {

							/**
							 * [tLogRow_1 main ] start
							 */

							currentComponent = "tLogRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "logs"

								);
							}

///////////////////////		

							strBuffer_tLogRow_1 = new StringBuilder();

							if (logs.message != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(logs.message));

							} //

							if (globalMap.get("tLogRow_CONSOLE") != null) {
								consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
							} else {
								consoleOut_tLogRow_1 = new java.io.PrintStream(
										new java.io.BufferedOutputStream(System.out));
								globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
							}
							consoleOut_tLogRow_1.println(strBuffer_tLogRow_1.toString());
							consoleOut_tLogRow_1.flush();
							nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

							tos_count_tLogRow_1++;

							/**
							 * [tLogRow_1 main ] stop
							 */

							/**
							 * [tLogRow_1 process_data_begin ] start
							 */

							currentComponent = "tLogRow_1";

							/**
							 * [tLogRow_1 process_data_begin ] stop
							 */

							/**
							 * [tLogRow_1 process_data_end ] start
							 */

							currentComponent = "tLogRow_1";

							/**
							 * [tLogRow_1 process_data_end ] stop
							 */

						} // End of branch "logs"

						/**
						 * [tMap_3 process_data_end ] start
						 */

						currentComponent = "tMap_3";

						/**
						 * [tMap_3 process_data_end ] stop
						 */

						/**
						 * [tLogCatcher_1 process_data_end ] start
						 */

						currentComponent = "tLogCatcher_1";

						/**
						 * [tLogCatcher_1 process_data_end ] stop
						 */

						/**
						 * [tLogCatcher_1 end ] start
						 */

						currentComponent = "tLogCatcher_1";

					}
				} catch (Exception e_tLogCatcher_1) {
					globalMap.put("tLogCatcher_1_ERROR_MESSAGE", e_tLogCatcher_1.getMessage());
					logIgnoredError(String.format(
							"tLogCatcher_1 - tLogCatcher failed to process log message(s) due to internal error: %s",
							e_tLogCatcher_1), e_tLogCatcher_1);
				}

				ok_Hash.put("tLogCatcher_1", true);
				end_Hash.put("tLogCatcher_1", System.currentTimeMillis());

				/**
				 * [tLogCatcher_1 end ] stop
				 */

				/**
				 * [tMap_3 end ] start
				 */

				currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tMap_3", true);
				end_Hash.put("tMap_3", System.currentTimeMillis());

				/**
				 * [tMap_3 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "logs");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
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
				 * [tLogCatcher_1 finally ] start
				 */

				currentComponent = "tLogCatcher_1";

				/**
				 * [tLogCatcher_1 finally ] stop
				 */

				/**
				 * [tMap_3 finally ] start
				 */

				currentComponent = "tMap_3";

				/**
				 * [tMap_3 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tLogCatcher_1_SUBPROCESS_STATE", 1);
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
		final daily_loading_staging daily_loading_stagingClass = new daily_loading_staging();

		int exitCode = daily_loading_stagingClass.runJobInTOS(args);

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
			java.io.InputStream inContext = daily_loading_staging.class.getClassLoader()
					.getResourceAsStream("cityviz/daily_loading_staging_1_0/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = daily_loading_staging.class.getClassLoader()
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
					context.setContextType("cityviz_dwh_AdditionalParams", "id_String");
					if (context.getStringValue("cityviz_dwh_AdditionalParams") == null) {
						context.cityviz_dwh_AdditionalParams = null;
					} else {
						context.cityviz_dwh_AdditionalParams = (String) context
								.getProperty("cityviz_dwh_AdditionalParams");
					}
					context.setContextType("cityviz_dwh_Database", "id_String");
					if (context.getStringValue("cityviz_dwh_Database") == null) {
						context.cityviz_dwh_Database = null;
					} else {
						context.cityviz_dwh_Database = (String) context.getProperty("cityviz_dwh_Database");
					}
					context.setContextType("cityviz_dwh_Login", "id_String");
					if (context.getStringValue("cityviz_dwh_Login") == null) {
						context.cityviz_dwh_Login = null;
					} else {
						context.cityviz_dwh_Login = (String) context.getProperty("cityviz_dwh_Login");
					}
					context.setContextType("cityviz_dwh_Password", "id_Password");
					if (context.getStringValue("cityviz_dwh_Password") == null) {
						context.cityviz_dwh_Password = null;
					} else {
						String pwd_cityviz_dwh_Password_value = context.getProperty("cityviz_dwh_Password");
						context.cityviz_dwh_Password = null;
						if (pwd_cityviz_dwh_Password_value != null) {
							if (context_param.containsKey("cityviz_dwh_Password")) {// no need to decrypt if it come
																					// from program argument or parent
																					// job runtime
								context.cityviz_dwh_Password = pwd_cityviz_dwh_Password_value;
							} else if (!pwd_cityviz_dwh_Password_value.isEmpty()) {
								try {
									context.cityviz_dwh_Password = routines.system.PasswordEncryptUtil
											.decryptPassword(pwd_cityviz_dwh_Password_value);
									context.put("cityviz_dwh_Password", context.cityviz_dwh_Password);
								} catch (java.lang.RuntimeException e) {
									// do nothing
								}
							}
						}
					}
					context.setContextType("cityviz_dwh_Port", "id_String");
					if (context.getStringValue("cityviz_dwh_Port") == null) {
						context.cityviz_dwh_Port = null;
					} else {
						context.cityviz_dwh_Port = (String) context.getProperty("cityviz_dwh_Port");
					}
					context.setContextType("cityviz_dwh_Schema", "id_String");
					if (context.getStringValue("cityviz_dwh_Schema") == null) {
						context.cityviz_dwh_Schema = null;
					} else {
						context.cityviz_dwh_Schema = (String) context.getProperty("cityviz_dwh_Schema");
					}
					context.setContextType("cityviz_dwh_Server", "id_String");
					if (context.getStringValue("cityviz_dwh_Server") == null) {
						context.cityviz_dwh_Server = null;
					} else {
						context.cityviz_dwh_Server = (String) context.getProperty("cityviz_dwh_Server");
					}
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
					context.setContextType("logs_path", "id_String");
					if (context.getStringValue("logs_path") == null) {
						context.logs_path = null;
					} else {
						context.logs_path = (String) context.getProperty("logs_path");
					}
					context.setContextType("param_file_path", "id_String");
					if (context.getStringValue("param_file_path") == null) {
						context.param_file_path = null;
					} else {
						context.param_file_path = (String) context.getProperty("param_file_path");
					}
					context.setContextType("start_value", "id_Integer");
					if (context.getStringValue("start_value") == null) {
						context.start_value = null;
					} else {
						try {
							context.start_value = routines.system.ParserUtils
									.parseTo_Integer(context.getProperty("start_value"));
						} catch (NumberFormatException e) {
							System.err.println(String.format("Null value will be used for context parameter %s: %s",
									"start_value", e.getMessage()));
							context.start_value = null;
						}
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
			if (parentContextMap.containsKey("cityviz_dwh_AdditionalParams")) {
				context.cityviz_dwh_AdditionalParams = (String) parentContextMap.get("cityviz_dwh_AdditionalParams");
			}
			if (parentContextMap.containsKey("cityviz_dwh_Database")) {
				context.cityviz_dwh_Database = (String) parentContextMap.get("cityviz_dwh_Database");
			}
			if (parentContextMap.containsKey("cityviz_dwh_Login")) {
				context.cityviz_dwh_Login = (String) parentContextMap.get("cityviz_dwh_Login");
			}
			if (parentContextMap.containsKey("cityviz_dwh_Password")) {
				context.cityviz_dwh_Password = (java.lang.String) parentContextMap.get("cityviz_dwh_Password");
			}
			if (parentContextMap.containsKey("cityviz_dwh_Port")) {
				context.cityviz_dwh_Port = (String) parentContextMap.get("cityviz_dwh_Port");
			}
			if (parentContextMap.containsKey("cityviz_dwh_Schema")) {
				context.cityviz_dwh_Schema = (String) parentContextMap.get("cityviz_dwh_Schema");
			}
			if (parentContextMap.containsKey("cityviz_dwh_Server")) {
				context.cityviz_dwh_Server = (String) parentContextMap.get("cityviz_dwh_Server");
			}
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
			if (parentContextMap.containsKey("logs_path")) {
				context.logs_path = (String) parentContextMap.get("logs_path");
			}
			if (parentContextMap.containsKey("param_file_path")) {
				context.param_file_path = (String) parentContextMap.get("param_file_path");
			}
			if (parentContextMap.containsKey("start_value")) {
				context.start_value = (Integer) parentContextMap.get("start_value");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		parametersToEncrypt.add("cityviz_dwh_Password");
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
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : daily_loading_staging");
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
			obj_conn = globalMap.remove("conn_tDBConnection_2");
			if (null != obj_conn) {
				((java.sql.Connection) obj_conn).close();
			}
		} catch (java.lang.Exception e) {
		}
	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();
		connections.put("conn_tDBConnection_1", globalMap.get("conn_tDBConnection_1"));
		connections.put("conn_tDBConnection_2", globalMap.get("conn_tDBConnection_2"));

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
 * 495948 characters generated by Talend Open Studio for Data Integration on the
 * 30 mars 2022 à 17:29:04 CEST
 ************************************************************************************************/