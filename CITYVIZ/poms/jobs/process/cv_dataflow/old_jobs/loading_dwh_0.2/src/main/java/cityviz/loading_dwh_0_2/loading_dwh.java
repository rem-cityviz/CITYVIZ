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

package cityviz.loading_dwh_0_2;

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
 * Job: loading_dwh Purpose: Alimentation du dwh depuis notre base de
 * staging<br>
 * Description: 0.2 : Chargement des 3 tables dimensions ok, schémas ok et fixe
 * (voir les metadatas / modèles de données) <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class loading_dwh implements TalendJob {

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
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.2";
	private final String jobName = "loading_dwh";
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
					loading_dwh.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(loading_dwh.this, new Object[] { e, currentComponent, globalMap });
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

	public void tCreateTable_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tCreateTable_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tCreateTable_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tCreateTable_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tCreateTable_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tCreateTable_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tCreateTable_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tCreateTable_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tWarn_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tWarn_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBCommit_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBCommit_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBCommit_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBCommit_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tWarn_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tWarn_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBClose_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBClose_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBClose_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBClose_4_onSubJobError(exception, errorComponent, globalMap);
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

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row7_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tCreateTable_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tCreateTable_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tCreateTable_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tCreateTable_4_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tWarn_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_5_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tWarn_6_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBClose_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBClose_4_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

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

	public void tDBInput_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_4_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tCreateTable_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tCreateTable_1_SUBPROCESS_STATE", 0);

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
				 * [tCreateTable_1 begin ] start
				 */

				ok_Hash.put("tCreateTable_1", false);
				start_Hash.put("tCreateTable_1", System.currentTimeMillis());

				currentComponent = "tCreateTable_1";

				int tos_count_tCreateTable_1 = 0;

				/**
				 * [tCreateTable_1 begin ] stop
				 */

				/**
				 * [tCreateTable_1 main ] start
				 */

				currentComponent = "tCreateTable_1";

				try {
					String tableName_tCreateTable_1 = "dim_acheteur";
					String dbSchema_tCreateTable_1 = (String) globalMap.get("schema_tDBConnection_1");
					if (dbSchema_tCreateTable_1 != null && dbSchema_tCreateTable_1.trim().length() != 0) {
						tableName_tCreateTable_1 = dbSchema_tCreateTable_1 + "\".\"" + tableName_tCreateTable_1;
					}
					java.sql.Connection conn_tCreateTable_1 = (java.sql.Connection) globalMap
							.get("conn_tDBConnection_1");
					boolean whetherExist_tCreateTable_1 = false;
					java.sql.DatabaseMetaData dbMetaData_tCreateTable_1 = conn_tCreateTable_1.getMetaData();
					java.sql.ResultSet rsTable_tCreateTable_1 = dbMetaData_tCreateTable_1.getTables(null, null, null,
							new String[] { "TABLE" });
					String defaultSchema_tCreateTable_1 = "public";
					if (dbSchema_tCreateTable_1 == null || dbSchema_tCreateTable_1.trim().length() == 0) {
						java.sql.Statement stmtSchema_tCreateTable_1 = conn_tCreateTable_1.createStatement();
						java.sql.ResultSet rsSchema_tCreateTable_1 = stmtSchema_tCreateTable_1
								.executeQuery("select current_schema() ");
						while (rsSchema_tCreateTable_1.next()) {
							defaultSchema_tCreateTable_1 = rsSchema_tCreateTable_1.getString("current_schema");
						}
						rsSchema_tCreateTable_1.close();
						stmtSchema_tCreateTable_1.close();
					}
					while (rsTable_tCreateTable_1.next()) {
						String table_tCreateTable_1 = rsTable_tCreateTable_1.getString("TABLE_NAME");
						String schema_tCreateTable_1 = rsTable_tCreateTable_1.getString("TABLE_SCHEM");
						if (table_tCreateTable_1.equals("dim_acheteur") && (schema_tCreateTable_1
								.equals(dbSchema_tCreateTable_1)
								|| ((dbSchema_tCreateTable_1 == null || dbSchema_tCreateTable_1.trim().length() == 0)
										&& defaultSchema_tCreateTable_1.equals(schema_tCreateTable_1)))) {
							whetherExist_tCreateTable_1 = true;
							break;
						}
					}
					rsTable_tCreateTable_1.close();
					if (!whetherExist_tCreateTable_1) {
						java.sql.Statement stmt_tCreateTable_1 = conn_tCreateTable_1.createStatement();
						stmt_tCreateTable_1.execute("CREATE TABLE \"" + tableName_tCreateTable_1
								+ "\"(\"idacheteur\" VARCHAR ,\"nomacheteur\" VARCHAR ,\"codepostalacheteur\" VARCHAR ,\"codepostalcommune\" VARCHAR ,\"nomcommune\" VARCHAR ,\"codedepartement\" VARCHAR ,\"libelledepartement\" VARCHAR ,\"coderegion\" VARCHAR ,\"libelleregion\" VARCHAR ,primary key(\"idacheteur\"))");

					}

					globalMap.put("tCreateTable_1_QUERY", "CREATE TABLE \"" + tableName_tCreateTable_1
							+ "\"(\"idacheteur\" VARCHAR ,\"nomacheteur\" VARCHAR ,\"codepostalacheteur\" VARCHAR ,\"codepostalcommune\" VARCHAR ,\"nomcommune\" VARCHAR ,\"codedepartement\" VARCHAR ,\"libelledepartement\" VARCHAR ,\"coderegion\" VARCHAR ,\"libelleregion\" VARCHAR ,primary key(\"idacheteur\"))");
				} catch (java.lang.Exception e) {
					globalMap.put("tCreateTable_1_ERROR_MESSAGE", e.getMessage());
					throw new RuntimeException("Creating table failed", e);
				}

				tos_count_tCreateTable_1++;

				/**
				 * [tCreateTable_1 main ] stop
				 */

				/**
				 * [tCreateTable_1 process_data_begin ] start
				 */

				currentComponent = "tCreateTable_1";

				/**
				 * [tCreateTable_1 process_data_begin ] stop
				 */

				/**
				 * [tCreateTable_1 process_data_end ] start
				 */

				currentComponent = "tCreateTable_1";

				/**
				 * [tCreateTable_1 process_data_end ] stop
				 */

				/**
				 * [tCreateTable_1 end ] start
				 */

				currentComponent = "tCreateTable_1";

				ok_Hash.put("tCreateTable_1", true);
				end_Hash.put("tCreateTable_1", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk7", 0, "ok");
				}
				tCreateTable_2Process(globalMap);

				/**
				 * [tCreateTable_1 end ] stop
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
				 * [tCreateTable_1 finally ] start
				 */

				currentComponent = "tCreateTable_1";

				/**
				 * [tCreateTable_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tCreateTable_1_SUBPROCESS_STATE", 1);
	}

	public void tCreateTable_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tCreateTable_2_SUBPROCESS_STATE", 0);

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
				 * [tCreateTable_2 begin ] start
				 */

				ok_Hash.put("tCreateTable_2", false);
				start_Hash.put("tCreateTable_2", System.currentTimeMillis());

				currentComponent = "tCreateTable_2";

				int tos_count_tCreateTable_2 = 0;

				/**
				 * [tCreateTable_2 begin ] stop
				 */

				/**
				 * [tCreateTable_2 main ] start
				 */

				currentComponent = "tCreateTable_2";

				try {
					String tableName_tCreateTable_2 = "dim_marche";
					String dbSchema_tCreateTable_2 = (String) globalMap.get("schema_tDBConnection_1");
					if (dbSchema_tCreateTable_2 != null && dbSchema_tCreateTable_2.trim().length() != 0) {
						tableName_tCreateTable_2 = dbSchema_tCreateTable_2 + "\".\"" + tableName_tCreateTable_2;
					}
					java.sql.Connection conn_tCreateTable_2 = (java.sql.Connection) globalMap
							.get("conn_tDBConnection_1");
					boolean whetherExist_tCreateTable_2 = false;
					java.sql.DatabaseMetaData dbMetaData_tCreateTable_2 = conn_tCreateTable_2.getMetaData();
					java.sql.ResultSet rsTable_tCreateTable_2 = dbMetaData_tCreateTable_2.getTables(null, null, null,
							new String[] { "TABLE" });
					String defaultSchema_tCreateTable_2 = "public";
					if (dbSchema_tCreateTable_2 == null || dbSchema_tCreateTable_2.trim().length() == 0) {
						java.sql.Statement stmtSchema_tCreateTable_2 = conn_tCreateTable_2.createStatement();
						java.sql.ResultSet rsSchema_tCreateTable_2 = stmtSchema_tCreateTable_2
								.executeQuery("select current_schema() ");
						while (rsSchema_tCreateTable_2.next()) {
							defaultSchema_tCreateTable_2 = rsSchema_tCreateTable_2.getString("current_schema");
						}
						rsSchema_tCreateTable_2.close();
						stmtSchema_tCreateTable_2.close();
					}
					while (rsTable_tCreateTable_2.next()) {
						String table_tCreateTable_2 = rsTable_tCreateTable_2.getString("TABLE_NAME");
						String schema_tCreateTable_2 = rsTable_tCreateTable_2.getString("TABLE_SCHEM");
						if (table_tCreateTable_2.equals("dim_marche") && (schema_tCreateTable_2
								.equals(dbSchema_tCreateTable_2)
								|| ((dbSchema_tCreateTable_2 == null || dbSchema_tCreateTable_2.trim().length() == 0)
										&& defaultSchema_tCreateTable_2.equals(schema_tCreateTable_2)))) {
							whetherExist_tCreateTable_2 = true;
							break;
						}
					}
					rsTable_tCreateTable_2.close();
					if (!whetherExist_tCreateTable_2) {
						java.sql.Statement stmt_tCreateTable_2 = conn_tCreateTable_2.createStatement();
						stmt_tCreateTable_2.execute("CREATE TABLE \"" + tableName_tCreateTable_2
								+ "\"(\"idmarche\" INT4 ,\"natureobjetmarche\" VARCHAR ,\"objetmarche\" VARCHAR(512)  ,\"datenotification\" VARCHAR ,\"dureemois\" INT4 ,\"lieuexecutionnom\" VARCHAR ,\"formeprix\" VARCHAR ,\"lieuexecutiontypecode\" VARCHAR ,\"codedepartementexecution\" FLOAT4 ,\"coderegionexecution\" FLOAT4 ,\"libelleregionexecution\" VARCHAR ,\"nature\" VARCHAR ,\"procedure\" VARCHAR ,\"codecpv\" VARCHAR ,\"codecpv_original\" VARCHAR ,\"codecpv_division\" INT4 ,\"referencecpv\" VARCHAR ,primary key(\"idmarche\"))");

					}

					globalMap.put("tCreateTable_2_QUERY", "CREATE TABLE \"" + tableName_tCreateTable_2
							+ "\"(\"idmarche\" INT4 ,\"natureobjetmarche\" VARCHAR ,\"objetmarche\" VARCHAR(512)  ,\"datenotification\" VARCHAR ,\"dureemois\" INT4 ,\"lieuexecutionnom\" VARCHAR ,\"formeprix\" VARCHAR ,\"lieuexecutiontypecode\" VARCHAR ,\"codedepartementexecution\" FLOAT4 ,\"coderegionexecution\" FLOAT4 ,\"libelleregionexecution\" VARCHAR ,\"nature\" VARCHAR ,\"procedure\" VARCHAR ,\"codecpv\" VARCHAR ,\"codecpv_original\" VARCHAR ,\"codecpv_division\" INT4 ,\"referencecpv\" VARCHAR ,primary key(\"idmarche\"))");
				} catch (java.lang.Exception e) {
					globalMap.put("tCreateTable_2_ERROR_MESSAGE", e.getMessage());
					throw new RuntimeException("Creating table failed", e);
				}

				tos_count_tCreateTable_2++;

				/**
				 * [tCreateTable_2 main ] stop
				 */

				/**
				 * [tCreateTable_2 process_data_begin ] start
				 */

				currentComponent = "tCreateTable_2";

				/**
				 * [tCreateTable_2 process_data_begin ] stop
				 */

				/**
				 * [tCreateTable_2 process_data_end ] start
				 */

				currentComponent = "tCreateTable_2";

				/**
				 * [tCreateTable_2 process_data_end ] stop
				 */

				/**
				 * [tCreateTable_2 end ] start
				 */

				currentComponent = "tCreateTable_2";

				ok_Hash.put("tCreateTable_2", true);
				end_Hash.put("tCreateTable_2", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk4", 0, "ok");
				}
				tCreateTable_3Process(globalMap);

				/**
				 * [tCreateTable_2 end ] stop
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
				 * [tCreateTable_2 finally ] start
				 */

				currentComponent = "tCreateTable_2";

				/**
				 * [tCreateTable_2 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tCreateTable_2_SUBPROCESS_STATE", 1);
	}

	public void tCreateTable_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tCreateTable_3_SUBPROCESS_STATE", 0);

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
				 * [tCreateTable_3 begin ] start
				 */

				ok_Hash.put("tCreateTable_3", false);
				start_Hash.put("tCreateTable_3", System.currentTimeMillis());

				currentComponent = "tCreateTable_3";

				int tos_count_tCreateTable_3 = 0;

				/**
				 * [tCreateTable_3 begin ] stop
				 */

				/**
				 * [tCreateTable_3 main ] start
				 */

				currentComponent = "tCreateTable_3";

				try {
					String tableName_tCreateTable_3 = "dim_etablissement";
					String dbSchema_tCreateTable_3 = (String) globalMap.get("schema_tDBConnection_1");
					if (dbSchema_tCreateTable_3 != null && dbSchema_tCreateTable_3.trim().length() != 0) {
						tableName_tCreateTable_3 = dbSchema_tCreateTable_3 + "\".\"" + tableName_tCreateTable_3;
					}
					java.sql.Connection conn_tCreateTable_3 = (java.sql.Connection) globalMap
							.get("conn_tDBConnection_1");
					boolean whetherExist_tCreateTable_3 = false;
					java.sql.DatabaseMetaData dbMetaData_tCreateTable_3 = conn_tCreateTable_3.getMetaData();
					java.sql.ResultSet rsTable_tCreateTable_3 = dbMetaData_tCreateTable_3.getTables(null, null, null,
							new String[] { "TABLE" });
					String defaultSchema_tCreateTable_3 = "public";
					if (dbSchema_tCreateTable_3 == null || dbSchema_tCreateTable_3.trim().length() == 0) {
						java.sql.Statement stmtSchema_tCreateTable_3 = conn_tCreateTable_3.createStatement();
						java.sql.ResultSet rsSchema_tCreateTable_3 = stmtSchema_tCreateTable_3
								.executeQuery("select current_schema() ");
						while (rsSchema_tCreateTable_3.next()) {
							defaultSchema_tCreateTable_3 = rsSchema_tCreateTable_3.getString("current_schema");
						}
						rsSchema_tCreateTable_3.close();
						stmtSchema_tCreateTable_3.close();
					}
					while (rsTable_tCreateTable_3.next()) {
						String table_tCreateTable_3 = rsTable_tCreateTable_3.getString("TABLE_NAME");
						String schema_tCreateTable_3 = rsTable_tCreateTable_3.getString("TABLE_SCHEM");
						if (table_tCreateTable_3.equals("dim_etablissement") && (schema_tCreateTable_3
								.equals(dbSchema_tCreateTable_3)
								|| ((dbSchema_tCreateTable_3 == null || dbSchema_tCreateTable_3.trim().length() == 0)
										&& defaultSchema_tCreateTable_3.equals(schema_tCreateTable_3)))) {
							whetherExist_tCreateTable_3 = true;
							break;
						}
					}
					rsTable_tCreateTable_3.close();
					if (!whetherExist_tCreateTable_3) {
						java.sql.Statement stmt_tCreateTable_3 = conn_tCreateTable_3.createStatement();
						stmt_tCreateTable_3.execute("CREATE TABLE \"" + tableName_tCreateTable_3
								+ "\"(\"siretetablissement\" VARCHAR ,\"siretetablissementvalide\" VARCHAR ,\"sirenetablissement\" VARCHAR ,\"categorieetablissement\" VARCHAR ,\"denominationsocialeetablissement\" VARCHAR ,\"codecommuneetablissement\" VARCHAR ,\"communeetablissement\" VARCHAR ,\"departementetablissement\" VARCHAR ,\"libelledepartementetablissement\" VARCHAR ,\"coderegionetablissement\" VARCHAR ,\"libelleregionetablissement\" VARCHAR ,primary key(\"sirenetablissement\"))");

					}

					globalMap.put("tCreateTable_3_QUERY", "CREATE TABLE \"" + tableName_tCreateTable_3
							+ "\"(\"siretetablissement\" VARCHAR ,\"siretetablissementvalide\" VARCHAR ,\"sirenetablissement\" VARCHAR ,\"categorieetablissement\" VARCHAR ,\"denominationsocialeetablissement\" VARCHAR ,\"codecommuneetablissement\" VARCHAR ,\"communeetablissement\" VARCHAR ,\"departementetablissement\" VARCHAR ,\"libelledepartementetablissement\" VARCHAR ,\"coderegionetablissement\" VARCHAR ,\"libelleregionetablissement\" VARCHAR ,primary key(\"sirenetablissement\"))");
				} catch (java.lang.Exception e) {
					globalMap.put("tCreateTable_3_ERROR_MESSAGE", e.getMessage());
					throw new RuntimeException("Creating table failed", e);
				}

				tos_count_tCreateTable_3++;

				/**
				 * [tCreateTable_3 main ] stop
				 */

				/**
				 * [tCreateTable_3 process_data_begin ] start
				 */

				currentComponent = "tCreateTable_3";

				/**
				 * [tCreateTable_3 process_data_begin ] stop
				 */

				/**
				 * [tCreateTable_3 process_data_end ] start
				 */

				currentComponent = "tCreateTable_3";

				/**
				 * [tCreateTable_3 process_data_end ] stop
				 */

				/**
				 * [tCreateTable_3 end ] start
				 */

				currentComponent = "tCreateTable_3";

				ok_Hash.put("tCreateTable_3", true);
				end_Hash.put("tCreateTable_3", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk5", 0, "ok");
				}
				tCreateTable_4Process(globalMap);

				/**
				 * [tCreateTable_3 end ] stop
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
				 * [tCreateTable_3 finally ] start
				 */

				currentComponent = "tCreateTable_3";

				/**
				 * [tCreateTable_3 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tCreateTable_3_SUBPROCESS_STATE", 1);
	}

	public void tCreateTable_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tCreateTable_4_SUBPROCESS_STATE", 0);

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
				 * [tCreateTable_4 begin ] start
				 */

				ok_Hash.put("tCreateTable_4", false);
				start_Hash.put("tCreateTable_4", System.currentTimeMillis());

				currentComponent = "tCreateTable_4";

				int tos_count_tCreateTable_4 = 0;

				/**
				 * [tCreateTable_4 begin ] stop
				 */

				/**
				 * [tCreateTable_4 main ] start
				 */

				currentComponent = "tCreateTable_4";

				try {
					String tableName_tCreateTable_4 = "fait_marche";
					String dbSchema_tCreateTable_4 = (String) globalMap.get("schema_tDBConnection_1");
					if (dbSchema_tCreateTable_4 != null && dbSchema_tCreateTable_4.trim().length() != 0) {
						tableName_tCreateTable_4 = dbSchema_tCreateTable_4 + "\".\"" + tableName_tCreateTable_4;
					}
					java.sql.Connection conn_tCreateTable_4 = (java.sql.Connection) globalMap
							.get("conn_tDBConnection_1");
					boolean whetherExist_tCreateTable_4 = false;
					java.sql.DatabaseMetaData dbMetaData_tCreateTable_4 = conn_tCreateTable_4.getMetaData();
					java.sql.ResultSet rsTable_tCreateTable_4 = dbMetaData_tCreateTable_4.getTables(null, null, null,
							new String[] { "TABLE" });
					String defaultSchema_tCreateTable_4 = "public";
					if (dbSchema_tCreateTable_4 == null || dbSchema_tCreateTable_4.trim().length() == 0) {
						java.sql.Statement stmtSchema_tCreateTable_4 = conn_tCreateTable_4.createStatement();
						java.sql.ResultSet rsSchema_tCreateTable_4 = stmtSchema_tCreateTable_4
								.executeQuery("select current_schema() ");
						while (rsSchema_tCreateTable_4.next()) {
							defaultSchema_tCreateTable_4 = rsSchema_tCreateTable_4.getString("current_schema");
						}
						rsSchema_tCreateTable_4.close();
						stmtSchema_tCreateTable_4.close();
					}
					while (rsTable_tCreateTable_4.next()) {
						String table_tCreateTable_4 = rsTable_tCreateTable_4.getString("TABLE_NAME");
						String schema_tCreateTable_4 = rsTable_tCreateTable_4.getString("TABLE_SCHEM");
						if (table_tCreateTable_4.equals("fait_marche") && (schema_tCreateTable_4
								.equals(dbSchema_tCreateTable_4)
								|| ((dbSchema_tCreateTable_4 == null || dbSchema_tCreateTable_4.trim().length() == 0)
										&& defaultSchema_tCreateTable_4.equals(schema_tCreateTable_4)))) {
							whetherExist_tCreateTable_4 = true;
							break;
						}
					}
					rsTable_tCreateTable_4.close();
					if (!whetherExist_tCreateTable_4) {
						java.sql.Statement stmt_tCreateTable_4 = conn_tCreateTable_4.createStatement();
						stmt_tCreateTable_4.execute("CREATE TABLE \"" + tableName_tCreateTable_4
								+ "\"(\"sirenetablissement\" VARCHAR ,\"idacheteur\" VARCHAR ,\"datenotification\" VARCHAR ,\"idmarche\" INT4 ,\"montantmarche\" VARCHAR ,\"montantcalculemarche\" FLOAT4 ,primary key(\"sirenetablissement\",\"idacheteur\",\"datenotification\",\"idmarche\"))");

					}

					globalMap.put("tCreateTable_4_QUERY", "CREATE TABLE \"" + tableName_tCreateTable_4
							+ "\"(\"sirenetablissement\" VARCHAR ,\"idacheteur\" VARCHAR ,\"datenotification\" VARCHAR ,\"idmarche\" INT4 ,\"montantmarche\" VARCHAR ,\"montantcalculemarche\" FLOAT4 ,primary key(\"sirenetablissement\",\"idacheteur\",\"datenotification\",\"idmarche\"))");
				} catch (java.lang.Exception e) {
					globalMap.put("tCreateTable_4_ERROR_MESSAGE", e.getMessage());
					throw new RuntimeException("Creating table failed", e);
				}

				tos_count_tCreateTable_4++;

				/**
				 * [tCreateTable_4 main ] stop
				 */

				/**
				 * [tCreateTable_4 process_data_begin ] start
				 */

				currentComponent = "tCreateTable_4";

				/**
				 * [tCreateTable_4 process_data_begin ] stop
				 */

				/**
				 * [tCreateTable_4 process_data_end ] start
				 */

				currentComponent = "tCreateTable_4";

				/**
				 * [tCreateTable_4 process_data_end ] stop
				 */

				/**
				 * [tCreateTable_4 end ] start
				 */

				currentComponent = "tCreateTable_4";

				ok_Hash.put("tCreateTable_4", true);
				end_Hash.put("tCreateTable_4", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk6", 0, "ok");
				}
				tWarn_1Process(globalMap);

				/**
				 * [tCreateTable_4 end ] stop
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
				 * [tCreateTable_4 finally ] start
				 */

				currentComponent = "tCreateTable_4";

				/**
				 * [tCreateTable_4 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tCreateTable_4_SUBPROCESS_STATE", 1);
	}

	public void tWarn_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tWarn_1_SUBPROCESS_STATE", 0);

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
				 * [tWarn_1 begin ] start
				 */

				ok_Hash.put("tWarn_1", false);
				start_Hash.put("tWarn_1", System.currentTimeMillis());

				currentComponent = "tWarn_1";

				int tos_count_tWarn_1 = 0;

				/**
				 * [tWarn_1 begin ] stop
				 */

				/**
				 * [tWarn_1 main ] start
				 */

				currentComponent = "tWarn_1";

				try {

					resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_1", "", Thread.currentThread().getId() + "", "WARN",
							"", " --------------  CITYVIZ : Start loading data warehouse -------------- ", "", "");
					globalMap.put("tWarn_1_WARN_MESSAGES",
							" --------------  CITYVIZ : Start loading data warehouse -------------- ");
					globalMap.put("tWarn_1_WARN_PRIORITY", 4);
					globalMap.put("tWarn_1_WARN_CODE", 42);

				} catch (Exception e_tWarn_1) {
					globalMap.put("tWarn_1_ERROR_MESSAGE", e_tWarn_1.getMessage());
					logIgnoredError(
							String.format("tWarn_1 - tWarn failed to log message due to internal error: %s", e_tWarn_1),
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
				 * [tWarn_1 end ] start
				 */

				currentComponent = "tWarn_1";

				ok_Hash.put("tWarn_1", true);
				end_Hash.put("tWarn_1", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk3", 0, "ok");
				}
				tDBInput_1Process(globalMap);

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

			try {

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

		globalMap.put("tWarn_1_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public String siretetablissementvalide;

		public String getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public String categorieetablissement;

		public String getCategorieetablissement() {
			return this.categorieetablissement;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public String codecommuneetablissement;

		public String getCodecommuneetablissement() {
			return this.codecommuneetablissement;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public String libelledepartementetablissement;

		public String getLibelledepartementetablissement() {
			return this.libelledepartementetablissement;
		}

		public String coderegionetablissement;

		public String getCoderegionetablissement() {
			return this.coderegionetablissement;
		}

		public String libelleregionetablissement;

		public String getLibelleregionetablissement() {
			return this.libelleregionetablissement;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.sirenetablissement == null) ? 0 : this.sirenetablissement.hashCode());

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

			if (this.sirenetablissement == null) {
				if (other.sirenetablissement != null)
					return false;

			} else if (!this.sirenetablissement.equals(other.sirenetablissement))

				return false;

			return true;
		}

		public void copyDataTo(row6Struct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.siretetablissement = this.siretetablissement;
			other.siretetablissementvalide = this.siretetablissementvalide;
			other.categorieetablissement = this.categorieetablissement;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.codecommuneetablissement = this.codecommuneetablissement;
			other.communeetablissement = this.communeetablissement;
			other.departementetablissement = this.departementetablissement;
			other.libelledepartementetablissement = this.libelledepartementetablissement;
			other.coderegionetablissement = this.coderegionetablissement;
			other.libelleregionetablissement = this.libelleregionetablissement;

		}

		public void copyKeysDataTo(row6Struct other) {

			other.sirenetablissement = this.sirenetablissement;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.siretetablissement = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.categorieetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.departementetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.libelleregionetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.siretetablissement = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.categorieetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.departementetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.libelleregionetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("sirenetablissement=" + sirenetablissement);
			sb.append(",siretetablissement=" + siretetablissement);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",categorieetablissement=" + categorieetablissement);
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",codecommuneetablissement=" + codecommuneetablissement);
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",libelledepartementetablissement=" + libelledepartementetablissement);
			sb.append(",coderegionetablissement=" + coderegionetablissement);
			sb.append(",libelleregionetablissement=" + libelleregionetablissement);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.sirenetablissement, other.sirenetablissement);
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

	public static class etablissementStruct implements routines.system.IPersistableRow<etablissementStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public String siretetablissementvalide;

		public String getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public String categorieetablissement;

		public String getCategorieetablissement() {
			return this.categorieetablissement;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public String codecommuneetablissement;

		public String getCodecommuneetablissement() {
			return this.codecommuneetablissement;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public String libelledepartementetablissement;

		public String getLibelledepartementetablissement() {
			return this.libelledepartementetablissement;
		}

		public String coderegionetablissement;

		public String getCoderegionetablissement() {
			return this.coderegionetablissement;
		}

		public String libelleregionetablissement;

		public String getLibelleregionetablissement() {
			return this.libelleregionetablissement;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.sirenetablissement == null) ? 0 : this.sirenetablissement.hashCode());

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
			final etablissementStruct other = (etablissementStruct) obj;

			if (this.sirenetablissement == null) {
				if (other.sirenetablissement != null)
					return false;

			} else if (!this.sirenetablissement.equals(other.sirenetablissement))

				return false;

			return true;
		}

		public void copyDataTo(etablissementStruct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.siretetablissement = this.siretetablissement;
			other.siretetablissementvalide = this.siretetablissementvalide;
			other.categorieetablissement = this.categorieetablissement;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.codecommuneetablissement = this.codecommuneetablissement;
			other.communeetablissement = this.communeetablissement;
			other.departementetablissement = this.departementetablissement;
			other.libelledepartementetablissement = this.libelledepartementetablissement;
			other.coderegionetablissement = this.coderegionetablissement;
			other.libelleregionetablissement = this.libelleregionetablissement;

		}

		public void copyKeysDataTo(etablissementStruct other) {

			other.sirenetablissement = this.sirenetablissement;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.siretetablissement = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.categorieetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.departementetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.libelleregionetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.siretetablissement = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.categorieetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.departementetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.libelleregionetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("sirenetablissement=" + sirenetablissement);
			sb.append(",siretetablissement=" + siretetablissement);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",categorieetablissement=" + categorieetablissement);
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",codecommuneetablissement=" + codecommuneetablissement);
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",libelledepartementetablissement=" + libelledepartementetablissement);
			sb.append(",coderegionetablissement=" + coderegionetablissement);
			sb.append(",libelleregionetablissement=" + libelleregionetablissement);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(etablissementStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.sirenetablissement, other.sirenetablissement);
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

	public static class doublon_etablissementStruct
			implements routines.system.IPersistableRow<doublon_etablissementStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public String siretetablissementvalide;

		public String getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public String categorieetablissement;

		public String getCategorieetablissement() {
			return this.categorieetablissement;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public String codecommuneetablissement;

		public String getCodecommuneetablissement() {
			return this.codecommuneetablissement;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public String libelledepartementetablissement;

		public String getLibelledepartementetablissement() {
			return this.libelledepartementetablissement;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.sirenetablissement == null) ? 0 : this.sirenetablissement.hashCode());

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
			final doublon_etablissementStruct other = (doublon_etablissementStruct) obj;

			if (this.sirenetablissement == null) {
				if (other.sirenetablissement != null)
					return false;

			} else if (!this.sirenetablissement.equals(other.sirenetablissement))

				return false;

			return true;
		}

		public void copyDataTo(doublon_etablissementStruct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.siretetablissement = this.siretetablissement;
			other.siretetablissementvalide = this.siretetablissementvalide;
			other.categorieetablissement = this.categorieetablissement;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.codecommuneetablissement = this.codecommuneetablissement;
			other.communeetablissement = this.communeetablissement;
			other.departementetablissement = this.departementetablissement;
			other.libelledepartementetablissement = this.libelledepartementetablissement;

		}

		public void copyKeysDataTo(doublon_etablissementStruct other) {

			other.sirenetablissement = this.sirenetablissement;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.siretetablissement = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.categorieetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.departementetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.siretetablissement = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.categorieetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.departementetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("sirenetablissement=" + sirenetablissement);
			sb.append(",siretetablissement=" + siretetablissement);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",categorieetablissement=" + categorieetablissement);
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",codecommuneetablissement=" + codecommuneetablissement);
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",libelledepartementetablissement=" + libelledepartementetablissement);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(doublon_etablissementStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.sirenetablissement, other.sirenetablissement);
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

	public static class siren_nullStruct implements routines.system.IPersistableRow<siren_nullStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public String siretetablissementvalide;

		public String getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public String categorieetablissement;

		public String getCategorieetablissement() {
			return this.categorieetablissement;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public String codecommuneetablissement;

		public String getCodecommuneetablissement() {
			return this.codecommuneetablissement;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public String libelledepartementetablissement;

		public String getLibelledepartementetablissement() {
			return this.libelledepartementetablissement;
		}

		public String coderegionetablissement;

		public String getCoderegionetablissement() {
			return this.coderegionetablissement;
		}

		public String libelleregionetablissement;

		public String getLibelleregionetablissement() {
			return this.libelleregionetablissement;
		}

		public String recordid;

		public String getRecordid() {
			return this.recordid;
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

				result = prime * result + ((this.sirenetablissement == null) ? 0 : this.sirenetablissement.hashCode());

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
			final siren_nullStruct other = (siren_nullStruct) obj;

			if (this.sirenetablissement == null) {
				if (other.sirenetablissement != null)
					return false;

			} else if (!this.sirenetablissement.equals(other.sirenetablissement))

				return false;

			return true;
		}

		public void copyDataTo(siren_nullStruct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.siretetablissement = this.siretetablissement;
			other.siretetablissementvalide = this.siretetablissementvalide;
			other.categorieetablissement = this.categorieetablissement;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.codecommuneetablissement = this.codecommuneetablissement;
			other.communeetablissement = this.communeetablissement;
			other.departementetablissement = this.departementetablissement;
			other.libelledepartementetablissement = this.libelledepartementetablissement;
			other.coderegionetablissement = this.coderegionetablissement;
			other.libelleregionetablissement = this.libelleregionetablissement;
			other.recordid = this.recordid;
			other.idmarche = this.idmarche;

		}

		public void copyKeysDataTo(siren_nullStruct other) {

			other.sirenetablissement = this.sirenetablissement;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.siretetablissement = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.categorieetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.departementetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.libelleregionetablissement = readString(dis);

					this.recordid = readString(dis);

					this.idmarche = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.siretetablissement = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.categorieetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.departementetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.libelleregionetablissement = readString(dis);

					this.recordid = readString(dis);

					this.idmarche = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

				// String

				writeString(this.recordid, dos);

				// Integer

				writeInteger(this.idmarche, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

				// String

				writeString(this.recordid, dos);

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
			sb.append("sirenetablissement=" + sirenetablissement);
			sb.append(",siretetablissement=" + siretetablissement);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",categorieetablissement=" + categorieetablissement);
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",codecommuneetablissement=" + codecommuneetablissement);
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",libelledepartementetablissement=" + libelledepartementetablissement);
			sb.append(",coderegionetablissement=" + coderegionetablissement);
			sb.append(",libelleregionetablissement=" + libelleregionetablissement);
			sb.append(",recordid=" + recordid);
			sb.append(",idmarche=" + String.valueOf(idmarche));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(siren_nullStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.sirenetablissement, other.sirenetablissement);
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

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		public String natureobjetmarche;

		public String getNatureobjetmarche() {
			return this.natureobjetmarche;
		}

		public String objetmarche;

		public String getObjetmarche() {
			return this.objetmarche;
		}

		public String datenotification;

		public String getDatenotification() {
			return this.datenotification;
		}

		public Integer dureemois;

		public Integer getDureemois() {
			return this.dureemois;
		}

		public String lieuexecutionnom;

		public String getLieuexecutionnom() {
			return this.lieuexecutionnom;
		}

		public String formeprix;

		public String getFormeprix() {
			return this.formeprix;
		}

		public String lieuexecutiontypecode;

		public String getLieuexecutiontypecode() {
			return this.lieuexecutiontypecode;
		}

		public Float codedepartementexecution;

		public Float getCodedepartementexecution() {
			return this.codedepartementexecution;
		}

		public Float coderegionexecution;

		public Float getCoderegionexecution() {
			return this.coderegionexecution;
		}

		public String libelleregionexecution;

		public String getLibelleregionexecution() {
			return this.libelleregionexecution;
		}

		public String nature;

		public String getNature() {
			return this.nature;
		}

		public String procedure;

		public String getProcedure() {
			return this.procedure;
		}

		public String codecpv;

		public String getCodecpv() {
			return this.codecpv;
		}

		public String codecpv_original;

		public String getCodecpv_original() {
			return this.codecpv_original;
		}

		public Integer codecpv_division;

		public Integer getCodecpv_division() {
			return this.codecpv_division;
		}

		public String referencecpv;

		public String getReferencecpv() {
			return this.referencecpv;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.idmarche == null) ? 0 : this.idmarche.hashCode());

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
			final row5Struct other = (row5Struct) obj;

			if (this.idmarche == null) {
				if (other.idmarche != null)
					return false;

			} else if (!this.idmarche.equals(other.idmarche))

				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.idmarche = this.idmarche;
			other.natureobjetmarche = this.natureobjetmarche;
			other.objetmarche = this.objetmarche;
			other.datenotification = this.datenotification;
			other.dureemois = this.dureemois;
			other.lieuexecutionnom = this.lieuexecutionnom;
			other.formeprix = this.formeprix;
			other.lieuexecutiontypecode = this.lieuexecutiontypecode;
			other.codedepartementexecution = this.codedepartementexecution;
			other.coderegionexecution = this.coderegionexecution;
			other.libelleregionexecution = this.libelleregionexecution;
			other.nature = this.nature;
			other.procedure = this.procedure;
			other.codecpv = this.codecpv;
			other.codecpv_original = this.codecpv_original;
			other.codecpv_division = this.codecpv_division;
			other.referencecpv = this.referencecpv;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.idmarche = this.idmarche;

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idmarche = readInteger(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.referencecpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idmarche = readInteger(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.referencecpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.datenotification, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.codecpv_original, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.referencecpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.datenotification, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.codecpv_original, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.referencecpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idmarche=" + String.valueOf(idmarche));
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",datenotification=" + datenotification);
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",formeprix=" + formeprix);
			sb.append(",lieuexecutiontypecode=" + lieuexecutiontypecode);
			sb.append(",codedepartementexecution=" + String.valueOf(codedepartementexecution));
			sb.append(",coderegionexecution=" + String.valueOf(coderegionexecution));
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append(",nature=" + nature);
			sb.append(",procedure=" + procedure);
			sb.append(",codecpv=" + codecpv);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",codecpv_division=" + String.valueOf(codecpv_division));
			sb.append(",referencecpv=" + referencecpv);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idmarche, other.idmarche);
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

	public static class marcheStruct implements routines.system.IPersistableRow<marcheStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		public String natureobjetmarche;

		public String getNatureobjetmarche() {
			return this.natureobjetmarche;
		}

		public String objetmarche;

		public String getObjetmarche() {
			return this.objetmarche;
		}

		public String datenotification;

		public String getDatenotification() {
			return this.datenotification;
		}

		public Integer dureemois;

		public Integer getDureemois() {
			return this.dureemois;
		}

		public String lieuexecutionnom;

		public String getLieuexecutionnom() {
			return this.lieuexecutionnom;
		}

		public String formeprix;

		public String getFormeprix() {
			return this.formeprix;
		}

		public String lieuexecutiontypecode;

		public String getLieuexecutiontypecode() {
			return this.lieuexecutiontypecode;
		}

		public Float codedepartementexecution;

		public Float getCodedepartementexecution() {
			return this.codedepartementexecution;
		}

		public Float coderegionexecution;

		public Float getCoderegionexecution() {
			return this.coderegionexecution;
		}

		public String libelleregionexecution;

		public String getLibelleregionexecution() {
			return this.libelleregionexecution;
		}

		public String nature;

		public String getNature() {
			return this.nature;
		}

		public String procedure;

		public String getProcedure() {
			return this.procedure;
		}

		public String codecpv;

		public String getCodecpv() {
			return this.codecpv;
		}

		public String codecpv_original;

		public String getCodecpv_original() {
			return this.codecpv_original;
		}

		public Integer codecpv_division;

		public Integer getCodecpv_division() {
			return this.codecpv_division;
		}

		public String referencecpv;

		public String getReferencecpv() {
			return this.referencecpv;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.idmarche == null) ? 0 : this.idmarche.hashCode());

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
			final marcheStruct other = (marcheStruct) obj;

			if (this.idmarche == null) {
				if (other.idmarche != null)
					return false;

			} else if (!this.idmarche.equals(other.idmarche))

				return false;

			return true;
		}

		public void copyDataTo(marcheStruct other) {

			other.idmarche = this.idmarche;
			other.natureobjetmarche = this.natureobjetmarche;
			other.objetmarche = this.objetmarche;
			other.datenotification = this.datenotification;
			other.dureemois = this.dureemois;
			other.lieuexecutionnom = this.lieuexecutionnom;
			other.formeprix = this.formeprix;
			other.lieuexecutiontypecode = this.lieuexecutiontypecode;
			other.codedepartementexecution = this.codedepartementexecution;
			other.coderegionexecution = this.coderegionexecution;
			other.libelleregionexecution = this.libelleregionexecution;
			other.nature = this.nature;
			other.procedure = this.procedure;
			other.codecpv = this.codecpv;
			other.codecpv_original = this.codecpv_original;
			other.codecpv_division = this.codecpv_division;
			other.referencecpv = this.referencecpv;

		}

		public void copyKeysDataTo(marcheStruct other) {

			other.idmarche = this.idmarche;

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idmarche = readInteger(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.referencecpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idmarche = readInteger(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.referencecpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.datenotification, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.codecpv_original, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.referencecpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.datenotification, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.codecpv_original, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.referencecpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idmarche=" + String.valueOf(idmarche));
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",datenotification=" + datenotification);
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",formeprix=" + formeprix);
			sb.append(",lieuexecutiontypecode=" + lieuexecutiontypecode);
			sb.append(",codedepartementexecution=" + String.valueOf(codedepartementexecution));
			sb.append(",coderegionexecution=" + String.valueOf(coderegionexecution));
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append(",nature=" + nature);
			sb.append(",procedure=" + procedure);
			sb.append(",codecpv=" + codecpv);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",codecpv_division=" + String.valueOf(codecpv_division));
			sb.append(",referencecpv=" + referencecpv);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(marcheStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idmarche, other.idmarche);
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

	public static class doublon_marcheStruct implements routines.system.IPersistableRow<doublon_marcheStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		public String natureobjetmarche;

		public String getNatureobjetmarche() {
			return this.natureobjetmarche;
		}

		public String objetmarche;

		public String getObjetmarche() {
			return this.objetmarche;
		}

		public String datenotification;

		public String getDatenotification() {
			return this.datenotification;
		}

		public String lieuexecutionnom;

		public String getLieuexecutionnom() {
			return this.lieuexecutionnom;
		}

		public String libelleregionexecution;

		public String getLibelleregionexecution() {
			return this.libelleregionexecution;
		}

		public String nature;

		public String getNature() {
			return this.nature;
		}

		public String codecpv;

		public String getCodecpv() {
			return this.codecpv;
		}

		public String codecpv_original;

		public String getCodecpv_original() {
			return this.codecpv_original;
		}

		public Integer codecpv_division;

		public Integer getCodecpv_division() {
			return this.codecpv_division;
		}

		public String referencecpv;

		public String getReferencecpv() {
			return this.referencecpv;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.idmarche == null) ? 0 : this.idmarche.hashCode());

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
			final doublon_marcheStruct other = (doublon_marcheStruct) obj;

			if (this.idmarche == null) {
				if (other.idmarche != null)
					return false;

			} else if (!this.idmarche.equals(other.idmarche))

				return false;

			return true;
		}

		public void copyDataTo(doublon_marcheStruct other) {

			other.idmarche = this.idmarche;
			other.natureobjetmarche = this.natureobjetmarche;
			other.objetmarche = this.objetmarche;
			other.datenotification = this.datenotification;
			other.lieuexecutionnom = this.lieuexecutionnom;
			other.libelleregionexecution = this.libelleregionexecution;
			other.nature = this.nature;
			other.codecpv = this.codecpv;
			other.codecpv_original = this.codecpv_original;
			other.codecpv_division = this.codecpv_division;
			other.referencecpv = this.referencecpv;

		}

		public void copyKeysDataTo(doublon_marcheStruct other) {

			other.idmarche = this.idmarche;

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idmarche = readInteger(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.lieuexecutionnom = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.referencecpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idmarche = readInteger(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.lieuexecutionnom = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.referencecpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.datenotification, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.codecpv_original, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.referencecpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.datenotification, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.codecpv_original, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.referencecpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idmarche=" + String.valueOf(idmarche));
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",datenotification=" + datenotification);
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append(",nature=" + nature);
			sb.append(",codecpv=" + codecpv);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",codecpv_division=" + String.valueOf(codecpv_division));
			sb.append(",referencecpv=" + referencecpv);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(doublon_marcheStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idmarche, other.idmarche);
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

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public String nomacheteur;

		public String getNomacheteur() {
			return this.nomacheteur;
		}

		public String codepostaleacheteur;

		public String getCodepostaleacheteur() {
			return this.codepostaleacheteur;
		}

		public String codepostalecommune;

		public String getCodepostalecommune() {
			return this.codepostalecommune;
		}

		public String nomcommune;

		public String getNomcommune() {
			return this.nomcommune;
		}

		public String codedepartement;

		public String getCodedepartement() {
			return this.codedepartement;
		}

		public String libelledepartement;

		public String getLibelledepartement() {
			return this.libelledepartement;
		}

		public String coderegion;

		public String getCoderegion() {
			return this.coderegion;
		}

		public String libelleregion;

		public String getLibelleregion() {
			return this.libelleregion;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.idacheteur == null) ? 0 : this.idacheteur.hashCode());

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
			final row4Struct other = (row4Struct) obj;

			if (this.idacheteur == null) {
				if (other.idacheteur != null)
					return false;

			} else if (!this.idacheteur.equals(other.idacheteur))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.idacheteur = this.idacheteur;
			other.nomacheteur = this.nomacheteur;
			other.codepostaleacheteur = this.codepostaleacheteur;
			other.codepostalecommune = this.codepostalecommune;
			other.nomcommune = this.nomcommune;
			other.codedepartement = this.codedepartement;
			other.libelledepartement = this.libelledepartement;
			other.coderegion = this.coderegion;
			other.libelleregion = this.libelleregion;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.idacheteur = this.idacheteur;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idacheteur = readString(dis);

					this.nomacheteur = readString(dis);

					this.codepostaleacheteur = readString(dis);

					this.codepostalecommune = readString(dis);

					this.nomcommune = readString(dis);

					this.codedepartement = readString(dis);

					this.libelledepartement = readString(dis);

					this.coderegion = readString(dis);

					this.libelleregion = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idacheteur = readString(dis);

					this.nomacheteur = readString(dis);

					this.codepostaleacheteur = readString(dis);

					this.codepostalecommune = readString(dis);

					this.nomcommune = readString(dis);

					this.codedepartement = readString(dis);

					this.libelledepartement = readString(dis);

					this.coderegion = readString(dis);

					this.libelleregion = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.codepostaleacheteur, dos);

				// String

				writeString(this.codepostalecommune, dos);

				// String

				writeString(this.nomcommune, dos);

				// String

				writeString(this.codedepartement, dos);

				// String

				writeString(this.libelledepartement, dos);

				// String

				writeString(this.coderegion, dos);

				// String

				writeString(this.libelleregion, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.codepostaleacheteur, dos);

				// String

				writeString(this.codepostalecommune, dos);

				// String

				writeString(this.nomcommune, dos);

				// String

				writeString(this.codedepartement, dos);

				// String

				writeString(this.libelledepartement, dos);

				// String

				writeString(this.coderegion, dos);

				// String

				writeString(this.libelleregion, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idacheteur=" + idacheteur);
			sb.append(",nomacheteur=" + nomacheteur);
			sb.append(",codepostaleacheteur=" + codepostaleacheteur);
			sb.append(",codepostalecommune=" + codepostalecommune);
			sb.append(",nomcommune=" + nomcommune);
			sb.append(",codedepartement=" + codedepartement);
			sb.append(",libelledepartement=" + libelledepartement);
			sb.append(",coderegion=" + coderegion);
			sb.append(",libelleregion=" + libelleregion);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idacheteur, other.idacheteur);
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

	public static class acheteurStruct implements routines.system.IPersistableRow<acheteurStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public String nomacheteur;

		public String getNomacheteur() {
			return this.nomacheteur;
		}

		public String codepostaleacheteur;

		public String getCodepostaleacheteur() {
			return this.codepostaleacheteur;
		}

		public String codepostalecommune;

		public String getCodepostalecommune() {
			return this.codepostalecommune;
		}

		public String nomcommune;

		public String getNomcommune() {
			return this.nomcommune;
		}

		public String codedepartement;

		public String getCodedepartement() {
			return this.codedepartement;
		}

		public String libelledepartement;

		public String getLibelledepartement() {
			return this.libelledepartement;
		}

		public String coderegion;

		public String getCoderegion() {
			return this.coderegion;
		}

		public String libelleregion;

		public String getLibelleregion() {
			return this.libelleregion;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.idacheteur == null) ? 0 : this.idacheteur.hashCode());

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
			final acheteurStruct other = (acheteurStruct) obj;

			if (this.idacheteur == null) {
				if (other.idacheteur != null)
					return false;

			} else if (!this.idacheteur.equals(other.idacheteur))

				return false;

			return true;
		}

		public void copyDataTo(acheteurStruct other) {

			other.idacheteur = this.idacheteur;
			other.nomacheteur = this.nomacheteur;
			other.codepostaleacheteur = this.codepostaleacheteur;
			other.codepostalecommune = this.codepostalecommune;
			other.nomcommune = this.nomcommune;
			other.codedepartement = this.codedepartement;
			other.libelledepartement = this.libelledepartement;
			other.coderegion = this.coderegion;
			other.libelleregion = this.libelleregion;

		}

		public void copyKeysDataTo(acheteurStruct other) {

			other.idacheteur = this.idacheteur;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idacheteur = readString(dis);

					this.nomacheteur = readString(dis);

					this.codepostaleacheteur = readString(dis);

					this.codepostalecommune = readString(dis);

					this.nomcommune = readString(dis);

					this.codedepartement = readString(dis);

					this.libelledepartement = readString(dis);

					this.coderegion = readString(dis);

					this.libelleregion = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idacheteur = readString(dis);

					this.nomacheteur = readString(dis);

					this.codepostaleacheteur = readString(dis);

					this.codepostalecommune = readString(dis);

					this.nomcommune = readString(dis);

					this.codedepartement = readString(dis);

					this.libelledepartement = readString(dis);

					this.coderegion = readString(dis);

					this.libelleregion = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.codepostaleacheteur, dos);

				// String

				writeString(this.codepostalecommune, dos);

				// String

				writeString(this.nomcommune, dos);

				// String

				writeString(this.codedepartement, dos);

				// String

				writeString(this.libelledepartement, dos);

				// String

				writeString(this.coderegion, dos);

				// String

				writeString(this.libelleregion, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.codepostaleacheteur, dos);

				// String

				writeString(this.codepostalecommune, dos);

				// String

				writeString(this.nomcommune, dos);

				// String

				writeString(this.codedepartement, dos);

				// String

				writeString(this.libelledepartement, dos);

				// String

				writeString(this.coderegion, dos);

				// String

				writeString(this.libelleregion, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idacheteur=" + idacheteur);
			sb.append(",nomacheteur=" + nomacheteur);
			sb.append(",codepostaleacheteur=" + codepostaleacheteur);
			sb.append(",codepostalecommune=" + codepostalecommune);
			sb.append(",nomcommune=" + nomcommune);
			sb.append(",codedepartement=" + codedepartement);
			sb.append(",libelledepartement=" + libelledepartement);
			sb.append(",coderegion=" + coderegion);
			sb.append(",libelleregion=" + libelleregion);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(acheteurStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idacheteur, other.idacheteur);
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

	public static class doublons_acheteurStruct implements routines.system.IPersistableRow<doublons_acheteurStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public String nomacheteur;

		public String getNomacheteur() {
			return this.nomacheteur;
		}

		public String codepostaleacheteur;

		public String getCodepostaleacheteur() {
			return this.codepostaleacheteur;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.idacheteur == null) ? 0 : this.idacheteur.hashCode());

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
			final doublons_acheteurStruct other = (doublons_acheteurStruct) obj;

			if (this.idacheteur == null) {
				if (other.idacheteur != null)
					return false;

			} else if (!this.idacheteur.equals(other.idacheteur))

				return false;

			return true;
		}

		public void copyDataTo(doublons_acheteurStruct other) {

			other.idacheteur = this.idacheteur;
			other.nomacheteur = this.nomacheteur;
			other.codepostaleacheteur = this.codepostaleacheteur;

		}

		public void copyKeysDataTo(doublons_acheteurStruct other) {

			other.idacheteur = this.idacheteur;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idacheteur = readString(dis);

					this.nomacheteur = readString(dis);

					this.codepostaleacheteur = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idacheteur = readString(dis);

					this.nomacheteur = readString(dis);

					this.codepostaleacheteur = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.codepostaleacheteur, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.codepostaleacheteur, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idacheteur=" + idacheteur);
			sb.append(",nomacheteur=" + nomacheteur);
			sb.append(",codepostaleacheteur=" + codepostaleacheteur);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(doublons_acheteurStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idacheteur, other.idacheteur);
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

	public static class dim_acheteurStruct implements routines.system.IPersistableRow<dim_acheteurStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public String nomacheteur;

		public String getNomacheteur() {
			return this.nomacheteur;
		}

		public String codepostaleacheteur;

		public String getCodepostaleacheteur() {
			return this.codepostaleacheteur;
		}

		public String codepostalecommune;

		public String getCodepostalecommune() {
			return this.codepostalecommune;
		}

		public String nomcommune;

		public String getNomcommune() {
			return this.nomcommune;
		}

		public String codedepartement;

		public String getCodedepartement() {
			return this.codedepartement;
		}

		public String libelledepartement;

		public String getLibelledepartement() {
			return this.libelledepartement;
		}

		public String coderegion;

		public String getCoderegion() {
			return this.coderegion;
		}

		public String libelleregion;

		public String getLibelleregion() {
			return this.libelleregion;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idacheteur = readString(dis);

					this.nomacheteur = readString(dis);

					this.codepostaleacheteur = readString(dis);

					this.codepostalecommune = readString(dis);

					this.nomcommune = readString(dis);

					this.codedepartement = readString(dis);

					this.libelledepartement = readString(dis);

					this.coderegion = readString(dis);

					this.libelleregion = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idacheteur = readString(dis);

					this.nomacheteur = readString(dis);

					this.codepostaleacheteur = readString(dis);

					this.codepostalecommune = readString(dis);

					this.nomcommune = readString(dis);

					this.codedepartement = readString(dis);

					this.libelledepartement = readString(dis);

					this.coderegion = readString(dis);

					this.libelleregion = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.codepostaleacheteur, dos);

				// String

				writeString(this.codepostalecommune, dos);

				// String

				writeString(this.nomcommune, dos);

				// String

				writeString(this.codedepartement, dos);

				// String

				writeString(this.libelledepartement, dos);

				// String

				writeString(this.coderegion, dos);

				// String

				writeString(this.libelleregion, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.nomacheteur, dos);

				// String

				writeString(this.codepostaleacheteur, dos);

				// String

				writeString(this.codepostalecommune, dos);

				// String

				writeString(this.nomcommune, dos);

				// String

				writeString(this.codedepartement, dos);

				// String

				writeString(this.libelledepartement, dos);

				// String

				writeString(this.coderegion, dos);

				// String

				writeString(this.libelleregion, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idacheteur=" + idacheteur);
			sb.append(",nomacheteur=" + nomacheteur);
			sb.append(",codepostaleacheteur=" + codepostaleacheteur);
			sb.append(",codepostalecommune=" + codepostalecommune);
			sb.append(",nomcommune=" + nomcommune);
			sb.append(",codedepartement=" + codedepartement);
			sb.append(",libelledepartement=" + libelledepartement);
			sb.append(",coderegion=" + coderegion);
			sb.append(",libelleregion=" + libelleregion);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(dim_acheteurStruct other) {

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

	public static class dim_marcheStruct implements routines.system.IPersistableRow<dim_marcheStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		public String natureobjetmarche;

		public String getNatureobjetmarche() {
			return this.natureobjetmarche;
		}

		public String objetmarche;

		public String getObjetmarche() {
			return this.objetmarche;
		}

		public String datenotification;

		public String getDatenotification() {
			return this.datenotification;
		}

		public Integer dureemois;

		public Integer getDureemois() {
			return this.dureemois;
		}

		public String lieuexecutionnom;

		public String getLieuexecutionnom() {
			return this.lieuexecutionnom;
		}

		public String formeprix;

		public String getFormeprix() {
			return this.formeprix;
		}

		public String lieuexecutiontypecode;

		public String getLieuexecutiontypecode() {
			return this.lieuexecutiontypecode;
		}

		public Float codedepartementexecution;

		public Float getCodedepartementexecution() {
			return this.codedepartementexecution;
		}

		public Float coderegionexecution;

		public Float getCoderegionexecution() {
			return this.coderegionexecution;
		}

		public String libelleregionexecution;

		public String getLibelleregionexecution() {
			return this.libelleregionexecution;
		}

		public String nature;

		public String getNature() {
			return this.nature;
		}

		public String procedure;

		public String getProcedure() {
			return this.procedure;
		}

		public String codecpv;

		public String getCodecpv() {
			return this.codecpv;
		}

		public String codecpv_original;

		public String getCodecpv_original() {
			return this.codecpv_original;
		}

		public Integer codecpv_division;

		public Integer getCodecpv_division() {
			return this.codecpv_division;
		}

		public String referencecpv;

		public String getReferencecpv() {
			return this.referencecpv;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idmarche = readInteger(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.referencecpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.idmarche = readInteger(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.codedepartementexecution = null;
					} else {
						this.codedepartementexecution = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.coderegionexecution = null;
					} else {
						this.coderegionexecution = dis.readFloat();
					}

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readInteger(dis);

					this.referencecpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.datenotification, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.codecpv_original, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.referencecpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.objetmarche, dos);

				// String

				writeString(this.datenotification, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// Float

				if (this.codedepartementexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.codedepartementexecution);
				}

				// Float

				if (this.coderegionexecution == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.coderegionexecution);
				}

				// String

				writeString(this.libelleregionexecution, dos);

				// String

				writeString(this.nature, dos);

				// String

				writeString(this.procedure, dos);

				// String

				writeString(this.codecpv, dos);

				// String

				writeString(this.codecpv_original, dos);

				// Integer

				writeInteger(this.codecpv_division, dos);

				// String

				writeString(this.referencecpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idmarche=" + String.valueOf(idmarche));
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",datenotification=" + datenotification);
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",formeprix=" + formeprix);
			sb.append(",lieuexecutiontypecode=" + lieuexecutiontypecode);
			sb.append(",codedepartementexecution=" + String.valueOf(codedepartementexecution));
			sb.append(",coderegionexecution=" + String.valueOf(coderegionexecution));
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append(",nature=" + nature);
			sb.append(",procedure=" + procedure);
			sb.append(",codecpv=" + codecpv);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",codecpv_division=" + String.valueOf(codecpv_division));
			sb.append(",referencecpv=" + referencecpv);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(dim_marcheStruct other) {

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

	public static class dim_etablissementStruct implements routines.system.IPersistableRow<dim_etablissementStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public String siretetablissementvalide;

		public String getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public String categorieetablissement;

		public String getCategorieetablissement() {
			return this.categorieetablissement;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
		}

		public String codecommuneetablissement;

		public String getCodecommuneetablissement() {
			return this.codecommuneetablissement;
		}

		public String communeetablissement;

		public String getCommuneetablissement() {
			return this.communeetablissement;
		}

		public String departementetablissement;

		public String getDepartementetablissement() {
			return this.departementetablissement;
		}

		public String libelledepartementetablissement;

		public String getLibelledepartementetablissement() {
			return this.libelledepartementetablissement;
		}

		public String coderegionetablissement;

		public String getCoderegionetablissement() {
			return this.coderegionetablissement;
		}

		public String libelleregionetablissement;

		public String getLibelleregionetablissement() {
			return this.libelleregionetablissement;
		}

		public String recordid;

		public String getRecordid() {
			return this.recordid;
		}

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.siretetablissement = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.categorieetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.departementetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.libelleregionetablissement = readString(dis);

					this.recordid = readString(dis);

					this.idmarche = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.siretetablissement = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.categorieetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

					this.codecommuneetablissement = readString(dis);

					this.communeetablissement = readString(dis);

					this.departementetablissement = readString(dis);

					this.libelledepartementetablissement = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.libelleregionetablissement = readString(dis);

					this.recordid = readString(dis);

					this.idmarche = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

				// String

				writeString(this.recordid, dos);

				// Integer

				writeInteger(this.idmarche, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.siretetablissement, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.categorieetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.codecommuneetablissement, dos);

				// String

				writeString(this.communeetablissement, dos);

				// String

				writeString(this.departementetablissement, dos);

				// String

				writeString(this.libelledepartementetablissement, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.libelleregionetablissement, dos);

				// String

				writeString(this.recordid, dos);

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
			sb.append("sirenetablissement=" + sirenetablissement);
			sb.append(",siretetablissement=" + siretetablissement);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",categorieetablissement=" + categorieetablissement);
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",codecommuneetablissement=" + codecommuneetablissement);
			sb.append(",communeetablissement=" + communeetablissement);
			sb.append(",departementetablissement=" + departementetablissement);
			sb.append(",libelledepartementetablissement=" + libelledepartementetablissement);
			sb.append(",coderegionetablissement=" + coderegionetablissement);
			sb.append(",libelleregionetablissement=" + libelleregionetablissement);
			sb.append(",recordid=" + recordid);
			sb.append(",idmarche=" + String.valueOf(idmarche));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(dim_etablissementStruct other) {

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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

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

	public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

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
				dim_acheteurStruct dim_acheteur = new dim_acheteurStruct();
				acheteurStruct acheteur = new acheteurStruct();
				row4Struct row4 = new row4Struct();
				doublons_acheteurStruct doublons_acheteur = new doublons_acheteurStruct();
				dim_marcheStruct dim_marche = new dim_marcheStruct();
				marcheStruct marche = new marcheStruct();
				row5Struct row5 = new row5Struct();
				doublon_marcheStruct doublon_marche = new doublon_marcheStruct();
				dim_etablissementStruct dim_etablissement = new dim_etablissementStruct();
				etablissementStruct etablissement = new etablissementStruct();
				row6Struct row6 = new row6Struct();
				doublon_etablissementStruct doublon_etablissement = new doublon_etablissementStruct();
				siren_nullStruct siren_null = new siren_nullStruct();

				/**
				 * [tDBCommit_1 begin ] start
				 */

				ok_Hash.put("tDBCommit_1", false);
				start_Hash.put("tDBCommit_1", System.currentTimeMillis());

				currentComponent = "tDBCommit_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tDBCommit_1 = 0;

				/**
				 * [tDBCommit_1 begin ] stop
				 */

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "acheteur");
				}

				int tos_count_tDBOutput_1 = 0;

				String dbschema_tDBOutput_1 = null;
				dbschema_tDBOutput_1 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_1 = null;
				if (dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
					tableName_tDBOutput_1 = ("dim_acheteur");
				} else {
					tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "\".\"" + ("dim_acheteur");
				}

				int updateKeyCount_tDBOutput_1 = 1;
				if (updateKeyCount_tDBOutput_1 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_1 == 9 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;

				boolean whetherReject_tDBOutput_1 = false;

				java.sql.Connection conn_tDBOutput_1 = null;
				String dbUser_tDBOutput_1 = null;

				conn_tDBOutput_1 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				int count_tDBOutput_1 = 0;
				String update_tDBOutput_1 = "UPDATE \"" + tableName_tDBOutput_1
						+ "\" SET \"nomacheteur\" = ?,\"codepostalacheteur\" = ?,\"codepostalcommune\" = ?,\"nomcommune\" = ?,\"codedepartement\" = ?,\"libelledepartement\" = ?,\"coderegion\" = ?,\"libelleregion\" = ? WHERE \"idacheteur\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(update_tDBOutput_1);
				resourceMap.put("pstmtUpdate_tDBOutput_1", pstmtUpdate_tDBOutput_1);
				String insert_tDBOutput_1 = "INSERT INTO \"" + tableName_tDBOutput_1
						+ "\" (\"idacheteur\",\"nomacheteur\",\"codepostalacheteur\",\"codepostalcommune\",\"nomcommune\",\"codedepartement\",\"libelledepartement\",\"coderegion\",\"libelleregion\") VALUES (?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmtInsert_tDBOutput_1", pstmtInsert_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "doublons_acheteur");
				}

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						context.logs_path + "/insert_doublons/doublons_dim_acheteur.csv")).getAbsolutePath()
								.replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						dir_tFileOutputDelimited_1.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_1.length() == 0) {
					outtFileOutputDelimited_1.write("idacheteur");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("nomacheteur");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("codepostaleacheteur");
					outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tMap_2 begin ] start
				 */

				ok_Hash.put("tMap_2", false);
				start_Hash.put("tMap_2", System.currentTimeMillis());

				currentComponent = "tMap_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "dim_acheteur");
				}

				int tos_count_tMap_2 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = null;

				row2Struct row2HashKey = new row2Struct();
				row2Struct row2Default = new row2Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				acheteurStruct acheteur_tmp = new acheteurStruct();
				doublons_acheteurStruct doublons_acheteur_tmp = new doublons_acheteurStruct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
				 */

				/**
				 * [tDBCommit_2 begin ] start
				 */

				ok_Hash.put("tDBCommit_2", false);
				start_Hash.put("tDBCommit_2", System.currentTimeMillis());

				currentComponent = "tDBCommit_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tDBCommit_2 = 0;

				/**
				 * [tDBCommit_2 begin ] stop
				 */

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "marche");
				}

				int tos_count_tDBOutput_2 = 0;

				String dbschema_tDBOutput_2 = null;
				dbschema_tDBOutput_2 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_2 = null;
				if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
					tableName_tDBOutput_2 = ("dim_marche");
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "\".\"" + ("dim_marche");
				}

				int updateKeyCount_tDBOutput_2 = 1;
				if (updateKeyCount_tDBOutput_2 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_2 == 17 && true) {
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
				String update_tDBOutput_2 = "UPDATE \"" + tableName_tDBOutput_2
						+ "\" SET \"natureobjetmarche\" = ?,\"objetmarche\" = ?,\"datenotification\" = ?,\"dureemois\" = ?,\"lieuexecutionnom\" = ?,\"formeprix\" = ?,\"lieuexecutiontypecode\" = ?,\"codedepartementexecution\" = ?,\"coderegionexecution\" = ?,\"libelleregionexecution\" = ?,\"nature\" = ?,\"procedure\" = ?,\"codecpv\" = ?,\"codecpv_original\" = ?,\"codecpv_division\" = ?,\"referencecpv\" = ? WHERE \"idmarche\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_2 = conn_tDBOutput_2
						.prepareStatement(update_tDBOutput_2);
				resourceMap.put("pstmtUpdate_tDBOutput_2", pstmtUpdate_tDBOutput_2);
				String insert_tDBOutput_2 = "INSERT INTO \"" + tableName_tDBOutput_2
						+ "\" (\"idmarche\",\"natureobjetmarche\",\"objetmarche\",\"datenotification\",\"dureemois\",\"lieuexecutionnom\",\"formeprix\",\"lieuexecutiontypecode\",\"codedepartementexecution\",\"coderegionexecution\",\"libelleregionexecution\",\"nature\",\"procedure\",\"codecpv\",\"codecpv_original\",\"codecpv_division\",\"referencecpv\") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_2 = conn_tDBOutput_2
						.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmtInsert_tDBOutput_2", pstmtInsert_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_2", false);
				start_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "doublon_marche");
				}

				int tos_count_tFileOutputDelimited_2 = 0;

				String fileName_tFileOutputDelimited_2 = "";
				fileName_tFileOutputDelimited_2 = (new java.io.File(
						context.logs_path + "/insert_doublons/doublons_dim_marche.csv")).getAbsolutePath().replace("\\",
								"/");
				String fullName_tFileOutputDelimited_2 = null;
				String extension_tFileOutputDelimited_2 = null;
				String directory_tFileOutputDelimited_2 = null;
				if ((fileName_tFileOutputDelimited_2.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") < fileName_tFileOutputDelimited_2
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
							fileName_tFileOutputDelimited_2.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					}
					directory_tFileOutputDelimited_2 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_2 = true;
				java.io.File filetFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);
				int nb_line_tFileOutputDelimited_2 = 0;
				int splitedFileNo_tFileOutputDelimited_2 = 0;
				int currentRow_tFileOutputDelimited_2 = 0;

				final String OUT_DELIM_tFileOutputDelimited_2 = /** Start field tFileOutputDelimited_2:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_2:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_2 = /**
																		 * Start field
																		 * tFileOutputDelimited_2:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_2:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_2 != null && directory_tFileOutputDelimited_2.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_2 = new java.io.File(directory_tFileOutputDelimited_2);
					if (!dir_tFileOutputDelimited_2.exists()) {
						dir_tFileOutputDelimited_2.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_2 = null;

				java.io.File fileToDelete_tFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				if (fileToDelete_tFileOutputDelimited_2.exists()) {
					fileToDelete_tFileOutputDelimited_2.delete();
				}
				outtFileOutputDelimited_2 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_2, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_2.length() == 0) {
					outtFileOutputDelimited_2.write("idmarche");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("natureobjetmarche");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("objetmarche");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("datenotification");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("lieuexecutionnom");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("libelleregionexecution");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("nature");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("codecpv");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("codecpv_original");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("codecpv_division");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("referencecpv");
					outtFileOutputDelimited_2.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_2", outtFileOutputDelimited_2);
				resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

				/**
				 * [tFileOutputDelimited_2 begin ] stop
				 */

				/**
				 * [tMap_3 begin ] start
				 */

				ok_Hash.put("tMap_3", false);
				start_Hash.put("tMap_3", System.currentTimeMillis());

				currentComponent = "tMap_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "dim_marche");
				}

				int tos_count_tMap_3 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = null;

				row3Struct row3HashKey = new row3Struct();
				row3Struct row3Default = new row3Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_3__Struct {
				}
				Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
				marcheStruct marche_tmp = new marcheStruct();
				doublon_marcheStruct doublon_marche_tmp = new doublon_marcheStruct();
// ###############################

				/**
				 * [tMap_3 begin ] stop
				 */

				/**
				 * [tDBCommit_3 begin ] start
				 */

				ok_Hash.put("tDBCommit_3", false);
				start_Hash.put("tDBCommit_3", System.currentTimeMillis());

				currentComponent = "tDBCommit_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
				}

				int tos_count_tDBCommit_3 = 0;

				/**
				 * [tDBCommit_3 begin ] stop
				 */

				/**
				 * [tDBOutput_3 begin ] start
				 */

				ok_Hash.put("tDBOutput_3", false);
				start_Hash.put("tDBOutput_3", System.currentTimeMillis());

				currentComponent = "tDBOutput_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "etablissement");
				}

				int tos_count_tDBOutput_3 = 0;

				String dbschema_tDBOutput_3 = null;
				dbschema_tDBOutput_3 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_3 = null;
				if (dbschema_tDBOutput_3 == null || dbschema_tDBOutput_3.trim().length() == 0) {
					tableName_tDBOutput_3 = ("dim_etablissement");
				} else {
					tableName_tDBOutput_3 = dbschema_tDBOutput_3 + "\".\"" + ("dim_etablissement");
				}

				int updateKeyCount_tDBOutput_3 = 1;
				if (updateKeyCount_tDBOutput_3 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_3 == 11 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_3 = 0;
				int nb_line_update_tDBOutput_3 = 0;
				int nb_line_inserted_tDBOutput_3 = 0;
				int nb_line_deleted_tDBOutput_3 = 0;
				int nb_line_rejected_tDBOutput_3 = 0;

				int deletedCount_tDBOutput_3 = 0;
				int updatedCount_tDBOutput_3 = 0;
				int insertedCount_tDBOutput_3 = 0;
				int rowsToCommitCount_tDBOutput_3 = 0;
				int rejectedCount_tDBOutput_3 = 0;

				boolean whetherReject_tDBOutput_3 = false;

				java.sql.Connection conn_tDBOutput_3 = null;
				String dbUser_tDBOutput_3 = null;

				conn_tDBOutput_3 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				int count_tDBOutput_3 = 0;
				String update_tDBOutput_3 = "UPDATE \"" + tableName_tDBOutput_3
						+ "\" SET \"siretetablissement\" = ?,\"siretetablissementvalide\" = ?,\"categorieetablissement\" = ?,\"denominationsocialeetablissement\" = ?,\"codecommuneetablissement\" = ?,\"communeetablissement\" = ?,\"departementetablissement\" = ?,\"libelledepartementetablissement\" = ?,\"coderegionetablissement\" = ?,\"libelleregionetablissement\" = ? WHERE \"sirenetablissement\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_3 = conn_tDBOutput_3
						.prepareStatement(update_tDBOutput_3);
				resourceMap.put("pstmtUpdate_tDBOutput_3", pstmtUpdate_tDBOutput_3);
				String insert_tDBOutput_3 = "INSERT INTO \"" + tableName_tDBOutput_3
						+ "\" (\"sirenetablissement\",\"siretetablissement\",\"siretetablissementvalide\",\"categorieetablissement\",\"denominationsocialeetablissement\",\"codecommuneetablissement\",\"communeetablissement\",\"departementetablissement\",\"libelledepartementetablissement\",\"coderegionetablissement\",\"libelleregionetablissement\") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_3 = conn_tDBOutput_3
						.prepareStatement(insert_tDBOutput_3);
				resourceMap.put("pstmtInsert_tDBOutput_3", pstmtInsert_tDBOutput_3);

				/**
				 * [tDBOutput_3 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_3", false);
				start_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "doublon_etablissement");
				}

				int tos_count_tFileOutputDelimited_3 = 0;

				String fileName_tFileOutputDelimited_3 = "";
				fileName_tFileOutputDelimited_3 = (new java.io.File(
						context.logs_path + "/insert_doublons/doublons_dim_etablissement.csv")).getAbsolutePath()
								.replace("\\", "/");
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

				java.io.File fileToDelete_tFileOutputDelimited_3 = new java.io.File(fileName_tFileOutputDelimited_3);
				if (fileToDelete_tFileOutputDelimited_3.exists()) {
					fileToDelete_tFileOutputDelimited_3.delete();
				}
				outtFileOutputDelimited_3 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_3, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_3.length() == 0) {
					outtFileOutputDelimited_3.write("sirenetablissement");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("siretetablissement");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("siretetablissementvalide");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("categorieetablissement");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("denominationsocialeetablissement");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("codecommuneetablissement");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("communeetablissement");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("departementetablissement");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("libelledepartementetablissement");
					outtFileOutputDelimited_3.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_3", outtFileOutputDelimited_3);
				resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);

				/**
				 * [tFileOutputDelimited_3 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_5 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_5", false);
				start_Hash.put("tFileOutputDelimited_5", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "siren_null");
				}

				int tos_count_tFileOutputDelimited_5 = 0;

				String fileName_tFileOutputDelimited_5 = "";
				fileName_tFileOutputDelimited_5 = (new java.io.File(
						context.logs_path + "/insert_null/null_dim_etablissement.csv")).getAbsolutePath().replace("\\",
								"/");
				String fullName_tFileOutputDelimited_5 = null;
				String extension_tFileOutputDelimited_5 = null;
				String directory_tFileOutputDelimited_5 = null;
				if ((fileName_tFileOutputDelimited_5.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_5.lastIndexOf(".") < fileName_tFileOutputDelimited_5
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5;
						extension_tFileOutputDelimited_5 = "";
					} else {
						fullName_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5.substring(0,
								fileName_tFileOutputDelimited_5.lastIndexOf("."));
						extension_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5
								.substring(fileName_tFileOutputDelimited_5.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5.substring(0,
							fileName_tFileOutputDelimited_5.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_5.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5.substring(0,
								fileName_tFileOutputDelimited_5.lastIndexOf("."));
						extension_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5
								.substring(fileName_tFileOutputDelimited_5.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5;
						extension_tFileOutputDelimited_5 = "";
					}
					directory_tFileOutputDelimited_5 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_5 = true;
				java.io.File filetFileOutputDelimited_5 = new java.io.File(fileName_tFileOutputDelimited_5);
				globalMap.put("tFileOutputDelimited_5_FILE_NAME", fileName_tFileOutputDelimited_5);
				int nb_line_tFileOutputDelimited_5 = 0;
				int splitedFileNo_tFileOutputDelimited_5 = 0;
				int currentRow_tFileOutputDelimited_5 = 0;

				final String OUT_DELIM_tFileOutputDelimited_5 = /** Start field tFileOutputDelimited_5:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_5:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_5 = /**
																		 * Start field
																		 * tFileOutputDelimited_5:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_5:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_5 != null && directory_tFileOutputDelimited_5.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_5 = new java.io.File(directory_tFileOutputDelimited_5);
					if (!dir_tFileOutputDelimited_5.exists()) {
						dir_tFileOutputDelimited_5.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_5 = null;

				java.io.File fileToDelete_tFileOutputDelimited_5 = new java.io.File(fileName_tFileOutputDelimited_5);
				if (fileToDelete_tFileOutputDelimited_5.exists()) {
					fileToDelete_tFileOutputDelimited_5.delete();
				}
				outtFileOutputDelimited_5 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_5, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_5.length() == 0) {
					outtFileOutputDelimited_5.write("sirenetablissement");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("siretetablissement");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("siretetablissementvalide");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("categorieetablissement");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("denominationsocialeetablissement");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("codecommuneetablissement");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("communeetablissement");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("departementetablissement");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("libelledepartementetablissement");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("coderegionetablissement");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("libelleregionetablissement");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("recordid");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("idmarche");
					outtFileOutputDelimited_5.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_5", outtFileOutputDelimited_5);
				resourceMap.put("nb_line_tFileOutputDelimited_5", nb_line_tFileOutputDelimited_5);

				/**
				 * [tFileOutputDelimited_5 begin ] stop
				 */

				/**
				 * [tMap_4 begin ] start
				 */

				ok_Hash.put("tMap_4", false);
				start_Hash.put("tMap_4", System.currentTimeMillis());

				currentComponent = "tMap_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "dim_etablissement");
				}

				int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 = null;

				row7Struct row7HashKey = new row7Struct();
				row7Struct row7Default = new row7Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
					boolean isNotNull;
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				etablissementStruct etablissement_tmp = new etablissementStruct();
				doublon_etablissementStruct doublon_etablissement_tmp = new doublon_etablissementStruct();
				siren_nullStruct siren_null_tmp = new siren_nullStruct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				dim_acheteurStruct dim_acheteur_tmp = new dim_acheteurStruct();
				dim_marcheStruct dim_marche_tmp = new dim_marcheStruct();
				dim_etablissementStruct dim_etablissement_tmp = new dim_etablissementStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				int tos_count_tDBInput_1 = 0;

				int nb_line_tDBInput_1 = 0;
				java.sql.Connection conn_tDBInput_1 = null;
				conn_tDBInput_1 = (java.sql.Connection) globalMap.get("conn_tDBConnection_2");

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "select * FROM stg.marche";

				globalMap.put("tDBInput_1_QUERY", dbquery_tDBInput_1);
				java.sql.ResultSet rs_tDBInput_1 = null;

				try {
					rs_tDBInput_1 = stmt_tDBInput_1.executeQuery(dbquery_tDBInput_1);
					java.sql.ResultSetMetaData rsmd_tDBInput_1 = rs_tDBInput_1.getMetaData();
					int colQtyInRs_tDBInput_1 = rsmd_tDBInput_1.getColumnCount();

					String tmpContent_tDBInput_1 = null;

					while (rs_tDBInput_1.next()) {
						nb_line_tDBInput_1++;

						if (colQtyInRs_tDBInput_1 < 1) {
							row1.recordid = null;
						} else {

							row1.recordid = routines.system.JDBCUtil.getString(rs_tDBInput_1, 1, false);
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row1.sirenetablissementvalide = null;
						} else {

							row1.sirenetablissementvalide = routines.system.JDBCUtil.getString(rs_tDBInput_1, 2, false);
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							row1.denominationsocialeetablissement = null;
						} else {

							row1.denominationsocialeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 3,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 4) {
							row1.libellecommuneacheteur = null;
						} else {

							row1.libellecommuneacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 4, false);
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							row1.lieuexecutioncode = null;
						} else {

							row1.lieuexecutioncode = routines.system.JDBCUtil.getString(rs_tDBInput_1, 5, false);
						}
						if (colQtyInRs_tDBInput_1 < 6) {
							row1.adresseetablissement = null;
						} else {

							row1.adresseetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 6, false);
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							row1.codepostalacheteur = null;
						} else {

							row1.codepostalacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 7, false);
						}
						if (colQtyInRs_tDBInput_1 < 8) {
							row1.libelleregionacheteur = null;
						} else {

							row1.libelleregionacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 8, false);
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							row1.departementacheteur = null;
						} else {

							row1.departementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 9, false);
						}
						if (colQtyInRs_tDBInput_1 < 10) {
							row1.idmarche = null;
						} else {

							row1.idmarche = rs_tDBInput_1.getInt(10);
							if (rs_tDBInput_1.wasNull()) {
								row1.idmarche = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							row1.dureemois = null;
						} else {

							row1.dureemois = rs_tDBInput_1.getInt(11);
							if (rs_tDBInput_1.wasNull()) {
								row1.dureemois = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 12) {
							row1.datenotification = null;
						} else {

							row1.datenotification = routines.system.JDBCUtil.getString(rs_tDBInput_1, 12, false);
						}
						if (colQtyInRs_tDBInput_1 < 13) {
							row1.montantcalcule = null;
						} else {

							row1.montantcalcule = rs_tDBInput_1.getFloat(13);
							if (rs_tDBInput_1.wasNull()) {
								row1.montantcalcule = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 14) {
							row1.libelledepartementacheteur = null;
						} else {

							row1.libelledepartementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 14,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 15) {
							row1.codecpv_division = null;
						} else {

							row1.codecpv_division = rs_tDBInput_1.getInt(15);
							if (rs_tDBInput_1.wasNull()) {
								row1.codecpv_division = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 16) {
							row1.lieuexecutionnom = null;
						} else {

							row1.lieuexecutionnom = routines.system.JDBCUtil.getString(rs_tDBInput_1, 16, false);
						}
						if (colQtyInRs_tDBInput_1 < 17) {
							row1.libellearrondissementacheteur = null;
						} else {

							row1.libellearrondissementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 17,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 18) {
							row1.populationcommuneetablissement = null;
						} else {

							row1.populationcommuneetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 18,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 19) {
							row1.codecommuneacheteur = null;
						} else {

							row1.codecommuneacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 19, false);
						}
						if (colQtyInRs_tDBInput_1 < 20) {
							row1.superficiecommuneetablissement = null;
						} else {

							row1.superficiecommuneetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 20,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 21) {
							row1.nicetablissement = null;
						} else {

							row1.nicetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 21, false);
						}
						if (colQtyInRs_tDBInput_1 < 22) {
							row1.geoloccommuneetablissement___ = null;
						} else {

							row1.geoloccommuneetablissement___ = routines.system.JDBCUtil.getString(rs_tDBInput_1, 22,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 23) {
							row1.objetmarche = null;
						} else {

							row1.objetmarche = routines.system.JDBCUtil.getString(rs_tDBInput_1, 23, false);
						}
						if (colQtyInRs_tDBInput_1 < 24) {
							row1.nature = null;
						} else {

							row1.nature = routines.system.JDBCUtil.getString(rs_tDBInput_1, 24, false);
						}
						if (colQtyInRs_tDBInput_1 < 25) {
							row1.nombretitulairesurmarchepresume = null;
						} else {

							row1.nombretitulairesurmarchepresume = routines.system.JDBCUtil.getString(rs_tDBInput_1, 25,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 26) {
							row1.libelleregionetablissement = null;
						} else {

							row1.libelleregionetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 26,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 27) {
							row1.id = null;
						} else {

							row1.id = routines.system.JDBCUtil.getString(rs_tDBInput_1, 27, false);
						}
						if (colQtyInRs_tDBInput_1 < 28) {
							row1.source = null;
						} else {

							row1.source = routines.system.JDBCUtil.getString(rs_tDBInput_1, 28, false);
						}
						if (colQtyInRs_tDBInput_1 < 29) {
							row1.dureemoisestimee = null;
						} else {

							row1.dureemoisestimee = routines.system.JDBCUtil.getString(rs_tDBInput_1, 29, false);
						}
						if (colQtyInRs_tDBInput_1 < 30) {
							row1.nomacheteur = null;
						} else {

							row1.nomacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 30, false);
						}
						if (colQtyInRs_tDBInput_1 < 31) {
							row1.referencecpv = null;
						} else {

							row1.referencecpv = routines.system.JDBCUtil.getString(rs_tDBInput_1, 31, false);
						}
						if (colQtyInRs_tDBInput_1 < 32) {
							row1.type = null;
						} else {

							row1.type = routines.system.JDBCUtil.getString(rs_tDBInput_1, 32, false);
						}
						if (colQtyInRs_tDBInput_1 < 33) {
							row1.sirenetablissement = null;
						} else {

							row1.sirenetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 33, false);
						}
						if (colQtyInRs_tDBInput_1 < 34) {
							row1.superficiecommuneacheteur = null;
						} else {

							row1.superficiecommuneacheteur = rs_tDBInput_1.getFloat(34);
							if (rs_tDBInput_1.wasNull()) {
								row1.superficiecommuneacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 35) {
							row1.codearrondissementacheteur = null;
						} else {

							row1.codearrondissementacheteur = rs_tDBInput_1.getInt(35);
							if (rs_tDBInput_1.wasNull()) {
								row1.codearrondissementacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 36) {
							row1.distanceacheteuretablissement = null;
						} else {

							row1.distanceacheteuretablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 36,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 37) {
							row1.communeetablissement = null;
						} else {

							row1.communeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 37, false);
						}
						if (colQtyInRs_tDBInput_1 < 38) {
							row1.montant = null;
						} else {

							row1.montant = routines.system.JDBCUtil.getString(rs_tDBInput_1, 38, false);
						}
						if (colQtyInRs_tDBInput_1 < 39) {
							row1.departementetablissement = null;
						} else {

							row1.departementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 39,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 40) {
							row1.datepublicationdonnees = null;
						} else {

							row1.datepublicationdonnees = routines.system.JDBCUtil.getString(rs_tDBInput_1, 40, false);
						}
						if (colQtyInRs_tDBInput_1 < 41) {
							row1.sirenacheteurvalide = null;
						} else {

							row1.sirenacheteurvalide = routines.system.JDBCUtil.getString(rs_tDBInput_1, 41, false);
						}
						if (colQtyInRs_tDBInput_1 < 42) {
							row1.moisnotification = null;
						} else {

							row1.moisnotification = routines.system.JDBCUtil.getString(rs_tDBInput_1, 42, false);
						}
						if (colQtyInRs_tDBInput_1 < 43) {
							row1.coderegionetablissement = null;
						} else {

							row1.coderegionetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 43, false);
						}
						if (colQtyInRs_tDBInput_1 < 44) {
							row1.formeprix = null;
						} else {

							row1.formeprix = routines.system.JDBCUtil.getString(rs_tDBInput_1, 44, false);
						}
						if (colQtyInRs_tDBInput_1 < 45) {
							row1.siretetablissementvalide = null;
						} else {

							row1.siretetablissementvalide = routines.system.JDBCUtil.getString(rs_tDBInput_1, 45,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 46) {
							row1.codedepartementexecution = null;
						} else {

							row1.codedepartementexecution = rs_tDBInput_1.getFloat(46);
							if (rs_tDBInput_1.wasNull()) {
								row1.codedepartementexecution = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 47) {
							row1.dist = null;
						} else {

							row1.dist = rs_tDBInput_1.getFloat(47);
							if (rs_tDBInput_1.wasNull()) {
								row1.dist = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 48) {
							row1.coderegionacheteur = null;
						} else {

							row1.coderegionacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 48, false);
						}
						if (colQtyInRs_tDBInput_1 < 49) {
							row1.lieuexecutiontypecode = null;
						} else {

							row1.lieuexecutiontypecode = routines.system.JDBCUtil.getString(rs_tDBInput_1, 49, false);
						}
						if (colQtyInRs_tDBInput_1 < 50) {
							row1.codecommuneetablissement = null;
						} else {

							row1.codecommuneetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 50,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 51) {
							row1.populationcommuneacheteur = null;
						} else {

							row1.populationcommuneacheteur = rs_tDBInput_1.getFloat(51);
							if (rs_tDBInput_1.wasNull()) {
								row1.populationcommuneacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 52) {
							row1.idacheteur = null;
						} else {

							row1.idacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_1, 52, false);
						}
						if (colQtyInRs_tDBInput_1 < 53) {
							row1.codecpv_original = null;
						} else {

							row1.codecpv_original = routines.system.JDBCUtil.getString(rs_tDBInput_1, 53, false);
						}
						if (colQtyInRs_tDBInput_1 < 54) {
							row1.siretetablissement = null;
						} else {

							row1.siretetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 54, false);
						}
						if (colQtyInRs_tDBInput_1 < 55) {
							row1.libelledepartementetablissement = null;
						} else {

							row1.libelledepartementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 55,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 56) {
							row1.typeidentifiantetablissement = null;
						} else {

							row1.typeidentifiantetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 56,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 57) {
							row1.categorieetablissement = null;
						} else {

							row1.categorieetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 57, false);
						}
						if (colQtyInRs_tDBInput_1 < 58) {
							row1.coderegionexecution = null;
						} else {

							row1.coderegionexecution = rs_tDBInput_1.getFloat(58);
							if (rs_tDBInput_1.wasNull()) {
								row1.coderegionexecution = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 59) {
							row1.codepostaletablissement = null;
						} else {

							row1.codepostaletablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 59, false);
						}
						if (colQtyInRs_tDBInput_1 < 60) {
							row1.natureobjetmarche = null;
						} else {

							row1.natureobjetmarche = routines.system.JDBCUtil.getString(rs_tDBInput_1, 60, false);
						}
						if (colQtyInRs_tDBInput_1 < 61) {
							row1.dureemoiscalculee = null;
						} else {

							row1.dureemoiscalculee = rs_tDBInput_1.getFloat(61);
							if (rs_tDBInput_1.wasNull()) {
								row1.dureemoiscalculee = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 62) {
							row1.codetypeetablissement = null;
						} else {

							row1.codetypeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 62, false);
						}
						if (colQtyInRs_tDBInput_1 < 63) {
							row1.geoloccommuneacheteur___ = null;
						} else {

							row1.geoloccommuneacheteur___ = routines.system.JDBCUtil.getString(rs_tDBInput_1, 63,
									false);
						}
						if (colQtyInRs_tDBInput_1 < 64) {
							row1.codecpv = null;
						} else {

							row1.codecpv = routines.system.JDBCUtil.getString(rs_tDBInput_1, 64, false);
						}
						if (colQtyInRs_tDBInput_1 < 65) {
							row1.anneenotification = null;
						} else {

							row1.anneenotification = routines.system.JDBCUtil.getString(rs_tDBInput_1, 65, false);
						}
						if (colQtyInRs_tDBInput_1 < 66) {
							row1.libelleregionexecution = null;
						} else {

							row1.libelleregionexecution = routines.system.JDBCUtil.getString(rs_tDBInput_1, 66, false);
						}
						if (colQtyInRs_tDBInput_1 < 67) {
							row1.procedure = null;
						} else {

							row1.procedure = routines.system.JDBCUtil.getString(rs_tDBInput_1, 67, false);
						}
						if (colQtyInRs_tDBInput_1 < 68) {
							row1.libellearrondissementetablissement = null;
						} else {

							row1.libellearrondissementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1,
									68, false);
						}
						if (colQtyInRs_tDBInput_1 < 69) {
							row1.codearrondissementetablissement = null;
						} else {

							row1.codearrondissementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_1, 69,
									false);
						}

						/**
						 * [tDBInput_1 begin ] stop
						 */

						/**
						 * [tDBInput_1 main ] start
						 */

						currentComponent = "tDBInput_1";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tMap_1 main ] start
						 */

						currentComponent = "tMap_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row1"

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

							Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
							// ###############################
							// # Output tables

							dim_acheteur = null;
							dim_marche = null;
							dim_etablissement = null;

// # Output table : 'dim_acheteur'
							dim_acheteur_tmp.idacheteur = row1.idacheteur;
							dim_acheteur_tmp.nomacheteur = row1.nomacheteur;
							dim_acheteur_tmp.codepostaleacheteur = row1.codepostalacheteur;
							dim_acheteur_tmp.codepostalecommune = row1.codecommuneacheteur;
							dim_acheteur_tmp.nomcommune = row1.libellecommuneacheteur;
							dim_acheteur_tmp.codedepartement = row1.departementacheteur;
							dim_acheteur_tmp.libelledepartement = row1.libelledepartementacheteur;
							dim_acheteur_tmp.coderegion = row1.coderegionacheteur;
							dim_acheteur_tmp.libelleregion = row1.libelleregionacheteur;
							dim_acheteur = dim_acheteur_tmp;

// # Output table : 'dim_marche'
							dim_marche_tmp.idmarche = row1.idmarche;
							dim_marche_tmp.natureobjetmarche = row1.natureobjetmarche;
							dim_marche_tmp.objetmarche = row1.objetmarche;
							dim_marche_tmp.datenotification = row1.datenotification;
							dim_marche_tmp.dureemois = row1.dureemois;
							dim_marche_tmp.lieuexecutionnom = row1.lieuexecutionnom;
							dim_marche_tmp.formeprix = row1.formeprix;
							dim_marche_tmp.lieuexecutiontypecode = row1.lieuexecutiontypecode;
							dim_marche_tmp.codedepartementexecution = row1.codedepartementexecution;
							dim_marche_tmp.coderegionexecution = row1.coderegionexecution;
							dim_marche_tmp.libelleregionexecution = row1.libelleregionexecution;
							dim_marche_tmp.nature = row1.nature;
							dim_marche_tmp.procedure = row1.procedure;
							dim_marche_tmp.codecpv = row1.codecpv;
							dim_marche_tmp.codecpv_original = row1.codecpv_original;
							dim_marche_tmp.codecpv_division = row1.codecpv_division;
							dim_marche_tmp.referencecpv = row1.referencecpv;
							dim_marche = dim_marche_tmp;

// # Output table : 'dim_etablissement'
							dim_etablissement_tmp.sirenetablissement = row1.sirenetablissement;
							dim_etablissement_tmp.siretetablissement = row1.siretetablissement;
							dim_etablissement_tmp.siretetablissementvalide = row1.sirenetablissementvalide;
							dim_etablissement_tmp.categorieetablissement = row1.categorieetablissement;
							dim_etablissement_tmp.denominationsocialeetablissement = row1.denominationsocialeetablissement;
							dim_etablissement_tmp.codecommuneetablissement = row1.codecommuneetablissement;
							dim_etablissement_tmp.communeetablissement = row1.communeetablissement;
							dim_etablissement_tmp.departementetablissement = row1.communeetablissement;
							dim_etablissement_tmp.libelledepartementetablissement = row1.libelledepartementetablissement;
							dim_etablissement_tmp.coderegionetablissement = row1.coderegionetablissement;
							dim_etablissement_tmp.libelleregionetablissement = row1.libelleregionetablissement;
							dim_etablissement_tmp.recordid = row1.recordid;
							dim_etablissement_tmp.idmarche = null;
							dim_etablissement = dim_etablissement_tmp;
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
// Start of branch "dim_acheteur"
						if (dim_acheteur != null) {

							/**
							 * [tMap_2 main ] start
							 */

							currentComponent = "tMap_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "dim_acheteur"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_2 = false;
							boolean mainRowRejected_tMap_2 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row2"
							///////////////////////////////////////////////

							boolean forceLooprow2 = false;

							row2Struct row2ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_2 = false;

								row2HashKey.id = dim_acheteur.idacheteur;

								row2HashKey.hashCodeDirty = true;

								tDBInput_2Process(globalMap);

								tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) globalMap
										.get("tHash_Lookup_row2"));

								tHash_Lookup_row2.initGet();

								tHash_Lookup_row2.lookup(row2HashKey);

								if (!tHash_Lookup_row2.hasNext()) { // G_TM_M_090

									rejectedInnerJoin_tMap_2 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							if (tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2'
								// and it contains more one result from keys : row2.id = '" + row2HashKey.id +
								// "'");
							} // G 071

							row2Struct row2 = null;

							row2Struct fromLookup_row2 = null;
							row2 = row2Default;

							if (tHash_Lookup_row2 != null && tHash_Lookup_row2.hasNext()) { // G 099

								fromLookup_row2 = tHash_Lookup_row2.next();

							} // G 099

							if (fromLookup_row2 != null) {
								row2 = fromLookup_row2;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
								// ###############################
								// # Output tables

								acheteur = null;
								doublons_acheteur = null;

								boolean rejected_tMap_2 = true;
								if (!rejectedInnerJoin_tMap_2) {
								} // closing inner join bracket (1)
								else {
									rejected_tMap_2 = false;
								} // closing else inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'acheteur'
// # Filter conditions 
								if (rejectedInnerJoin_tMap_2) {
									acheteur_tmp.idacheteur = dim_acheteur.idacheteur;
									acheteur_tmp.nomacheteur = dim_acheteur.nomacheteur;
									acheteur_tmp.codepostaleacheteur = dim_acheteur.codepostaleacheteur;
									acheteur_tmp.codepostalecommune = dim_acheteur.codepostalecommune;
									acheteur_tmp.nomcommune = dim_acheteur.nomcommune;
									acheteur_tmp.codedepartement = dim_acheteur.codedepartement;
									acheteur_tmp.libelledepartement = dim_acheteur.libelledepartement;
									acheteur_tmp.coderegion = dim_acheteur.coderegion;
									acheteur_tmp.libelleregion = dim_acheteur.libelleregion;
									acheteur = acheteur_tmp;
								} // closing filter/reject

// # Output reject table : 'doublons_acheteur'
// # Filter conditions 
								if (rejected_tMap_2) {
									doublons_acheteur_tmp.idacheteur = dim_acheteur.idacheteur;
									doublons_acheteur_tmp.nomacheteur = dim_acheteur.nomacheteur;
									doublons_acheteur_tmp.codepostaleacheteur = dim_acheteur.codepostaleacheteur;
									doublons_acheteur = doublons_acheteur_tmp;
								} // closing filter/reject
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_2 = false;

							tos_count_tMap_2++;

							/**
							 * [tMap_2 main ] stop
							 */

							/**
							 * [tMap_2 process_data_begin ] start
							 */

							currentComponent = "tMap_2";

							/**
							 * [tMap_2 process_data_begin ] stop
							 */
// Start of branch "acheteur"
							if (acheteur != null) {

								/**
								 * [tDBOutput_1 main ] start
								 */

								currentComponent = "tDBOutput_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "acheteur"

									);
								}

								row4 = null;
								whetherReject_tDBOutput_1 = false;
								int updateFlag_tDBOutput_1 = 0;
								if (acheteur.nomacheteur == null) {
									pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(1, acheteur.nomacheteur);
								}

								if (acheteur.codepostaleacheteur == null) {
									pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(2, acheteur.codepostaleacheteur);
								}

								if (acheteur.codepostalecommune == null) {
									pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(3, acheteur.codepostalecommune);
								}

								if (acheteur.nomcommune == null) {
									pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(4, acheteur.nomcommune);
								}

								if (acheteur.codedepartement == null) {
									pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(5, acheteur.codedepartement);
								}

								if (acheteur.libelledepartement == null) {
									pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(6, acheteur.libelledepartement);
								}

								if (acheteur.coderegion == null) {
									pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(7, acheteur.coderegion);
								}

								if (acheteur.libelleregion == null) {
									pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(8, acheteur.libelleregion);
								}

								if (acheteur.idacheteur == null) {
									pstmtUpdate_tDBOutput_1.setNull(9 + count_tDBOutput_1, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_1.setString(9 + count_tDBOutput_1, acheteur.idacheteur);
								}

								try {

									updateFlag_tDBOutput_1 = pstmtUpdate_tDBOutput_1.executeUpdate();
									updatedCount_tDBOutput_1 = updatedCount_tDBOutput_1 + updateFlag_tDBOutput_1;
									rowsToCommitCount_tDBOutput_1 += updateFlag_tDBOutput_1;

									if (updateFlag_tDBOutput_1 == 0) {

										if (acheteur.idacheteur == null) {
											pstmtInsert_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_1.setString(1, acheteur.idacheteur);
										}

										if (acheteur.nomacheteur == null) {
											pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_1.setString(2, acheteur.nomacheteur);
										}

										if (acheteur.codepostaleacheteur == null) {
											pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_1.setString(3, acheteur.codepostaleacheteur);
										}

										if (acheteur.codepostalecommune == null) {
											pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_1.setString(4, acheteur.codepostalecommune);
										}

										if (acheteur.nomcommune == null) {
											pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_1.setString(5, acheteur.nomcommune);
										}

										if (acheteur.codedepartement == null) {
											pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_1.setString(6, acheteur.codedepartement);
										}

										if (acheteur.libelledepartement == null) {
											pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_1.setString(7, acheteur.libelledepartement);
										}

										if (acheteur.coderegion == null) {
											pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_1.setString(8, acheteur.coderegion);
										}

										if (acheteur.libelleregion == null) {
											pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_1.setString(9, acheteur.libelleregion);
										}

										int processedCount_tDBOutput_1 = pstmtInsert_tDBOutput_1.executeUpdate();
										insertedCount_tDBOutput_1 += processedCount_tDBOutput_1;
										rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
										nb_line_tDBOutput_1++;

									} else {
										nb_line_tDBOutput_1++;

									}
								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_1 = true;
									nb_line_tDBOutput_1++;
									System.err.print(e.getMessage());
								}
								if (!whetherReject_tDBOutput_1) {
									row4 = new row4Struct();
									row4.idacheteur = acheteur.idacheteur;
									row4.nomacheteur = acheteur.nomacheteur;
									row4.codepostaleacheteur = acheteur.codepostaleacheteur;
									row4.codepostalecommune = acheteur.codepostalecommune;
									row4.nomcommune = acheteur.nomcommune;
									row4.codedepartement = acheteur.codedepartement;
									row4.libelledepartement = acheteur.libelledepartement;
									row4.coderegion = acheteur.coderegion;
									row4.libelleregion = acheteur.libelleregion;
								}

								tos_count_tDBOutput_1++;

								/**
								 * [tDBOutput_1 main ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_begin ] stop
								 */
// Start of branch "row4"
								if (row4 != null) {

									/**
									 * [tDBCommit_1 main ] start
									 */

									currentComponent = "tDBCommit_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row4"

										);
									}

									java.sql.Connection conn_tDBCommit_1 = (java.sql.Connection) globalMap
											.get("conn_tDBConnection_1");
									if (conn_tDBCommit_1 != null && !conn_tDBCommit_1.isClosed()) {

										conn_tDBCommit_1.commit();

									}

									tos_count_tDBCommit_1++;

									/**
									 * [tDBCommit_1 main ] stop
									 */

									/**
									 * [tDBCommit_1 process_data_begin ] start
									 */

									currentComponent = "tDBCommit_1";

									/**
									 * [tDBCommit_1 process_data_begin ] stop
									 */

									/**
									 * [tDBCommit_1 process_data_end ] start
									 */

									currentComponent = "tDBCommit_1";

									/**
									 * [tDBCommit_1 process_data_end ] stop
									 */

								} // End of branch "row4"

								/**
								 * [tDBOutput_1 process_data_end ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_end ] stop
								 */

							} // End of branch "acheteur"

// Start of branch "doublons_acheteur"
							if (doublons_acheteur != null) {

								/**
								 * [tFileOutputDelimited_1 main ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "doublons_acheteur"

									);
								}

								StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
								if (doublons_acheteur.idacheteur != null) {
									sb_tFileOutputDelimited_1.append(doublons_acheteur.idacheteur);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (doublons_acheteur.nomacheteur != null) {
									sb_tFileOutputDelimited_1.append(doublons_acheteur.nomacheteur);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (doublons_acheteur.codepostaleacheteur != null) {
									sb_tFileOutputDelimited_1.append(doublons_acheteur.codepostaleacheteur);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

								nb_line_tFileOutputDelimited_1++;
								resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

								outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());

								tos_count_tFileOutputDelimited_1++;

								/**
								 * [tFileOutputDelimited_1 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_end ] stop
								 */

							} // End of branch "doublons_acheteur"

							/**
							 * [tMap_2 process_data_end ] start
							 */

							currentComponent = "tMap_2";

							/**
							 * [tMap_2 process_data_end ] stop
							 */

						} // End of branch "dim_acheteur"

// Start of branch "dim_marche"
						if (dim_marche != null) {

							/**
							 * [tMap_3 main ] start
							 */

							currentComponent = "tMap_3";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "dim_marche"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_3 = false;
							boolean mainRowRejected_tMap_3 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row3"
							///////////////////////////////////////////////

							boolean forceLooprow3 = false;

							row3Struct row3ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_3) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_3 = false;

								row3HashKey.id = dim_marche.idmarche;

								row3HashKey.hashCodeDirty = true;

								tDBInput_3Process(globalMap);

								tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) globalMap
										.get("tHash_Lookup_row3"));

								tHash_Lookup_row3.initGet();

								tHash_Lookup_row3.lookup(row3HashKey);

								if (!tHash_Lookup_row3.hasNext()) { // G_TM_M_090

									rejectedInnerJoin_tMap_3 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							if (tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3'
								// and it contains more one result from keys : row3.id = '" + row3HashKey.id +
								// "'");
							} // G 071

							row3Struct row3 = null;

							row3Struct fromLookup_row3 = null;
							row3 = row3Default;

							if (tHash_Lookup_row3 != null && tHash_Lookup_row3.hasNext()) { // G 099

								fromLookup_row3 = tHash_Lookup_row3.next();

							} // G 099

							if (fromLookup_row3 != null) {
								row3 = fromLookup_row3;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
								// ###############################
								// # Output tables

								marche = null;
								doublon_marche = null;

								boolean rejected_tMap_3 = true;
								if (!rejectedInnerJoin_tMap_3) {
								} // closing inner join bracket (1)
								else {
									rejected_tMap_3 = false;
								} // closing else inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'marche'
// # Filter conditions 
								if (rejectedInnerJoin_tMap_3) {
									marche_tmp.idmarche = dim_marche.idmarche;
									marche_tmp.natureobjetmarche = dim_marche.natureobjetmarche;
									marche_tmp.objetmarche = dim_marche.objetmarche;
									marche_tmp.datenotification = dim_marche.datenotification;
									marche_tmp.dureemois = dim_marche.dureemois;
									marche_tmp.lieuexecutionnom = dim_marche.lieuexecutionnom;
									marche_tmp.formeprix = dim_marche.formeprix;
									marche_tmp.lieuexecutiontypecode = dim_marche.lieuexecutiontypecode;
									marche_tmp.codedepartementexecution = dim_marche.codedepartementexecution;
									marche_tmp.coderegionexecution = dim_marche.coderegionexecution;
									marche_tmp.libelleregionexecution = dim_marche.libelleregionexecution;
									marche_tmp.nature = dim_marche.nature;
									marche_tmp.procedure = dim_marche.procedure;
									marche_tmp.codecpv = dim_marche.codecpv;
									marche_tmp.codecpv_original = dim_marche.codecpv_original;
									marche_tmp.codecpv_division = dim_marche.codecpv_division;
									marche_tmp.referencecpv = dim_marche.referencecpv;
									marche = marche_tmp;
								} // closing filter/reject

// # Output reject table : 'doublon_marche'
// # Filter conditions 
								if (rejected_tMap_3) {
									doublon_marche_tmp.idmarche = dim_marche.idmarche;
									doublon_marche_tmp.natureobjetmarche = dim_marche.natureobjetmarche;
									doublon_marche_tmp.objetmarche = dim_marche.objetmarche;
									doublon_marche_tmp.datenotification = dim_marche.datenotification;
									doublon_marche_tmp.lieuexecutionnom = dim_marche.lieuexecutionnom;
									doublon_marche_tmp.libelleregionexecution = dim_marche.libelleregionexecution;
									doublon_marche_tmp.nature = dim_marche.nature;
									doublon_marche_tmp.codecpv = dim_marche.codecpv;
									doublon_marche_tmp.codecpv_original = dim_marche.codecpv_original;
									doublon_marche_tmp.codecpv_division = dim_marche.codecpv_division;
									doublon_marche_tmp.referencecpv = dim_marche.referencecpv;
									doublon_marche = doublon_marche_tmp;
								} // closing filter/reject
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
// Start of branch "marche"
							if (marche != null) {

								/**
								 * [tDBOutput_2 main ] start
								 */

								currentComponent = "tDBOutput_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "marche"

									);
								}

								row5 = null;
								whetherReject_tDBOutput_2 = false;
								int updateFlag_tDBOutput_2 = 0;
								if (marche.natureobjetmarche == null) {
									pstmtUpdate_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(1, marche.natureobjetmarche);
								}

								if (marche.objetmarche == null) {
									pstmtUpdate_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(2, marche.objetmarche);
								}

								if (marche.datenotification == null) {
									pstmtUpdate_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(3, marche.datenotification);
								}

								if (marche.dureemois == null) {
									pstmtUpdate_tDBOutput_2.setNull(4, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(4, marche.dureemois);
								}

								if (marche.lieuexecutionnom == null) {
									pstmtUpdate_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(5, marche.lieuexecutionnom);
								}

								if (marche.formeprix == null) {
									pstmtUpdate_tDBOutput_2.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(6, marche.formeprix);
								}

								if (marche.lieuexecutiontypecode == null) {
									pstmtUpdate_tDBOutput_2.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(7, marche.lieuexecutiontypecode);
								}

								if (marche.codedepartementexecution == null) {
									pstmtUpdate_tDBOutput_2.setNull(8, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(8, marche.codedepartementexecution);
								}

								if (marche.coderegionexecution == null) {
									pstmtUpdate_tDBOutput_2.setNull(9, java.sql.Types.FLOAT);
								} else {
									pstmtUpdate_tDBOutput_2.setFloat(9, marche.coderegionexecution);
								}

								if (marche.libelleregionexecution == null) {
									pstmtUpdate_tDBOutput_2.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(10, marche.libelleregionexecution);
								}

								if (marche.nature == null) {
									pstmtUpdate_tDBOutput_2.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(11, marche.nature);
								}

								if (marche.procedure == null) {
									pstmtUpdate_tDBOutput_2.setNull(12, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(12, marche.procedure);
								}

								if (marche.codecpv == null) {
									pstmtUpdate_tDBOutput_2.setNull(13, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(13, marche.codecpv);
								}

								if (marche.codecpv_original == null) {
									pstmtUpdate_tDBOutput_2.setNull(14, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(14, marche.codecpv_original);
								}

								if (marche.codecpv_division == null) {
									pstmtUpdate_tDBOutput_2.setNull(15, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(15, marche.codecpv_division);
								}

								if (marche.referencecpv == null) {
									pstmtUpdate_tDBOutput_2.setNull(16, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(16, marche.referencecpv);
								}

								if (marche.idmarche == null) {
									pstmtUpdate_tDBOutput_2.setNull(17 + count_tDBOutput_2, java.sql.Types.INTEGER);
								} else {
									pstmtUpdate_tDBOutput_2.setInt(17 + count_tDBOutput_2, marche.idmarche);
								}

								try {

									updateFlag_tDBOutput_2 = pstmtUpdate_tDBOutput_2.executeUpdate();
									updatedCount_tDBOutput_2 = updatedCount_tDBOutput_2 + updateFlag_tDBOutput_2;
									rowsToCommitCount_tDBOutput_2 += updateFlag_tDBOutput_2;

									if (updateFlag_tDBOutput_2 == 0) {

										if (marche.idmarche == null) {
											pstmtInsert_tDBOutput_2.setNull(1, java.sql.Types.INTEGER);
										} else {
											pstmtInsert_tDBOutput_2.setInt(1, marche.idmarche);
										}

										if (marche.natureobjetmarche == null) {
											pstmtInsert_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(2, marche.natureobjetmarche);
										}

										if (marche.objetmarche == null) {
											pstmtInsert_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(3, marche.objetmarche);
										}

										if (marche.datenotification == null) {
											pstmtInsert_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(4, marche.datenotification);
										}

										if (marche.dureemois == null) {
											pstmtInsert_tDBOutput_2.setNull(5, java.sql.Types.INTEGER);
										} else {
											pstmtInsert_tDBOutput_2.setInt(5, marche.dureemois);
										}

										if (marche.lieuexecutionnom == null) {
											pstmtInsert_tDBOutput_2.setNull(6, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(6, marche.lieuexecutionnom);
										}

										if (marche.formeprix == null) {
											pstmtInsert_tDBOutput_2.setNull(7, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(7, marche.formeprix);
										}

										if (marche.lieuexecutiontypecode == null) {
											pstmtInsert_tDBOutput_2.setNull(8, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(8, marche.lieuexecutiontypecode);
										}

										if (marche.codedepartementexecution == null) {
											pstmtInsert_tDBOutput_2.setNull(9, java.sql.Types.FLOAT);
										} else {
											pstmtInsert_tDBOutput_2.setFloat(9, marche.codedepartementexecution);
										}

										if (marche.coderegionexecution == null) {
											pstmtInsert_tDBOutput_2.setNull(10, java.sql.Types.FLOAT);
										} else {
											pstmtInsert_tDBOutput_2.setFloat(10, marche.coderegionexecution);
										}

										if (marche.libelleregionexecution == null) {
											pstmtInsert_tDBOutput_2.setNull(11, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(11, marche.libelleregionexecution);
										}

										if (marche.nature == null) {
											pstmtInsert_tDBOutput_2.setNull(12, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(12, marche.nature);
										}

										if (marche.procedure == null) {
											pstmtInsert_tDBOutput_2.setNull(13, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(13, marche.procedure);
										}

										if (marche.codecpv == null) {
											pstmtInsert_tDBOutput_2.setNull(14, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(14, marche.codecpv);
										}

										if (marche.codecpv_original == null) {
											pstmtInsert_tDBOutput_2.setNull(15, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(15, marche.codecpv_original);
										}

										if (marche.codecpv_division == null) {
											pstmtInsert_tDBOutput_2.setNull(16, java.sql.Types.INTEGER);
										} else {
											pstmtInsert_tDBOutput_2.setInt(16, marche.codecpv_division);
										}

										if (marche.referencecpv == null) {
											pstmtInsert_tDBOutput_2.setNull(17, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_2.setString(17, marche.referencecpv);
										}

										int processedCount_tDBOutput_2 = pstmtInsert_tDBOutput_2.executeUpdate();
										insertedCount_tDBOutput_2 += processedCount_tDBOutput_2;
										rowsToCommitCount_tDBOutput_2 += processedCount_tDBOutput_2;
										nb_line_tDBOutput_2++;

									} else {
										nb_line_tDBOutput_2++;

									}
								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_2 = true;
									nb_line_tDBOutput_2++;
									System.err.print(e.getMessage());
								}
								if (!whetherReject_tDBOutput_2) {
									row5 = new row5Struct();
									row5.idmarche = marche.idmarche;
									row5.natureobjetmarche = marche.natureobjetmarche;
									row5.objetmarche = marche.objetmarche;
									row5.datenotification = marche.datenotification;
									row5.dureemois = marche.dureemois;
									row5.lieuexecutionnom = marche.lieuexecutionnom;
									row5.formeprix = marche.formeprix;
									row5.lieuexecutiontypecode = marche.lieuexecutiontypecode;
									row5.codedepartementexecution = marche.codedepartementexecution;
									row5.coderegionexecution = marche.coderegionexecution;
									row5.libelleregionexecution = marche.libelleregionexecution;
									row5.nature = marche.nature;
									row5.procedure = marche.procedure;
									row5.codecpv = marche.codecpv;
									row5.codecpv_original = marche.codecpv_original;
									row5.codecpv_division = marche.codecpv_division;
									row5.referencecpv = marche.referencecpv;
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
// Start of branch "row5"
								if (row5 != null) {

									/**
									 * [tDBCommit_2 main ] start
									 */

									currentComponent = "tDBCommit_2";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row5"

										);
									}

									java.sql.Connection conn_tDBCommit_2 = (java.sql.Connection) globalMap
											.get("conn_tDBConnection_1");
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

								} // End of branch "row5"

								/**
								 * [tDBOutput_2 process_data_end ] start
								 */

								currentComponent = "tDBOutput_2";

								/**
								 * [tDBOutput_2 process_data_end ] stop
								 */

							} // End of branch "marche"

// Start of branch "doublon_marche"
							if (doublon_marche != null) {

								/**
								 * [tFileOutputDelimited_2 main ] start
								 */

								currentComponent = "tFileOutputDelimited_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "doublon_marche"

									);
								}

								StringBuilder sb_tFileOutputDelimited_2 = new StringBuilder();
								if (doublon_marche.idmarche != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.idmarche);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (doublon_marche.natureobjetmarche != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.natureobjetmarche);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (doublon_marche.objetmarche != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.objetmarche);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (doublon_marche.datenotification != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.datenotification);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (doublon_marche.lieuexecutionnom != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.lieuexecutionnom);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (doublon_marche.libelleregionexecution != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.libelleregionexecution);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (doublon_marche.nature != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.nature);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (doublon_marche.codecpv != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.codecpv);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (doublon_marche.codecpv_original != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.codecpv_original);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (doublon_marche.codecpv_division != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.codecpv_division);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (doublon_marche.referencecpv != null) {
									sb_tFileOutputDelimited_2.append(doublon_marche.referencecpv);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);

								nb_line_tFileOutputDelimited_2++;
								resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

								outtFileOutputDelimited_2.write(sb_tFileOutputDelimited_2.toString());

								tos_count_tFileOutputDelimited_2++;

								/**
								 * [tFileOutputDelimited_2 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_2 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_2";

								/**
								 * [tFileOutputDelimited_2 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_2 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_2";

								/**
								 * [tFileOutputDelimited_2 process_data_end ] stop
								 */

							} // End of branch "doublon_marche"

							/**
							 * [tMap_3 process_data_end ] start
							 */

							currentComponent = "tMap_3";

							/**
							 * [tMap_3 process_data_end ] stop
							 */

						} // End of branch "dim_marche"

// Start of branch "dim_etablissement"
						if (dim_etablissement != null) {

							/**
							 * [tMap_4 main ] start
							 */

							currentComponent = "tMap_4";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "dim_etablissement"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_4 = false;
							boolean mainRowRejected_tMap_4 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row7"
							///////////////////////////////////////////////

							boolean forceLooprow7 = false;

							row7Struct row7ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_4) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_4 = false;

								row7HashKey.siren = dim_etablissement.sirenetablissement;

								row7HashKey.hashCodeDirty = true;

								tDBInput_4Process(globalMap);

								tHash_Lookup_row7 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) globalMap
										.get("tHash_Lookup_row7"));

								tHash_Lookup_row7.initGet();

								tHash_Lookup_row7.lookup(row7HashKey);

								if (!tHash_Lookup_row7.hasNext()) { // G_TM_M_090

									rejectedInnerJoin_tMap_4 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							if (tHash_Lookup_row7 != null && tHash_Lookup_row7.getCount(row7HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row7'
								// and it contains more one result from keys : row7.siren = '" +
								// row7HashKey.siren + "'");
							} // G 071

							row7Struct row7 = null;

							row7Struct fromLookup_row7 = null;
							row7 = row7Default;

							if (tHash_Lookup_row7 != null && tHash_Lookup_row7.hasNext()) { // G 099

								fromLookup_row7 = tHash_Lookup_row7.next();

							} // G 099

							if (fromLookup_row7 != null) {
								row7 = fromLookup_row7;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_4__Struct Var = Var__tMap_4;
								Var.isNotNull = DataProcessing.checkForNull(dim_etablissement.sirenetablissement);// ###############################
								// ###############################
								// # Output tables

								doublon_etablissement = null;
								etablissement = null;
								siren_null = null;

								if (!rejectedInnerJoin_tMap_4) {

// # Output table : 'doublon_etablissement'
									doublon_etablissement_tmp.sirenetablissement = null;
									doublon_etablissement_tmp.siretetablissement = null;
									doublon_etablissement_tmp.siretetablissementvalide = null;
									doublon_etablissement_tmp.categorieetablissement = null;
									doublon_etablissement_tmp.denominationsocialeetablissement = null;
									doublon_etablissement_tmp.codecommuneetablissement = null;
									doublon_etablissement_tmp.communeetablissement = null;
									doublon_etablissement_tmp.departementetablissement = null;
									doublon_etablissement_tmp.libelledepartementetablissement = null;
									doublon_etablissement = doublon_etablissement_tmp;
								} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'etablissement'
// # Filter conditions 
								if (rejectedInnerJoin_tMap_4 && (

								Var.isNotNull

								)) {
									etablissement_tmp.sirenetablissement = dim_etablissement.sirenetablissement;
									etablissement_tmp.siretetablissement = dim_etablissement.siretetablissement;
									etablissement_tmp.siretetablissementvalide = dim_etablissement.siretetablissementvalide;
									etablissement_tmp.categorieetablissement = dim_etablissement.categorieetablissement;
									etablissement_tmp.denominationsocialeetablissement = dim_etablissement.denominationsocialeetablissement;
									etablissement_tmp.codecommuneetablissement = dim_etablissement.codecommuneetablissement;
									etablissement_tmp.communeetablissement = dim_etablissement.communeetablissement;
									etablissement_tmp.departementetablissement = dim_etablissement.departementetablissement;
									etablissement_tmp.libelledepartementetablissement = dim_etablissement.libelledepartementetablissement;
									etablissement_tmp.coderegionetablissement = dim_etablissement.coderegionetablissement;
									etablissement_tmp.libelleregionetablissement = dim_etablissement.libelleregionetablissement;
									etablissement = etablissement_tmp;
								} // closing filter/reject

// # Output reject table : 'siren_null'
// # Filter conditions 
								if (rejectedInnerJoin_tMap_4 && (

								Var.isNotNull == false

								)) {
									siren_null_tmp.sirenetablissement = dim_etablissement.sirenetablissement;
									siren_null_tmp.siretetablissement = dim_etablissement.siretetablissement;
									siren_null_tmp.siretetablissementvalide = dim_etablissement.siretetablissementvalide;
									siren_null_tmp.categorieetablissement = dim_etablissement.categorieetablissement;
									siren_null_tmp.denominationsocialeetablissement = dim_etablissement.denominationsocialeetablissement;
									siren_null_tmp.codecommuneetablissement = dim_etablissement.codecommuneetablissement;
									siren_null_tmp.communeetablissement = dim_etablissement.communeetablissement;
									siren_null_tmp.departementetablissement = dim_etablissement.departementetablissement;
									siren_null_tmp.libelledepartementetablissement = dim_etablissement.libelledepartementetablissement;
									siren_null_tmp.coderegionetablissement = dim_etablissement.coderegionetablissement;
									siren_null_tmp.libelleregionetablissement = dim_etablissement.libelleregionetablissement;
									siren_null_tmp.recordid = dim_etablissement.recordid;
									siren_null_tmp.idmarche = dim_etablissement.idmarche;
									siren_null = siren_null_tmp;
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
// Start of branch "etablissement"
							if (etablissement != null) {

								/**
								 * [tDBOutput_3 main ] start
								 */

								currentComponent = "tDBOutput_3";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "etablissement"

									);
								}

								row6 = null;
								whetherReject_tDBOutput_3 = false;
								int updateFlag_tDBOutput_3 = 0;
								if (etablissement.siretetablissement == null) {
									pstmtUpdate_tDBOutput_3.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(1, etablissement.siretetablissement);
								}

								if (etablissement.siretetablissementvalide == null) {
									pstmtUpdate_tDBOutput_3.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(2, etablissement.siretetablissementvalide);
								}

								if (etablissement.categorieetablissement == null) {
									pstmtUpdate_tDBOutput_3.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(3, etablissement.categorieetablissement);
								}

								if (etablissement.denominationsocialeetablissement == null) {
									pstmtUpdate_tDBOutput_3.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(4,
											etablissement.denominationsocialeetablissement);
								}

								if (etablissement.codecommuneetablissement == null) {
									pstmtUpdate_tDBOutput_3.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(5, etablissement.codecommuneetablissement);
								}

								if (etablissement.communeetablissement == null) {
									pstmtUpdate_tDBOutput_3.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(6, etablissement.communeetablissement);
								}

								if (etablissement.departementetablissement == null) {
									pstmtUpdate_tDBOutput_3.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(7, etablissement.departementetablissement);
								}

								if (etablissement.libelledepartementetablissement == null) {
									pstmtUpdate_tDBOutput_3.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(8, etablissement.libelledepartementetablissement);
								}

								if (etablissement.coderegionetablissement == null) {
									pstmtUpdate_tDBOutput_3.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(9, etablissement.coderegionetablissement);
								}

								if (etablissement.libelleregionetablissement == null) {
									pstmtUpdate_tDBOutput_3.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(10, etablissement.libelleregionetablissement);
								}

								if (etablissement.sirenetablissement == null) {
									pstmtUpdate_tDBOutput_3.setNull(11 + count_tDBOutput_3, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_3.setString(11 + count_tDBOutput_3,
											etablissement.sirenetablissement);
								}

								try {

									updateFlag_tDBOutput_3 = pstmtUpdate_tDBOutput_3.executeUpdate();
									updatedCount_tDBOutput_3 = updatedCount_tDBOutput_3 + updateFlag_tDBOutput_3;
									rowsToCommitCount_tDBOutput_3 += updateFlag_tDBOutput_3;

									if (updateFlag_tDBOutput_3 == 0) {

										if (etablissement.sirenetablissement == null) {
											pstmtInsert_tDBOutput_3.setNull(1, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(1, etablissement.sirenetablissement);
										}

										if (etablissement.siretetablissement == null) {
											pstmtInsert_tDBOutput_3.setNull(2, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(2, etablissement.siretetablissement);
										}

										if (etablissement.siretetablissementvalide == null) {
											pstmtInsert_tDBOutput_3.setNull(3, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(3,
													etablissement.siretetablissementvalide);
										}

										if (etablissement.categorieetablissement == null) {
											pstmtInsert_tDBOutput_3.setNull(4, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(4, etablissement.categorieetablissement);
										}

										if (etablissement.denominationsocialeetablissement == null) {
											pstmtInsert_tDBOutput_3.setNull(5, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(5,
													etablissement.denominationsocialeetablissement);
										}

										if (etablissement.codecommuneetablissement == null) {
											pstmtInsert_tDBOutput_3.setNull(6, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(6,
													etablissement.codecommuneetablissement);
										}

										if (etablissement.communeetablissement == null) {
											pstmtInsert_tDBOutput_3.setNull(7, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(7, etablissement.communeetablissement);
										}

										if (etablissement.departementetablissement == null) {
											pstmtInsert_tDBOutput_3.setNull(8, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(8,
													etablissement.departementetablissement);
										}

										if (etablissement.libelledepartementetablissement == null) {
											pstmtInsert_tDBOutput_3.setNull(9, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(9,
													etablissement.libelledepartementetablissement);
										}

										if (etablissement.coderegionetablissement == null) {
											pstmtInsert_tDBOutput_3.setNull(10, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(10,
													etablissement.coderegionetablissement);
										}

										if (etablissement.libelleregionetablissement == null) {
											pstmtInsert_tDBOutput_3.setNull(11, java.sql.Types.VARCHAR);
										} else {
											pstmtInsert_tDBOutput_3.setString(11,
													etablissement.libelleregionetablissement);
										}

										int processedCount_tDBOutput_3 = pstmtInsert_tDBOutput_3.executeUpdate();
										insertedCount_tDBOutput_3 += processedCount_tDBOutput_3;
										rowsToCommitCount_tDBOutput_3 += processedCount_tDBOutput_3;
										nb_line_tDBOutput_3++;

									} else {
										nb_line_tDBOutput_3++;

									}
								} catch (java.lang.Exception e) {
									globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());

									whetherReject_tDBOutput_3 = true;
									nb_line_tDBOutput_3++;
									System.err.print(e.getMessage());
								}
								if (!whetherReject_tDBOutput_3) {
									row6 = new row6Struct();
									row6.sirenetablissement = etablissement.sirenetablissement;
									row6.siretetablissement = etablissement.siretetablissement;
									row6.siretetablissementvalide = etablissement.siretetablissementvalide;
									row6.categorieetablissement = etablissement.categorieetablissement;
									row6.denominationsocialeetablissement = etablissement.denominationsocialeetablissement;
									row6.codecommuneetablissement = etablissement.codecommuneetablissement;
									row6.communeetablissement = etablissement.communeetablissement;
									row6.departementetablissement = etablissement.departementetablissement;
									row6.libelledepartementetablissement = etablissement.libelledepartementetablissement;
									row6.coderegionetablissement = etablissement.coderegionetablissement;
									row6.libelleregionetablissement = etablissement.libelleregionetablissement;
								}

								tos_count_tDBOutput_3++;

								/**
								 * [tDBOutput_3 main ] stop
								 */

								/**
								 * [tDBOutput_3 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_3";

								/**
								 * [tDBOutput_3 process_data_begin ] stop
								 */
// Start of branch "row6"
								if (row6 != null) {

									/**
									 * [tDBCommit_3 main ] start
									 */

									currentComponent = "tDBCommit_3";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row6"

										);
									}

									java.sql.Connection conn_tDBCommit_3 = (java.sql.Connection) globalMap
											.get("conn_tDBConnection_1");
									if (conn_tDBCommit_3 != null && !conn_tDBCommit_3.isClosed()) {

										conn_tDBCommit_3.commit();

									}

									tos_count_tDBCommit_3++;

									/**
									 * [tDBCommit_3 main ] stop
									 */

									/**
									 * [tDBCommit_3 process_data_begin ] start
									 */

									currentComponent = "tDBCommit_3";

									/**
									 * [tDBCommit_3 process_data_begin ] stop
									 */

									/**
									 * [tDBCommit_3 process_data_end ] start
									 */

									currentComponent = "tDBCommit_3";

									/**
									 * [tDBCommit_3 process_data_end ] stop
									 */

								} // End of branch "row6"

								/**
								 * [tDBOutput_3 process_data_end ] start
								 */

								currentComponent = "tDBOutput_3";

								/**
								 * [tDBOutput_3 process_data_end ] stop
								 */

							} // End of branch "etablissement"

// Start of branch "doublon_etablissement"
							if (doublon_etablissement != null) {

								/**
								 * [tFileOutputDelimited_3 main ] start
								 */

								currentComponent = "tFileOutputDelimited_3";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "doublon_etablissement"

									);
								}

								StringBuilder sb_tFileOutputDelimited_3 = new StringBuilder();
								if (doublon_etablissement.sirenetablissement != null) {
									sb_tFileOutputDelimited_3.append(doublon_etablissement.sirenetablissement);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (doublon_etablissement.siretetablissement != null) {
									sb_tFileOutputDelimited_3.append(doublon_etablissement.siretetablissement);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (doublon_etablissement.siretetablissementvalide != null) {
									sb_tFileOutputDelimited_3.append(doublon_etablissement.siretetablissementvalide);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (doublon_etablissement.categorieetablissement != null) {
									sb_tFileOutputDelimited_3.append(doublon_etablissement.categorieetablissement);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (doublon_etablissement.denominationsocialeetablissement != null) {
									sb_tFileOutputDelimited_3
											.append(doublon_etablissement.denominationsocialeetablissement);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (doublon_etablissement.codecommuneetablissement != null) {
									sb_tFileOutputDelimited_3.append(doublon_etablissement.codecommuneetablissement);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (doublon_etablissement.communeetablissement != null) {
									sb_tFileOutputDelimited_3.append(doublon_etablissement.communeetablissement);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (doublon_etablissement.departementetablissement != null) {
									sb_tFileOutputDelimited_3.append(doublon_etablissement.departementetablissement);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (doublon_etablissement.libelledepartementetablissement != null) {
									sb_tFileOutputDelimited_3
											.append(doublon_etablissement.libelledepartementetablissement);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_3);

								nb_line_tFileOutputDelimited_3++;
								resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);

								outtFileOutputDelimited_3.write(sb_tFileOutputDelimited_3.toString());

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
								 * [tFileOutputDelimited_3 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_3";

								/**
								 * [tFileOutputDelimited_3 process_data_end ] stop
								 */

							} // End of branch "doublon_etablissement"

// Start of branch "siren_null"
							if (siren_null != null) {

								/**
								 * [tFileOutputDelimited_5 main ] start
								 */

								currentComponent = "tFileOutputDelimited_5";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "siren_null"

									);
								}

								StringBuilder sb_tFileOutputDelimited_5 = new StringBuilder();
								if (siren_null.sirenetablissement != null) {
									sb_tFileOutputDelimited_5.append(siren_null.sirenetablissement);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.siretetablissement != null) {
									sb_tFileOutputDelimited_5.append(siren_null.siretetablissement);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.siretetablissementvalide != null) {
									sb_tFileOutputDelimited_5.append(siren_null.siretetablissementvalide);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.categorieetablissement != null) {
									sb_tFileOutputDelimited_5.append(siren_null.categorieetablissement);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.denominationsocialeetablissement != null) {
									sb_tFileOutputDelimited_5.append(siren_null.denominationsocialeetablissement);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.codecommuneetablissement != null) {
									sb_tFileOutputDelimited_5.append(siren_null.codecommuneetablissement);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.communeetablissement != null) {
									sb_tFileOutputDelimited_5.append(siren_null.communeetablissement);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.departementetablissement != null) {
									sb_tFileOutputDelimited_5.append(siren_null.departementetablissement);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.libelledepartementetablissement != null) {
									sb_tFileOutputDelimited_5.append(siren_null.libelledepartementetablissement);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.coderegionetablissement != null) {
									sb_tFileOutputDelimited_5.append(siren_null.coderegionetablissement);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.libelleregionetablissement != null) {
									sb_tFileOutputDelimited_5.append(siren_null.libelleregionetablissement);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.recordid != null) {
									sb_tFileOutputDelimited_5.append(siren_null.recordid);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (siren_null.idmarche != null) {
									sb_tFileOutputDelimited_5.append(siren_null.idmarche);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_5);

								nb_line_tFileOutputDelimited_5++;
								resourceMap.put("nb_line_tFileOutputDelimited_5", nb_line_tFileOutputDelimited_5);

								outtFileOutputDelimited_5.write(sb_tFileOutputDelimited_5.toString());

								tos_count_tFileOutputDelimited_5++;

								/**
								 * [tFileOutputDelimited_5 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_5 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_5";

								/**
								 * [tFileOutputDelimited_5 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_5 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_5";

								/**
								 * [tFileOutputDelimited_5 process_data_end ] stop
								 */

							} // End of branch "siren_null"

							/**
							 * [tMap_4 process_data_end ] start
							 */

							currentComponent = "tMap_4";

							/**
							 * [tMap_4 process_data_end ] stop
							 */

						} // End of branch "dim_etablissement"

						/**
						 * [tMap_1 process_data_end ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

					}
				} finally {
					if (rs_tDBInput_1 != null) {
						rs_tDBInput_1.close();
					}
					if (stmt_tDBInput_1 != null) {
						stmt_tDBInput_1.close();
					}
				}
				globalMap.put("tDBInput_1_NB_LINE", nb_line_tDBInput_1);

				ok_Hash.put("tDBInput_1", true);
				end_Hash.put("tDBInput_1", System.currentTimeMillis());

				/**
				 * [tDBInput_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tMap_2 end ] start
				 */

				currentComponent = "tMap_2";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row2 != null) {
					tHash_Lookup_row2.endGet();
				}
				globalMap.remove("tHash_Lookup_row2");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "dim_acheteur");
				}

				ok_Hash.put("tMap_2", true);
				end_Hash.put("tMap_2", System.currentTimeMillis());

				/**
				 * [tMap_2 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				if (pstmtUpdate_tDBOutput_1 != null) {
					pstmtUpdate_tDBOutput_1.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_1");
				}
				if (pstmtInsert_tDBOutput_1 != null) {
					pstmtInsert_tDBOutput_1.close();
					resourceMap.remove("pstmtInsert_tDBOutput_1");
				}
				resourceMap.put("statementClosed_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "acheteur");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

				/**
				 * [tDBCommit_1 end ] start
				 */

				currentComponent = "tDBCommit_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tDBCommit_1", true);
				end_Hash.put("tDBCommit_1", System.currentTimeMillis());

				/**
				 * [tDBCommit_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "doublons_acheteur");
				}

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_3 end ] start
				 */

				currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row3 != null) {
					tHash_Lookup_row3.endGet();
				}
				globalMap.remove("tHash_Lookup_row3");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "dim_marche");
				}

				ok_Hash.put("tMap_3", true);
				end_Hash.put("tMap_3", System.currentTimeMillis());

				/**
				 * [tMap_3 end ] stop
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "marche");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

				/**
				 * [tDBCommit_2 end ] start
				 */

				currentComponent = "tDBCommit_2";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tDBCommit_2", true);
				end_Hash.put("tDBCommit_2", System.currentTimeMillis());

				/**
				 * [tDBCommit_2 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 end ] start
				 */

				currentComponent = "tFileOutputDelimited_2";

				if (outtFileOutputDelimited_2 != null) {
					outtFileOutputDelimited_2.flush();
					outtFileOutputDelimited_2.close();
				}

				globalMap.put("tFileOutputDelimited_2_NB_LINE", nb_line_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);

				resourceMap.put("finish_tFileOutputDelimited_2", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "doublon_marche");
				}

				ok_Hash.put("tFileOutputDelimited_2", true);
				end_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_2 end ] stop
				 */

				/**
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row7 != null) {
					tHash_Lookup_row7.endGet();
				}
				globalMap.remove("tHash_Lookup_row7");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "dim_etablissement");
				}

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
				 */

				/**
				 * [tDBOutput_3 end ] start
				 */

				currentComponent = "tDBOutput_3";

				if (pstmtUpdate_tDBOutput_3 != null) {
					pstmtUpdate_tDBOutput_3.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_3");
				}
				if (pstmtInsert_tDBOutput_3 != null) {
					pstmtInsert_tDBOutput_3.close();
					resourceMap.remove("pstmtInsert_tDBOutput_3");
				}
				resourceMap.put("statementClosed_tDBOutput_3", true);

				nb_line_deleted_tDBOutput_3 = nb_line_deleted_tDBOutput_3 + deletedCount_tDBOutput_3;
				nb_line_update_tDBOutput_3 = nb_line_update_tDBOutput_3 + updatedCount_tDBOutput_3;
				nb_line_inserted_tDBOutput_3 = nb_line_inserted_tDBOutput_3 + insertedCount_tDBOutput_3;
				nb_line_rejected_tDBOutput_3 = nb_line_rejected_tDBOutput_3 + rejectedCount_tDBOutput_3;

				globalMap.put("tDBOutput_3_NB_LINE", nb_line_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_UPDATED", nb_line_update_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_DELETED", nb_line_deleted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_3);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "etablissement");
				}

				ok_Hash.put("tDBOutput_3", true);
				end_Hash.put("tDBOutput_3", System.currentTimeMillis());

				/**
				 * [tDBOutput_3 end ] stop
				 */

				/**
				 * [tDBCommit_3 end ] start
				 */

				currentComponent = "tDBCommit_3";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
				}

				ok_Hash.put("tDBCommit_3", true);
				end_Hash.put("tDBCommit_3", System.currentTimeMillis());

				/**
				 * [tDBCommit_3 end ] stop
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "doublon_etablissement");
				}

				ok_Hash.put("tFileOutputDelimited_3", true);
				end_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_3 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_5 end ] start
				 */

				currentComponent = "tFileOutputDelimited_5";

				if (outtFileOutputDelimited_5 != null) {
					outtFileOutputDelimited_5.flush();
					outtFileOutputDelimited_5.close();
				}

				globalMap.put("tFileOutputDelimited_5_NB_LINE", nb_line_tFileOutputDelimited_5);
				globalMap.put("tFileOutputDelimited_5_FILE_NAME", fileName_tFileOutputDelimited_5);

				resourceMap.put("finish_tFileOutputDelimited_5", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "siren_null");
				}

				ok_Hash.put("tFileOutputDelimited_5", true);
				end_Hash.put("tFileOutputDelimited_5", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_5 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tDBInput_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk3", 0, "ok");
			}

			tDBInput_5Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_4"
			globalMap.remove("tHash_Lookup_row7");

			// free memory for "tMap_3"
			globalMap.remove("tHash_Lookup_row3");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row2");

			try {

				/**
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
					java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_1 = null;
					if ((pstmtUpdateToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmtUpdate_tDBOutput_1")) != null) {
						pstmtUpdateToClose_tDBOutput_1.close();
					}
					java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_1 = null;
					if ((pstmtInsertToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmtInsert_tDBOutput_1")) != null) {
						pstmtInsertToClose_tDBOutput_1.close();
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

				/**
				 * [tDBCommit_1 finally ] start
				 */

				currentComponent = "tDBCommit_1";

				/**
				 * [tDBCommit_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_3 finally ] start
				 */

				currentComponent = "tMap_3";

				/**
				 * [tMap_3 finally ] stop
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
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

				/**
				 * [tDBCommit_2 finally ] start
				 */

				currentComponent = "tDBCommit_2";

				/**
				 * [tDBCommit_2 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_2";

				if (resourceMap.get("finish_tFileOutputDelimited_2") == null) {

					java.io.Writer outtFileOutputDelimited_2 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_2");
					if (outtFileOutputDelimited_2 != null) {
						outtFileOutputDelimited_2.flush();
						outtFileOutputDelimited_2.close();
					}

				}

				/**
				 * [tFileOutputDelimited_2 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
				 */

				/**
				 * [tDBOutput_3 finally ] start
				 */

				currentComponent = "tDBOutput_3";

				if (resourceMap.get("statementClosed_tDBOutput_3") == null) {
					java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_3 = null;
					if ((pstmtUpdateToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmtUpdate_tDBOutput_3")) != null) {
						pstmtUpdateToClose_tDBOutput_3.close();
					}
					java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_3 = null;
					if ((pstmtInsertToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmtInsert_tDBOutput_3")) != null) {
						pstmtInsertToClose_tDBOutput_3.close();
					}
				}

				/**
				 * [tDBOutput_3 finally ] stop
				 */

				/**
				 * [tDBCommit_3 finally ] start
				 */

				currentComponent = "tDBCommit_3";

				/**
				 * [tDBCommit_3 finally ] stop
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
				 * [tFileOutputDelimited_5 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_5";

				if (resourceMap.get("finish_tFileOutputDelimited_5") == null) {

					java.io.Writer outtFileOutputDelimited_5 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_5");
					if (outtFileOutputDelimited_5 != null) {
						outtFileOutputDelimited_5.flush();
						outtFileOutputDelimited_5.close();
					}

				}

				/**
				 * [tFileOutputDelimited_5 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public String datenotification;

		public String getDatenotification() {
			return this.datenotification;
		}

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		public String montantmarche;

		public String getMontantmarche() {
			return this.montantmarche;
		}

		public Float montantcalculemarche;

		public Float getMontantcalculemarche() {
			return this.montantcalculemarche;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.sirenetablissement == null) ? 0 : this.sirenetablissement.hashCode());

				result = prime * result + ((this.idacheteur == null) ? 0 : this.idacheteur.hashCode());

				result = prime * result + ((this.datenotification == null) ? 0 : this.datenotification.hashCode());

				result = prime * result + ((this.idmarche == null) ? 0 : this.idmarche.hashCode());

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
			final row9Struct other = (row9Struct) obj;

			if (this.sirenetablissement == null) {
				if (other.sirenetablissement != null)
					return false;

			} else if (!this.sirenetablissement.equals(other.sirenetablissement))

				return false;

			if (this.idacheteur == null) {
				if (other.idacheteur != null)
					return false;

			} else if (!this.idacheteur.equals(other.idacheteur))

				return false;

			if (this.datenotification == null) {
				if (other.datenotification != null)
					return false;

			} else if (!this.datenotification.equals(other.datenotification))

				return false;

			if (this.idmarche == null) {
				if (other.idmarche != null)
					return false;

			} else if (!this.idmarche.equals(other.idmarche))

				return false;

			return true;
		}

		public void copyDataTo(row9Struct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.idacheteur = this.idacheteur;
			other.datenotification = this.datenotification;
			other.idmarche = this.idmarche;
			other.montantmarche = this.montantmarche;
			other.montantcalculemarche = this.montantcalculemarche;

		}

		public void copyKeysDataTo(row9Struct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.idacheteur = this.idacheteur;
			other.datenotification = this.datenotification;
			other.idmarche = this.idmarche;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.idacheteur = readString(dis);

					this.datenotification = readString(dis);

					this.idmarche = readInteger(dis);

					this.montantmarche = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montantcalculemarche = null;
					} else {
						this.montantcalculemarche = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.idacheteur = readString(dis);

					this.datenotification = readString(dis);

					this.idmarche = readInteger(dis);

					this.montantmarche = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montantcalculemarche = null;
					} else {
						this.montantcalculemarche = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.datenotification, dos);

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.montantmarche, dos);

				// Float

				if (this.montantcalculemarche == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalculemarche);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.datenotification, dos);

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.montantmarche, dos);

				// Float

				if (this.montantcalculemarche == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalculemarche);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("sirenetablissement=" + sirenetablissement);
			sb.append(",idacheteur=" + idacheteur);
			sb.append(",datenotification=" + datenotification);
			sb.append(",idmarche=" + String.valueOf(idmarche));
			sb.append(",montantmarche=" + montantmarche);
			sb.append(",montantcalculemarche=" + String.valueOf(montantcalculemarche));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.sirenetablissement, other.sirenetablissement);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.idacheteur, other.idacheteur);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.datenotification, other.datenotification);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.idmarche, other.idmarche);
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

	public static class fait_marcheStruct implements routines.system.IPersistableRow<fait_marcheStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public String datenotification;

		public String getDatenotification() {
			return this.datenotification;
		}

		public Integer idmarche;

		public Integer getIdmarche() {
			return this.idmarche;
		}

		public String montantmarche;

		public String getMontantmarche() {
			return this.montantmarche;
		}

		public Float montantcalculemarche;

		public Float getMontantcalculemarche() {
			return this.montantcalculemarche;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.sirenetablissement == null) ? 0 : this.sirenetablissement.hashCode());

				result = prime * result + ((this.idacheteur == null) ? 0 : this.idacheteur.hashCode());

				result = prime * result + ((this.datenotification == null) ? 0 : this.datenotification.hashCode());

				result = prime * result + ((this.idmarche == null) ? 0 : this.idmarche.hashCode());

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
			final fait_marcheStruct other = (fait_marcheStruct) obj;

			if (this.sirenetablissement == null) {
				if (other.sirenetablissement != null)
					return false;

			} else if (!this.sirenetablissement.equals(other.sirenetablissement))

				return false;

			if (this.idacheteur == null) {
				if (other.idacheteur != null)
					return false;

			} else if (!this.idacheteur.equals(other.idacheteur))

				return false;

			if (this.datenotification == null) {
				if (other.datenotification != null)
					return false;

			} else if (!this.datenotification.equals(other.datenotification))

				return false;

			if (this.idmarche == null) {
				if (other.idmarche != null)
					return false;

			} else if (!this.idmarche.equals(other.idmarche))

				return false;

			return true;
		}

		public void copyDataTo(fait_marcheStruct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.idacheteur = this.idacheteur;
			other.datenotification = this.datenotification;
			other.idmarche = this.idmarche;
			other.montantmarche = this.montantmarche;
			other.montantcalculemarche = this.montantcalculemarche;

		}

		public void copyKeysDataTo(fait_marcheStruct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.idacheteur = this.idacheteur;
			other.datenotification = this.datenotification;
			other.idmarche = this.idmarche;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.idacheteur = readString(dis);

					this.datenotification = readString(dis);

					this.idmarche = readInteger(dis);

					this.montantmarche = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montantcalculemarche = null;
					} else {
						this.montantcalculemarche = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.idacheteur = readString(dis);

					this.datenotification = readString(dis);

					this.idmarche = readInteger(dis);

					this.montantmarche = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.montantcalculemarche = null;
					} else {
						this.montantcalculemarche = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.datenotification, dos);

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.montantmarche, dos);

				// Float

				if (this.montantcalculemarche == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalculemarche);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.idacheteur, dos);

				// String

				writeString(this.datenotification, dos);

				// Integer

				writeInteger(this.idmarche, dos);

				// String

				writeString(this.montantmarche, dos);

				// Float

				if (this.montantcalculemarche == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.montantcalculemarche);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("sirenetablissement=" + sirenetablissement);
			sb.append(",idacheteur=" + idacheteur);
			sb.append(",datenotification=" + datenotification);
			sb.append(",idmarche=" + String.valueOf(idmarche));
			sb.append(",montantmarche=" + montantmarche);
			sb.append(",montantcalculemarche=" + String.valueOf(montantcalculemarche));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(fait_marcheStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.sirenetablissement, other.sirenetablissement);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.idacheteur, other.idacheteur);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.datenotification, other.datenotification);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.idmarche, other.idmarche);
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

	public static class row8Struct implements routines.system.IPersistableRow<row8Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

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

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

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
		public int compareTo(row8Struct other) {

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

	public void tDBInput_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 0);

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

				row8Struct row8 = new row8Struct();
				fait_marcheStruct fait_marche = new fait_marcheStruct();
				row9Struct row9 = new row9Struct();

				/**
				 * [tDBCommit_4 begin ] start
				 */

				ok_Hash.put("tDBCommit_4", false);
				start_Hash.put("tDBCommit_4", System.currentTimeMillis());

				currentComponent = "tDBCommit_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row9");
				}

				int tos_count_tDBCommit_4 = 0;

				/**
				 * [tDBCommit_4 begin ] stop
				 */

				/**
				 * [tDBOutput_4 begin ] start
				 */

				ok_Hash.put("tDBOutput_4", false);
				start_Hash.put("tDBOutput_4", System.currentTimeMillis());

				currentComponent = "tDBOutput_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "fait_marche");
				}

				int tos_count_tDBOutput_4 = 0;

				String dbschema_tDBOutput_4 = null;
				dbschema_tDBOutput_4 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_4 = null;
				if (dbschema_tDBOutput_4 == null || dbschema_tDBOutput_4.trim().length() == 0) {
					tableName_tDBOutput_4 = ("fait_marche");
				} else {
					tableName_tDBOutput_4 = dbschema_tDBOutput_4 + "\".\"" + ("fait_marche");
				}

				int updateKeyCount_tDBOutput_4 = 4;
				if (updateKeyCount_tDBOutput_4 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_4 == 6 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_4 = 0;
				int nb_line_update_tDBOutput_4 = 0;
				int nb_line_inserted_tDBOutput_4 = 0;
				int nb_line_deleted_tDBOutput_4 = 0;
				int nb_line_rejected_tDBOutput_4 = 0;

				int deletedCount_tDBOutput_4 = 0;
				int updatedCount_tDBOutput_4 = 0;
				int insertedCount_tDBOutput_4 = 0;
				int rowsToCommitCount_tDBOutput_4 = 0;
				int rejectedCount_tDBOutput_4 = 0;

				boolean whetherReject_tDBOutput_4 = false;

				java.sql.Connection conn_tDBOutput_4 = null;
				String dbUser_tDBOutput_4 = null;

				conn_tDBOutput_4 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				int count_tDBOutput_4 = 0;
				String update_tDBOutput_4 = "UPDATE \"" + tableName_tDBOutput_4
						+ "\" SET \"montantmarche\" = ?,\"montantcalculemarche\" = ? WHERE \"sirenetablissement\" = ? AND \"idacheteur\" = ? AND \"datenotification\" = ? AND \"idmarche\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_4 = conn_tDBOutput_4
						.prepareStatement(update_tDBOutput_4);
				resourceMap.put("pstmtUpdate_tDBOutput_4", pstmtUpdate_tDBOutput_4);
				String insert_tDBOutput_4 = "INSERT INTO \"" + tableName_tDBOutput_4
						+ "\" (\"sirenetablissement\",\"idacheteur\",\"datenotification\",\"idmarche\",\"montantmarche\",\"montantcalculemarche\") VALUES (?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_4 = conn_tDBOutput_4
						.prepareStatement(insert_tDBOutput_4);
				resourceMap.put("pstmtInsert_tDBOutput_4", pstmtInsert_tDBOutput_4);

				/**
				 * [tDBOutput_4 begin ] stop
				 */

				/**
				 * [tMap_5 begin ] start
				 */

				ok_Hash.put("tMap_5", false);
				start_Hash.put("tMap_5", System.currentTimeMillis());

				currentComponent = "tMap_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row8");
				}

				int tos_count_tMap_5 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_5__Struct {
					boolean isSirenNull;
					boolean isDateNull;
					String newDate;
					String newSiren;
				}
				Var__tMap_5__Struct Var__tMap_5 = new Var__tMap_5__Struct();
// ###############################

// ###############################
// # Outputs initialization
				fait_marcheStruct fait_marche_tmp = new fait_marcheStruct();
// ###############################

				/**
				 * [tMap_5 begin ] stop
				 */

				/**
				 * [tDBInput_5 begin ] start
				 */

				ok_Hash.put("tDBInput_5", false);
				start_Hash.put("tDBInput_5", System.currentTimeMillis());

				currentComponent = "tDBInput_5";

				int tos_count_tDBInput_5 = 0;

				int nb_line_tDBInput_5 = 0;
				java.sql.Connection conn_tDBInput_5 = null;
				conn_tDBInput_5 = (java.sql.Connection) globalMap.get("conn_tDBConnection_2");

				java.sql.Statement stmt_tDBInput_5 = conn_tDBInput_5.createStatement();

				String dbquery_tDBInput_5 = "select * FROM stg.marche";

				globalMap.put("tDBInput_5_QUERY", dbquery_tDBInput_5);
				java.sql.ResultSet rs_tDBInput_5 = null;

				try {
					rs_tDBInput_5 = stmt_tDBInput_5.executeQuery(dbquery_tDBInput_5);
					java.sql.ResultSetMetaData rsmd_tDBInput_5 = rs_tDBInput_5.getMetaData();
					int colQtyInRs_tDBInput_5 = rsmd_tDBInput_5.getColumnCount();

					String tmpContent_tDBInput_5 = null;

					while (rs_tDBInput_5.next()) {
						nb_line_tDBInput_5++;

						if (colQtyInRs_tDBInput_5 < 1) {
							row8.recordid = null;
						} else {

							row8.recordid = routines.system.JDBCUtil.getString(rs_tDBInput_5, 1, false);
						}
						if (colQtyInRs_tDBInput_5 < 2) {
							row8.sirenetablissementvalide = null;
						} else {

							row8.sirenetablissementvalide = routines.system.JDBCUtil.getString(rs_tDBInput_5, 2, false);
						}
						if (colQtyInRs_tDBInput_5 < 3) {
							row8.denominationsocialeetablissement = null;
						} else {

							row8.denominationsocialeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 3,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 4) {
							row8.libellecommuneacheteur = null;
						} else {

							row8.libellecommuneacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_5, 4, false);
						}
						if (colQtyInRs_tDBInput_5 < 5) {
							row8.lieuexecutioncode = null;
						} else {

							row8.lieuexecutioncode = routines.system.JDBCUtil.getString(rs_tDBInput_5, 5, false);
						}
						if (colQtyInRs_tDBInput_5 < 6) {
							row8.adresseetablissement = null;
						} else {

							row8.adresseetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 6, false);
						}
						if (colQtyInRs_tDBInput_5 < 7) {
							row8.codepostalacheteur = null;
						} else {

							row8.codepostalacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_5, 7, false);
						}
						if (colQtyInRs_tDBInput_5 < 8) {
							row8.libelleregionacheteur = null;
						} else {

							row8.libelleregionacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_5, 8, false);
						}
						if (colQtyInRs_tDBInput_5 < 9) {
							row8.departementacheteur = null;
						} else {

							row8.departementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_5, 9, false);
						}
						if (colQtyInRs_tDBInput_5 < 10) {
							row8.idmarche = null;
						} else {

							row8.idmarche = rs_tDBInput_5.getInt(10);
							if (rs_tDBInput_5.wasNull()) {
								row8.idmarche = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 11) {
							row8.dureemois = null;
						} else {

							row8.dureemois = rs_tDBInput_5.getInt(11);
							if (rs_tDBInput_5.wasNull()) {
								row8.dureemois = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 12) {
							row8.datenotification = null;
						} else {

							row8.datenotification = routines.system.JDBCUtil.getString(rs_tDBInput_5, 12, false);
						}
						if (colQtyInRs_tDBInput_5 < 13) {
							row8.montantcalcule = null;
						} else {

							row8.montantcalcule = rs_tDBInput_5.getFloat(13);
							if (rs_tDBInput_5.wasNull()) {
								row8.montantcalcule = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 14) {
							row8.libelledepartementacheteur = null;
						} else {

							row8.libelledepartementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_5, 14,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 15) {
							row8.codecpv_division = null;
						} else {

							row8.codecpv_division = rs_tDBInput_5.getInt(15);
							if (rs_tDBInput_5.wasNull()) {
								row8.codecpv_division = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 16) {
							row8.lieuexecutionnom = null;
						} else {

							row8.lieuexecutionnom = routines.system.JDBCUtil.getString(rs_tDBInput_5, 16, false);
						}
						if (colQtyInRs_tDBInput_5 < 17) {
							row8.libellearrondissementacheteur = null;
						} else {

							row8.libellearrondissementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_5, 17,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 18) {
							row8.populationcommuneetablissement = null;
						} else {

							row8.populationcommuneetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 18,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 19) {
							row8.codecommuneacheteur = null;
						} else {

							row8.codecommuneacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_5, 19, false);
						}
						if (colQtyInRs_tDBInput_5 < 20) {
							row8.superficiecommuneetablissement = null;
						} else {

							row8.superficiecommuneetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 20,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 21) {
							row8.nicetablissement = null;
						} else {

							row8.nicetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 21, false);
						}
						if (colQtyInRs_tDBInput_5 < 22) {
							row8.geoloccommuneetablissement___ = null;
						} else {

							row8.geoloccommuneetablissement___ = routines.system.JDBCUtil.getString(rs_tDBInput_5, 22,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 23) {
							row8.objetmarche = null;
						} else {

							row8.objetmarche = routines.system.JDBCUtil.getString(rs_tDBInput_5, 23, false);
						}
						if (colQtyInRs_tDBInput_5 < 24) {
							row8.nature = null;
						} else {

							row8.nature = routines.system.JDBCUtil.getString(rs_tDBInput_5, 24, false);
						}
						if (colQtyInRs_tDBInput_5 < 25) {
							row8.nombretitulairesurmarchepresume = null;
						} else {

							row8.nombretitulairesurmarchepresume = routines.system.JDBCUtil.getString(rs_tDBInput_5, 25,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 26) {
							row8.libelleregionetablissement = null;
						} else {

							row8.libelleregionetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 26,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 27) {
							row8.id = null;
						} else {

							row8.id = routines.system.JDBCUtil.getString(rs_tDBInput_5, 27, false);
						}
						if (colQtyInRs_tDBInput_5 < 28) {
							row8.source = null;
						} else {

							row8.source = routines.system.JDBCUtil.getString(rs_tDBInput_5, 28, false);
						}
						if (colQtyInRs_tDBInput_5 < 29) {
							row8.dureemoisestimee = null;
						} else {

							row8.dureemoisestimee = routines.system.JDBCUtil.getString(rs_tDBInput_5, 29, false);
						}
						if (colQtyInRs_tDBInput_5 < 30) {
							row8.nomacheteur = null;
						} else {

							row8.nomacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_5, 30, false);
						}
						if (colQtyInRs_tDBInput_5 < 31) {
							row8.referencecpv = null;
						} else {

							row8.referencecpv = routines.system.JDBCUtil.getString(rs_tDBInput_5, 31, false);
						}
						if (colQtyInRs_tDBInput_5 < 32) {
							row8.type = null;
						} else {

							row8.type = routines.system.JDBCUtil.getString(rs_tDBInput_5, 32, false);
						}
						if (colQtyInRs_tDBInput_5 < 33) {
							row8.sirenetablissement = null;
						} else {

							row8.sirenetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 33, false);
						}
						if (colQtyInRs_tDBInput_5 < 34) {
							row8.superficiecommuneacheteur = null;
						} else {

							row8.superficiecommuneacheteur = rs_tDBInput_5.getFloat(34);
							if (rs_tDBInput_5.wasNull()) {
								row8.superficiecommuneacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 35) {
							row8.codearrondissementacheteur = null;
						} else {

							row8.codearrondissementacheteur = rs_tDBInput_5.getInt(35);
							if (rs_tDBInput_5.wasNull()) {
								row8.codearrondissementacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 36) {
							row8.distanceacheteuretablissement = null;
						} else {

							row8.distanceacheteuretablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 36,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 37) {
							row8.communeetablissement = null;
						} else {

							row8.communeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 37, false);
						}
						if (colQtyInRs_tDBInput_5 < 38) {
							row8.montant = null;
						} else {

							row8.montant = routines.system.JDBCUtil.getString(rs_tDBInput_5, 38, false);
						}
						if (colQtyInRs_tDBInput_5 < 39) {
							row8.departementetablissement = null;
						} else {

							row8.departementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 39,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 40) {
							row8.datepublicationdonnees = null;
						} else {

							row8.datepublicationdonnees = routines.system.JDBCUtil.getString(rs_tDBInput_5, 40, false);
						}
						if (colQtyInRs_tDBInput_5 < 41) {
							row8.sirenacheteurvalide = null;
						} else {

							row8.sirenacheteurvalide = routines.system.JDBCUtil.getString(rs_tDBInput_5, 41, false);
						}
						if (colQtyInRs_tDBInput_5 < 42) {
							row8.moisnotification = null;
						} else {

							row8.moisnotification = routines.system.JDBCUtil.getString(rs_tDBInput_5, 42, false);
						}
						if (colQtyInRs_tDBInput_5 < 43) {
							row8.coderegionetablissement = null;
						} else {

							row8.coderegionetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 43, false);
						}
						if (colQtyInRs_tDBInput_5 < 44) {
							row8.formeprix = null;
						} else {

							row8.formeprix = routines.system.JDBCUtil.getString(rs_tDBInput_5, 44, false);
						}
						if (colQtyInRs_tDBInput_5 < 45) {
							row8.siretetablissementvalide = null;
						} else {

							row8.siretetablissementvalide = routines.system.JDBCUtil.getString(rs_tDBInput_5, 45,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 46) {
							row8.codedepartementexecution = null;
						} else {

							row8.codedepartementexecution = rs_tDBInput_5.getFloat(46);
							if (rs_tDBInput_5.wasNull()) {
								row8.codedepartementexecution = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 47) {
							row8.dist = null;
						} else {

							row8.dist = rs_tDBInput_5.getFloat(47);
							if (rs_tDBInput_5.wasNull()) {
								row8.dist = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 48) {
							row8.coderegionacheteur = null;
						} else {

							row8.coderegionacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_5, 48, false);
						}
						if (colQtyInRs_tDBInput_5 < 49) {
							row8.lieuexecutiontypecode = null;
						} else {

							row8.lieuexecutiontypecode = routines.system.JDBCUtil.getString(rs_tDBInput_5, 49, false);
						}
						if (colQtyInRs_tDBInput_5 < 50) {
							row8.codecommuneetablissement = null;
						} else {

							row8.codecommuneetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 50,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 51) {
							row8.populationcommuneacheteur = null;
						} else {

							row8.populationcommuneacheteur = rs_tDBInput_5.getFloat(51);
							if (rs_tDBInput_5.wasNull()) {
								row8.populationcommuneacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 52) {
							row8.idacheteur = null;
						} else {

							row8.idacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_5, 52, false);
						}
						if (colQtyInRs_tDBInput_5 < 53) {
							row8.codecpv_original = null;
						} else {

							row8.codecpv_original = routines.system.JDBCUtil.getString(rs_tDBInput_5, 53, false);
						}
						if (colQtyInRs_tDBInput_5 < 54) {
							row8.siretetablissement = null;
						} else {

							row8.siretetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 54, false);
						}
						if (colQtyInRs_tDBInput_5 < 55) {
							row8.libelledepartementetablissement = null;
						} else {

							row8.libelledepartementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 55,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 56) {
							row8.typeidentifiantetablissement = null;
						} else {

							row8.typeidentifiantetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 56,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 57) {
							row8.categorieetablissement = null;
						} else {

							row8.categorieetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 57, false);
						}
						if (colQtyInRs_tDBInput_5 < 58) {
							row8.coderegionexecution = null;
						} else {

							row8.coderegionexecution = rs_tDBInput_5.getFloat(58);
							if (rs_tDBInput_5.wasNull()) {
								row8.coderegionexecution = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 59) {
							row8.codepostaletablissement = null;
						} else {

							row8.codepostaletablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 59, false);
						}
						if (colQtyInRs_tDBInput_5 < 60) {
							row8.natureobjetmarche = null;
						} else {

							row8.natureobjetmarche = routines.system.JDBCUtil.getString(rs_tDBInput_5, 60, false);
						}
						if (colQtyInRs_tDBInput_5 < 61) {
							row8.dureemoiscalculee = null;
						} else {

							row8.dureemoiscalculee = rs_tDBInput_5.getFloat(61);
							if (rs_tDBInput_5.wasNull()) {
								row8.dureemoiscalculee = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 62) {
							row8.codetypeetablissement = null;
						} else {

							row8.codetypeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 62, false);
						}
						if (colQtyInRs_tDBInput_5 < 63) {
							row8.geoloccommuneacheteur___ = null;
						} else {

							row8.geoloccommuneacheteur___ = routines.system.JDBCUtil.getString(rs_tDBInput_5, 63,
									false);
						}
						if (colQtyInRs_tDBInput_5 < 64) {
							row8.codecpv = null;
						} else {

							row8.codecpv = routines.system.JDBCUtil.getString(rs_tDBInput_5, 64, false);
						}
						if (colQtyInRs_tDBInput_5 < 65) {
							row8.anneenotification = null;
						} else {

							row8.anneenotification = routines.system.JDBCUtil.getString(rs_tDBInput_5, 65, false);
						}
						if (colQtyInRs_tDBInput_5 < 66) {
							row8.libelleregionexecution = null;
						} else {

							row8.libelleregionexecution = routines.system.JDBCUtil.getString(rs_tDBInput_5, 66, false);
						}
						if (colQtyInRs_tDBInput_5 < 67) {
							row8.procedure = null;
						} else {

							row8.procedure = routines.system.JDBCUtil.getString(rs_tDBInput_5, 67, false);
						}
						if (colQtyInRs_tDBInput_5 < 68) {
							row8.libellearrondissementetablissement = null;
						} else {

							row8.libellearrondissementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5,
									68, false);
						}
						if (colQtyInRs_tDBInput_5 < 69) {
							row8.codearrondissementetablissement = null;
						} else {

							row8.codearrondissementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_5, 69,
									false);
						}

						/**
						 * [tDBInput_5 begin ] stop
						 */

						/**
						 * [tDBInput_5 main ] start
						 */

						currentComponent = "tDBInput_5";

						tos_count_tDBInput_5++;

						/**
						 * [tDBInput_5 main ] stop
						 */

						/**
						 * [tDBInput_5 process_data_begin ] start
						 */

						currentComponent = "tDBInput_5";

						/**
						 * [tDBInput_5 process_data_begin ] stop
						 */

						/**
						 * [tMap_5 main ] start
						 */

						currentComponent = "tMap_5";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row8"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_5 = false;
						boolean mainRowRejected_tMap_5 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_5__Struct Var = Var__tMap_5;
							Var.isSirenNull = DataProcessing.checkForNull(row8.sirenetablissement);
							Var.isDateNull = DataProcessing.checkForNull(row8.datenotification);
							Var.newDate = DataProcessing.replaceNullString(row8.datenotification);
							Var.newSiren = DataProcessing.replaceNullString(row8.sirenetablissement);// ###############################
							// ###############################
							// # Output tables

							fait_marche = null;

// # Output table : 'fait_marche'
							fait_marche_tmp.sirenetablissement = Var.newSiren;
							fait_marche_tmp.idacheteur = row8.idacheteur;
							fait_marche_tmp.datenotification = Var.newDate;
							fait_marche_tmp.idmarche = row8.idmarche;
							fait_marche_tmp.montantmarche = row8.montant;
							fait_marche_tmp.montantcalculemarche = row8.montantcalcule;
							fait_marche = fait_marche_tmp;
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_5 = false;

						tos_count_tMap_5++;

						/**
						 * [tMap_5 main ] stop
						 */

						/**
						 * [tMap_5 process_data_begin ] start
						 */

						currentComponent = "tMap_5";

						/**
						 * [tMap_5 process_data_begin ] stop
						 */
// Start of branch "fait_marche"
						if (fait_marche != null) {

							/**
							 * [tDBOutput_4 main ] start
							 */

							currentComponent = "tDBOutput_4";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "fait_marche"

								);
							}

							row9 = null;
							whetherReject_tDBOutput_4 = false;
							int updateFlag_tDBOutput_4 = 0;
							if (fait_marche.montantmarche == null) {
								pstmtUpdate_tDBOutput_4.setNull(1, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_4.setString(1, fait_marche.montantmarche);
							}

							if (fait_marche.montantcalculemarche == null) {
								pstmtUpdate_tDBOutput_4.setNull(2, java.sql.Types.FLOAT);
							} else {
								pstmtUpdate_tDBOutput_4.setFloat(2, fait_marche.montantcalculemarche);
							}

							if (fait_marche.sirenetablissement == null) {
								pstmtUpdate_tDBOutput_4.setNull(3 + count_tDBOutput_4, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_4.setString(3 + count_tDBOutput_4,
										fait_marche.sirenetablissement);
							}

							if (fait_marche.idacheteur == null) {
								pstmtUpdate_tDBOutput_4.setNull(4 + count_tDBOutput_4, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_4.setString(4 + count_tDBOutput_4, fait_marche.idacheteur);
							}

							if (fait_marche.datenotification == null) {
								pstmtUpdate_tDBOutput_4.setNull(5 + count_tDBOutput_4, java.sql.Types.VARCHAR);
							} else {
								pstmtUpdate_tDBOutput_4.setString(5 + count_tDBOutput_4, fait_marche.datenotification);
							}

							if (fait_marche.idmarche == null) {
								pstmtUpdate_tDBOutput_4.setNull(6 + count_tDBOutput_4, java.sql.Types.INTEGER);
							} else {
								pstmtUpdate_tDBOutput_4.setInt(6 + count_tDBOutput_4, fait_marche.idmarche);
							}

							try {

								updateFlag_tDBOutput_4 = pstmtUpdate_tDBOutput_4.executeUpdate();
								updatedCount_tDBOutput_4 = updatedCount_tDBOutput_4 + updateFlag_tDBOutput_4;
								rowsToCommitCount_tDBOutput_4 += updateFlag_tDBOutput_4;

								if (updateFlag_tDBOutput_4 == 0) {

									if (fait_marche.sirenetablissement == null) {
										pstmtInsert_tDBOutput_4.setNull(1, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_4.setString(1, fait_marche.sirenetablissement);
									}

									if (fait_marche.idacheteur == null) {
										pstmtInsert_tDBOutput_4.setNull(2, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_4.setString(2, fait_marche.idacheteur);
									}

									if (fait_marche.datenotification == null) {
										pstmtInsert_tDBOutput_4.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_4.setString(3, fait_marche.datenotification);
									}

									if (fait_marche.idmarche == null) {
										pstmtInsert_tDBOutput_4.setNull(4, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_4.setInt(4, fait_marche.idmarche);
									}

									if (fait_marche.montantmarche == null) {
										pstmtInsert_tDBOutput_4.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_4.setString(5, fait_marche.montantmarche);
									}

									if (fait_marche.montantcalculemarche == null) {
										pstmtInsert_tDBOutput_4.setNull(6, java.sql.Types.FLOAT);
									} else {
										pstmtInsert_tDBOutput_4.setFloat(6, fait_marche.montantcalculemarche);
									}

									int processedCount_tDBOutput_4 = pstmtInsert_tDBOutput_4.executeUpdate();
									insertedCount_tDBOutput_4 += processedCount_tDBOutput_4;
									rowsToCommitCount_tDBOutput_4 += processedCount_tDBOutput_4;
									nb_line_tDBOutput_4++;

								} else {
									nb_line_tDBOutput_4++;

								}
							} catch (java.lang.Exception e) {
								globalMap.put("tDBOutput_4_ERROR_MESSAGE", e.getMessage());

								whetherReject_tDBOutput_4 = true;
								nb_line_tDBOutput_4++;
								System.err.print(e.getMessage());
							}
							if (!whetherReject_tDBOutput_4) {
								row9 = new row9Struct();
								row9.sirenetablissement = fait_marche.sirenetablissement;
								row9.idacheteur = fait_marche.idacheteur;
								row9.datenotification = fait_marche.datenotification;
								row9.idmarche = fait_marche.idmarche;
								row9.montantmarche = fait_marche.montantmarche;
								row9.montantcalculemarche = fait_marche.montantcalculemarche;
							}

							tos_count_tDBOutput_4++;

							/**
							 * [tDBOutput_4 main ] stop
							 */

							/**
							 * [tDBOutput_4 process_data_begin ] start
							 */

							currentComponent = "tDBOutput_4";

							/**
							 * [tDBOutput_4 process_data_begin ] stop
							 */
// Start of branch "row9"
							if (row9 != null) {

								/**
								 * [tDBCommit_4 main ] start
								 */

								currentComponent = "tDBCommit_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row9"

									);
								}

								java.sql.Connection conn_tDBCommit_4 = (java.sql.Connection) globalMap
										.get("conn_tDBConnection_1");
								if (conn_tDBCommit_4 != null && !conn_tDBCommit_4.isClosed()) {

									conn_tDBCommit_4.commit();

								}

								tos_count_tDBCommit_4++;

								/**
								 * [tDBCommit_4 main ] stop
								 */

								/**
								 * [tDBCommit_4 process_data_begin ] start
								 */

								currentComponent = "tDBCommit_4";

								/**
								 * [tDBCommit_4 process_data_begin ] stop
								 */

								/**
								 * [tDBCommit_4 process_data_end ] start
								 */

								currentComponent = "tDBCommit_4";

								/**
								 * [tDBCommit_4 process_data_end ] stop
								 */

							} // End of branch "row9"

							/**
							 * [tDBOutput_4 process_data_end ] start
							 */

							currentComponent = "tDBOutput_4";

							/**
							 * [tDBOutput_4 process_data_end ] stop
							 */

						} // End of branch "fait_marche"

						/**
						 * [tMap_5 process_data_end ] start
						 */

						currentComponent = "tMap_5";

						/**
						 * [tMap_5 process_data_end ] stop
						 */

						/**
						 * [tDBInput_5 process_data_end ] start
						 */

						currentComponent = "tDBInput_5";

						/**
						 * [tDBInput_5 process_data_end ] stop
						 */

						/**
						 * [tDBInput_5 end ] start
						 */

						currentComponent = "tDBInput_5";

					}
				} finally {
					if (rs_tDBInput_5 != null) {
						rs_tDBInput_5.close();
					}
					if (stmt_tDBInput_5 != null) {
						stmt_tDBInput_5.close();
					}
				}
				globalMap.put("tDBInput_5_NB_LINE", nb_line_tDBInput_5);

				ok_Hash.put("tDBInput_5", true);
				end_Hash.put("tDBInput_5", System.currentTimeMillis());

				/**
				 * [tDBInput_5 end ] stop
				 */

				/**
				 * [tMap_5 end ] start
				 */

				currentComponent = "tMap_5";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row8");
				}

				ok_Hash.put("tMap_5", true);
				end_Hash.put("tMap_5", System.currentTimeMillis());

				/**
				 * [tMap_5 end ] stop
				 */

				/**
				 * [tDBOutput_4 end ] start
				 */

				currentComponent = "tDBOutput_4";

				if (pstmtUpdate_tDBOutput_4 != null) {
					pstmtUpdate_tDBOutput_4.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_4");
				}
				if (pstmtInsert_tDBOutput_4 != null) {
					pstmtInsert_tDBOutput_4.close();
					resourceMap.remove("pstmtInsert_tDBOutput_4");
				}
				resourceMap.put("statementClosed_tDBOutput_4", true);

				nb_line_deleted_tDBOutput_4 = nb_line_deleted_tDBOutput_4 + deletedCount_tDBOutput_4;
				nb_line_update_tDBOutput_4 = nb_line_update_tDBOutput_4 + updatedCount_tDBOutput_4;
				nb_line_inserted_tDBOutput_4 = nb_line_inserted_tDBOutput_4 + insertedCount_tDBOutput_4;
				nb_line_rejected_tDBOutput_4 = nb_line_rejected_tDBOutput_4 + rejectedCount_tDBOutput_4;

				globalMap.put("tDBOutput_4_NB_LINE", nb_line_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_UPDATED", nb_line_update_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_DELETED", nb_line_deleted_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_4);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "fait_marche");
				}

				ok_Hash.put("tDBOutput_4", true);
				end_Hash.put("tDBOutput_4", System.currentTimeMillis());

				/**
				 * [tDBOutput_4 end ] stop
				 */

				/**
				 * [tDBCommit_4 end ] start
				 */

				currentComponent = "tDBCommit_4";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row9");
				}

				ok_Hash.put("tDBCommit_4", true);
				end_Hash.put("tDBCommit_4", System.currentTimeMillis());

				/**
				 * [tDBCommit_4 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tDBInput_5:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk4", 0, "ok");
			}

			tWarn_6Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_5 finally ] start
				 */

				currentComponent = "tDBInput_5";

				/**
				 * [tDBInput_5 finally ] stop
				 */

				/**
				 * [tMap_5 finally ] start
				 */

				currentComponent = "tMap_5";

				/**
				 * [tMap_5 finally ] stop
				 */

				/**
				 * [tDBOutput_4 finally ] start
				 */

				currentComponent = "tDBOutput_4";

				if (resourceMap.get("statementClosed_tDBOutput_4") == null) {
					java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_4 = null;
					if ((pstmtUpdateToClose_tDBOutput_4 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmtUpdate_tDBOutput_4")) != null) {
						pstmtUpdateToClose_tDBOutput_4.close();
					}
					java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_4 = null;
					if ((pstmtInsertToClose_tDBOutput_4 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmtInsert_tDBOutput_4")) != null) {
						pstmtInsertToClose_tDBOutput_4.close();
					}
				}

				/**
				 * [tDBOutput_4 finally ] stop
				 */

				/**
				 * [tDBCommit_4 finally ] start
				 */

				currentComponent = "tDBCommit_4";

				/**
				 * [tDBCommit_4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 1);
	}

	public void tWarn_6Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tWarn_6_SUBPROCESS_STATE", 0);

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
				 * [tWarn_6 begin ] start
				 */

				ok_Hash.put("tWarn_6", false);
				start_Hash.put("tWarn_6", System.currentTimeMillis());

				currentComponent = "tWarn_6";

				int tos_count_tWarn_6 = 0;

				/**
				 * [tWarn_6 begin ] stop
				 */

				/**
				 * [tWarn_6 main ] start
				 */

				currentComponent = "tWarn_6";

				try {

					resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_6", "", Thread.currentThread().getId() + "", "WARN",
							"", " --------------  CITYVIZ : Data warehouse loaded with success -------------- \n ", "",
							"");
					globalMap.put("tWarn_6_WARN_MESSAGES",
							" --------------  CITYVIZ : Data warehouse loaded with success -------------- \n ");
					globalMap.put("tWarn_6_WARN_PRIORITY", 4);
					globalMap.put("tWarn_6_WARN_CODE", 42);

				} catch (Exception e_tWarn_6) {
					globalMap.put("tWarn_6_ERROR_MESSAGE", e_tWarn_6.getMessage());
					logIgnoredError(
							String.format("tWarn_6 - tWarn failed to log message due to internal error: %s", e_tWarn_6),
							e_tWarn_6);
				}

				tos_count_tWarn_6++;

				/**
				 * [tWarn_6 main ] stop
				 */

				/**
				 * [tWarn_6 process_data_begin ] start
				 */

				currentComponent = "tWarn_6";

				/**
				 * [tWarn_6 process_data_begin ] stop
				 */

				/**
				 * [tWarn_6 process_data_end ] start
				 */

				currentComponent = "tWarn_6";

				/**
				 * [tWarn_6 process_data_end ] stop
				 */

				/**
				 * [tWarn_6 end ] start
				 */

				currentComponent = "tWarn_6";

				ok_Hash.put("tWarn_6", true);
				end_Hash.put("tWarn_6", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk9", 0, "ok");
				}
				tDBClose_3Process(globalMap);

				/**
				 * [tWarn_6 end ] stop
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
				 * [tWarn_6 finally ] start
				 */

				currentComponent = "tWarn_6";

				/**
				 * [tWarn_6 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tWarn_6_SUBPROCESS_STATE", 1);
	}

	public void tDBClose_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBClose_3_SUBPROCESS_STATE", 0);

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
				 * [tDBClose_3 begin ] start
				 */

				ok_Hash.put("tDBClose_3", false);
				start_Hash.put("tDBClose_3", System.currentTimeMillis());

				currentComponent = "tDBClose_3";

				int tos_count_tDBClose_3 = 0;

				/**
				 * [tDBClose_3 begin ] stop
				 */

				/**
				 * [tDBClose_3 main ] start
				 */

				currentComponent = "tDBClose_3";

				java.sql.Connection conn_tDBClose_3 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");
				if (conn_tDBClose_3 != null && !conn_tDBClose_3.isClosed()) {
					conn_tDBClose_3.close();
				}

				tos_count_tDBClose_3++;

				/**
				 * [tDBClose_3 main ] stop
				 */

				/**
				 * [tDBClose_3 process_data_begin ] start
				 */

				currentComponent = "tDBClose_3";

				/**
				 * [tDBClose_3 process_data_begin ] stop
				 */

				/**
				 * [tDBClose_3 process_data_end ] start
				 */

				currentComponent = "tDBClose_3";

				/**
				 * [tDBClose_3 process_data_end ] stop
				 */

				/**
				 * [tDBClose_3 end ] start
				 */

				currentComponent = "tDBClose_3";

				ok_Hash.put("tDBClose_3", true);
				end_Hash.put("tDBClose_3", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk10", 0, "ok");
				}
				tDBClose_4Process(globalMap);

				/**
				 * [tDBClose_3 end ] stop
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
				 * [tDBClose_3 finally ] start
				 */

				currentComponent = "tDBClose_3";

				/**
				 * [tDBClose_3 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBClose_3_SUBPROCESS_STATE", 1);
	}

	public void tDBClose_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBClose_4_SUBPROCESS_STATE", 0);

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
				 * [tDBClose_4 begin ] start
				 */

				ok_Hash.put("tDBClose_4", false);
				start_Hash.put("tDBClose_4", System.currentTimeMillis());

				currentComponent = "tDBClose_4";

				int tos_count_tDBClose_4 = 0;

				/**
				 * [tDBClose_4 begin ] stop
				 */

				/**
				 * [tDBClose_4 main ] start
				 */

				currentComponent = "tDBClose_4";

				java.sql.Connection conn_tDBClose_4 = (java.sql.Connection) globalMap.get("conn_tDBConnection_2");
				if (conn_tDBClose_4 != null && !conn_tDBClose_4.isClosed()) {
					conn_tDBClose_4.close();
				}

				tos_count_tDBClose_4++;

				/**
				 * [tDBClose_4 main ] stop
				 */

				/**
				 * [tDBClose_4 process_data_begin ] start
				 */

				currentComponent = "tDBClose_4";

				/**
				 * [tDBClose_4 process_data_begin ] stop
				 */

				/**
				 * [tDBClose_4 process_data_end ] start
				 */

				currentComponent = "tDBClose_4";

				/**
				 * [tDBClose_4 process_data_end ] stop
				 */

				/**
				 * [tDBClose_4 end ] start
				 */

				currentComponent = "tDBClose_4";

				ok_Hash.put("tDBClose_4", true);
				end_Hash.put("tDBClose_4", System.currentTimeMillis());

				/**
				 * [tDBClose_4 end ] stop
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
				 * [tDBClose_4 finally ] start
				 */

				currentComponent = "tDBClose_4";

				/**
				 * [tDBClose_4 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBClose_4_SUBPROCESS_STATE", 1);
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

				String dbProperties_tDBConnection_1 = context.cityviz_dwh_AdditionalParams;
				String url_tDBConnection_1 = "jdbc:postgresql://" + context.cityviz_dwh_Server + ":"
						+ context.cityviz_dwh_Port + "/" + context.cityviz_dwh_Database;

				if (dbProperties_tDBConnection_1 != null && !"".equals(dbProperties_tDBConnection_1.trim())) {
					url_tDBConnection_1 = url_tDBConnection_1 + "?" + dbProperties_tDBConnection_1;
				}
				String dbUser_tDBConnection_1 = context.cityviz_dwh_Login;

				final String decryptedPassword_tDBConnection_1 = context.cityviz_dwh_Password;
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

				globalMap.put("schema_" + "tDBConnection_1", context.cityviz_dwh_Schema);

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

				String dbProperties_tDBConnection_2 = context.cityviz_staging_AdditionalParams;
				String url_tDBConnection_2 = "jdbc:postgresql://" + context.cityviz_staging_Server + ":"
						+ context.cityviz_staging_Port + "/" + context.cityviz_staging_Database;

				if (dbProperties_tDBConnection_2 != null && !"".equals(dbProperties_tDBConnection_2.trim())) {
					url_tDBConnection_2 = url_tDBConnection_2 + "?" + dbProperties_tDBConnection_2;
				}
				String dbUser_tDBConnection_2 = context.cityviz_staging_Login;

				final String decryptedPassword_tDBConnection_2 = context.cityviz_staging_Password;
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

				globalMap.put("schema_" + "tDBConnection_2", context.cityviz_staging_Schema);

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
					runStat.updateStatOnConnection("OnComponentOk2", 0, "ok");
				}
				tCreateTable_1Process(globalMap);

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

	public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

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
			final row2Struct other = (row2Struct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.id = this.id;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.id = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.id = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

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

			}

			finally {
			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

			}

			finally {
			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

			} finally {
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

			} finally {
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
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

				row2Struct row2 = new row2Struct();

				/**
				 * [tAdvancedHash_row2 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row2", false);
				start_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tAdvancedHash_row2 = 0;

				// connection name:row2
				// source node:tDBInput_2 - inputs:() outputs:(row2,row2) | target
				// node:tAdvancedHash_row2 - inputs:(row2) outputs:()
				// linked node: tMap_2 - inputs:(dim_acheteur,row2)
				// outputs:(acheteur,doublons_acheteur)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row2Struct>getLookup(matchingModeEnum_row2);

				globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);

				/**
				 * [tAdvancedHash_row2 begin ] stop
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

				String dbquery_tDBInput_2 = "SELECT \"idacheteur\" FROM \"dim_acheteur\"";

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
							row2.id = null;
						} else {

							row2.id = routines.system.JDBCUtil.getString(rs_tDBInput_2, 1, false);
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
						 * [tAdvancedHash_row2 main ] start
						 */

						currentComponent = "tAdvancedHash_row2";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row2"

							);
						}

						row2Struct row2_HashRow = new row2Struct();

						row2_HashRow.id = row2.id;

						tHash_Lookup_row2.put(row2_HashRow);

						tos_count_tAdvancedHash_row2++;

						/**
						 * [tAdvancedHash_row2 main ] stop
						 */

						/**
						 * [tAdvancedHash_row2 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row2";

						/**
						 * [tAdvancedHash_row2 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row2 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row2";

						/**
						 * [tAdvancedHash_row2 process_data_end ] stop
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
				 * [tAdvancedHash_row2 end ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				tHash_Lookup_row2.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tAdvancedHash_row2", true);
				end_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row2 end ] stop
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
				 * [tAdvancedHash_row2 finally ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				/**
				 * [tAdvancedHash_row2 finally ] stop
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

	public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer id;

		public Integer getId() {
			return this.id;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

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
			final row3Struct other = (row3Struct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.id = this.id;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.id = this.id;

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

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.id, dos);

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

			}

			finally {
			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

			}

			finally {
			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

			} finally {
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

			} finally {
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + String.valueOf(id));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
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

	public void tDBInput_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 0);

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

				row3Struct row3 = new row3Struct();

				/**
				 * [tAdvancedHash_row3 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row3", false);
				start_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tAdvancedHash_row3 = 0;

				// connection name:row3
				// source node:tDBInput_3 - inputs:() outputs:(row3,row3) | target
				// node:tAdvancedHash_row3 - inputs:(row3) outputs:()
				// linked node: tMap_3 - inputs:(dim_marche,row3)
				// outputs:(marche,doublon_marche)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row3Struct>getLookup(matchingModeEnum_row3);

				globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);

				/**
				 * [tAdvancedHash_row3 begin ] stop
				 */

				/**
				 * [tDBInput_3 begin ] start
				 */

				ok_Hash.put("tDBInput_3", false);
				start_Hash.put("tDBInput_3", System.currentTimeMillis());

				currentComponent = "tDBInput_3";

				int tos_count_tDBInput_3 = 0;

				int nb_line_tDBInput_3 = 0;
				java.sql.Connection conn_tDBInput_3 = null;
				conn_tDBInput_3 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				java.sql.Statement stmt_tDBInput_3 = conn_tDBInput_3.createStatement();

				String dbquery_tDBInput_3 = "SELECT \"idmarche\" FROM \"dim_marche\"";

				globalMap.put("tDBInput_3_QUERY", dbquery_tDBInput_3);
				java.sql.ResultSet rs_tDBInput_3 = null;

				try {
					rs_tDBInput_3 = stmt_tDBInput_3.executeQuery(dbquery_tDBInput_3);
					java.sql.ResultSetMetaData rsmd_tDBInput_3 = rs_tDBInput_3.getMetaData();
					int colQtyInRs_tDBInput_3 = rsmd_tDBInput_3.getColumnCount();

					String tmpContent_tDBInput_3 = null;

					while (rs_tDBInput_3.next()) {
						nb_line_tDBInput_3++;

						if (colQtyInRs_tDBInput_3 < 1) {
							row3.id = null;
						} else {

							row3.id = rs_tDBInput_3.getInt(1);
							if (rs_tDBInput_3.wasNull()) {
								row3.id = null;
							}
						}

						/**
						 * [tDBInput_3 begin ] stop
						 */

						/**
						 * [tDBInput_3 main ] start
						 */

						currentComponent = "tDBInput_3";

						tos_count_tDBInput_3++;

						/**
						 * [tDBInput_3 main ] stop
						 */

						/**
						 * [tDBInput_3 process_data_begin ] start
						 */

						currentComponent = "tDBInput_3";

						/**
						 * [tDBInput_3 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row3 main ] start
						 */

						currentComponent = "tAdvancedHash_row3";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row3"

							);
						}

						row3Struct row3_HashRow = new row3Struct();

						row3_HashRow.id = row3.id;

						tHash_Lookup_row3.put(row3_HashRow);

						tos_count_tAdvancedHash_row3++;

						/**
						 * [tAdvancedHash_row3 main ] stop
						 */

						/**
						 * [tAdvancedHash_row3 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row3";

						/**
						 * [tAdvancedHash_row3 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row3 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row3";

						/**
						 * [tAdvancedHash_row3 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 process_data_end ] start
						 */

						currentComponent = "tDBInput_3";

						/**
						 * [tDBInput_3 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 end ] start
						 */

						currentComponent = "tDBInput_3";

					}
				} finally {
					if (rs_tDBInput_3 != null) {
						rs_tDBInput_3.close();
					}
					if (stmt_tDBInput_3 != null) {
						stmt_tDBInput_3.close();
					}
				}
				globalMap.put("tDBInput_3_NB_LINE", nb_line_tDBInput_3);

				ok_Hash.put("tDBInput_3", true);
				end_Hash.put("tDBInput_3", System.currentTimeMillis());

				/**
				 * [tDBInput_3 end ] stop
				 */

				/**
				 * [tAdvancedHash_row3 end ] start
				 */

				currentComponent = "tAdvancedHash_row3";

				tHash_Lookup_row3.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tAdvancedHash_row3", true);
				end_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row3 end ] stop
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
				 * [tDBInput_3 finally ] start
				 */

				currentComponent = "tDBInput_3";

				/**
				 * [tDBInput_3 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row3 finally ] start
				 */

				currentComponent = "tAdvancedHash_row3";

				/**
				 * [tAdvancedHash_row3 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 1);
	}

	public static class row7Struct implements routines.system.IPersistableComparableLookupRow<row7Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_loading_dwh = new byte[0];
		static byte[] commonByteArray_CITYVIZ_loading_dwh = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String siren;

		public String getSiren() {
			return this.siren;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.siren == null) ? 0 : this.siren.hashCode());

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
			final row7Struct other = (row7Struct) obj;

			if (this.siren == null) {
				if (other.siren != null)
					return false;

			} else if (!this.siren.equals(other.siren))

				return false;

			return true;
		}

		public void copyDataTo(row7Struct other) {

			other.siren = this.siren;

		}

		public void copyKeysDataTo(row7Struct other) {

			other.siren = this.siren;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_loading_dwh.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_loading_dwh.length == 0) {
						commonByteArray_CITYVIZ_loading_dwh = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_loading_dwh = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_loading_dwh, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_loading_dwh, 0, length, utf8Charset);
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

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.siren = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_loading_dwh) {

				try {

					int length = 0;

					this.siren = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.siren, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.siren, dos);

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

			}

			finally {
			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

			}

			finally {
			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

			} finally {
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

			} finally {
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("siren=" + siren);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.siren, other.siren);
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

	public void tDBInput_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_4_SUBPROCESS_STATE", 0);

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

				row7Struct row7 = new row7Struct();

				/**
				 * [tAdvancedHash_row7 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row7", false);
				start_Hash.put("tAdvancedHash_row7", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row7";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row7");
				}

				int tos_count_tAdvancedHash_row7 = 0;

				// connection name:row7
				// source node:tDBInput_4 - inputs:() outputs:(row7,row7) | target
				// node:tAdvancedHash_row7 - inputs:(row7) outputs:()
				// linked node: tMap_4 - inputs:(dim_etablissement,row7)
				// outputs:(etablissement,doublon_etablissement,siren_null)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row7 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row7Struct>getLookup(matchingModeEnum_row7);

				globalMap.put("tHash_Lookup_row7", tHash_Lookup_row7);

				/**
				 * [tAdvancedHash_row7 begin ] stop
				 */

				/**
				 * [tDBInput_4 begin ] start
				 */

				ok_Hash.put("tDBInput_4", false);
				start_Hash.put("tDBInput_4", System.currentTimeMillis());

				currentComponent = "tDBInput_4";

				int tos_count_tDBInput_4 = 0;

				int nb_line_tDBInput_4 = 0;
				java.sql.Connection conn_tDBInput_4 = null;
				conn_tDBInput_4 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				java.sql.Statement stmt_tDBInput_4 = conn_tDBInput_4.createStatement();

				String dbquery_tDBInput_4 = "SELECT \"sirenetablissement\" FROM \"dim_etablissement\"";

				globalMap.put("tDBInput_4_QUERY", dbquery_tDBInput_4);
				java.sql.ResultSet rs_tDBInput_4 = null;

				try {
					rs_tDBInput_4 = stmt_tDBInput_4.executeQuery(dbquery_tDBInput_4);
					java.sql.ResultSetMetaData rsmd_tDBInput_4 = rs_tDBInput_4.getMetaData();
					int colQtyInRs_tDBInput_4 = rsmd_tDBInput_4.getColumnCount();

					String tmpContent_tDBInput_4 = null;

					while (rs_tDBInput_4.next()) {
						nb_line_tDBInput_4++;

						if (colQtyInRs_tDBInput_4 < 1) {
							row7.siren = null;
						} else {

							row7.siren = routines.system.JDBCUtil.getString(rs_tDBInput_4, 1, false);
						}

						/**
						 * [tDBInput_4 begin ] stop
						 */

						/**
						 * [tDBInput_4 main ] start
						 */

						currentComponent = "tDBInput_4";

						tos_count_tDBInput_4++;

						/**
						 * [tDBInput_4 main ] stop
						 */

						/**
						 * [tDBInput_4 process_data_begin ] start
						 */

						currentComponent = "tDBInput_4";

						/**
						 * [tDBInput_4 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row7 main ] start
						 */

						currentComponent = "tAdvancedHash_row7";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row7"

							);
						}

						row7Struct row7_HashRow = new row7Struct();

						row7_HashRow.siren = row7.siren;

						tHash_Lookup_row7.put(row7_HashRow);

						tos_count_tAdvancedHash_row7++;

						/**
						 * [tAdvancedHash_row7 main ] stop
						 */

						/**
						 * [tAdvancedHash_row7 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row7";

						/**
						 * [tAdvancedHash_row7 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row7 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row7";

						/**
						 * [tAdvancedHash_row7 process_data_end ] stop
						 */

						/**
						 * [tDBInput_4 process_data_end ] start
						 */

						currentComponent = "tDBInput_4";

						/**
						 * [tDBInput_4 process_data_end ] stop
						 */

						/**
						 * [tDBInput_4 end ] start
						 */

						currentComponent = "tDBInput_4";

					}
				} finally {
					if (rs_tDBInput_4 != null) {
						rs_tDBInput_4.close();
					}
					if (stmt_tDBInput_4 != null) {
						stmt_tDBInput_4.close();
					}
				}
				globalMap.put("tDBInput_4_NB_LINE", nb_line_tDBInput_4);

				ok_Hash.put("tDBInput_4", true);
				end_Hash.put("tDBInput_4", System.currentTimeMillis());

				/**
				 * [tDBInput_4 end ] stop
				 */

				/**
				 * [tAdvancedHash_row7 end ] start
				 */

				currentComponent = "tAdvancedHash_row7";

				tHash_Lookup_row7.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row7");
				}

				ok_Hash.put("tAdvancedHash_row7", true);
				end_Hash.put("tAdvancedHash_row7", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row7 end ] stop
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
				 * [tDBInput_4 finally ] start
				 */

				currentComponent = "tDBInput_4";

				/**
				 * [tDBInput_4 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row7 finally ] start
				 */

				currentComponent = "tAdvancedHash_row7";

				/**
				 * [tAdvancedHash_row7 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_4_SUBPROCESS_STATE", 1);
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
		final loading_dwh loading_dwhClass = new loading_dwh();

		int exitCode = loading_dwhClass.runJobInTOS(args);

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
			java.io.InputStream inContext = loading_dwh.class.getClassLoader()
					.getResourceAsStream("cityviz/loading_dwh_0_2/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = loading_dwh.class.getClassLoader()
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
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : loading_dwh");
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
 * 603442 characters generated by Talend Open Studio for Data Integration on the
 * 6 mars 2022 à 16:28:31 CET
 ************************************************************************************************/