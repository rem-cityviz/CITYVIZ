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

package cityviz.alim_dwh_1_0;

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
 * Job: alim_DWH Purpose: Alimentation du DWH depuis la base de staging<br>
 * Description: Process établis pour les tables de dimensions : validé pour une
 * seule table ("DIM_etablissement") donc reste à faire les deux autres tables
 * de dimension. Inclure les transformations de données listées par Thomas.
 * 
 * Faire le chargement de la table de fait. <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class alim_DWH implements TalendJob {

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

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "1.0";
	private final String jobName = "alim_DWH";
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
					alim_DWH.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(alim_DWH.this, new Object[] { e, currentComponent, globalMap });
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

	public void tDBClose_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBClose_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBClose_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBClose_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBConnection_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBConnection_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tWarn_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tWarn_5_onSubJobError(exception, errorComponent, globalMap);
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

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_5_error(Exception exception, String errorComponent,
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

	public void tWarn_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
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

	public void tDBCommit_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tWarn_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
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

	public void tDBOutput_3_error(Exception exception, String errorComponent,
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

	public void tWarn_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBCommit_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tWarn_7_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tWarn_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tWarn_6_onSubJobError(exception, errorComponent, globalMap);
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

	public void tDBInput_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogCatcher_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tLogCatcher_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
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

	public void tAdvancedHash_row2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBClose_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBClose_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBConnection_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tWarn_5_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_4_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tWarn_6_onSubJobError(Exception exception, String errorComponent,
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

	public void tDBInput_5_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tLogCatcher_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBClose_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBClose_1_SUBPROCESS_STATE", 0);

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
				 * [tDBClose_1 begin ] start
				 */

				ok_Hash.put("tDBClose_1", false);
				start_Hash.put("tDBClose_1", System.currentTimeMillis());

				currentComponent = "tDBClose_1";

				int tos_count_tDBClose_1 = 0;

				/**
				 * [tDBClose_1 begin ] stop
				 */

				/**
				 * [tDBClose_1 main ] start
				 */

				currentComponent = "tDBClose_1";

				java.sql.Connection conn_tDBClose_1 = (java.sql.Connection) globalMap.get("conn_tDBConnection_2");
				if (conn_tDBClose_1 != null && !conn_tDBClose_1.isClosed()) {
					conn_tDBClose_1.close();
				}

				tos_count_tDBClose_1++;

				/**
				 * [tDBClose_1 main ] stop
				 */

				/**
				 * [tDBClose_1 process_data_begin ] start
				 */

				currentComponent = "tDBClose_1";

				/**
				 * [tDBClose_1 process_data_begin ] stop
				 */

				/**
				 * [tDBClose_1 process_data_end ] start
				 */

				currentComponent = "tDBClose_1";

				/**
				 * [tDBClose_1 process_data_end ] stop
				 */

				/**
				 * [tDBClose_1 end ] start
				 */

				currentComponent = "tDBClose_1";

				ok_Hash.put("tDBClose_1", true);
				end_Hash.put("tDBClose_1", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk5", 0, "ok");
				}
				tDBClose_2Process(globalMap);

				/**
				 * [tDBClose_1 end ] stop
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
				 * [tDBClose_1 finally ] start
				 */

				currentComponent = "tDBClose_1";

				/**
				 * [tDBClose_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBClose_1_SUBPROCESS_STATE", 1);
	}

	public void tDBClose_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBClose_2_SUBPROCESS_STATE", 0);

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
				 * [tDBClose_2 begin ] start
				 */

				ok_Hash.put("tDBClose_2", false);
				start_Hash.put("tDBClose_2", System.currentTimeMillis());

				currentComponent = "tDBClose_2";

				int tos_count_tDBClose_2 = 0;

				/**
				 * [tDBClose_2 begin ] stop
				 */

				/**
				 * [tDBClose_2 main ] start
				 */

				currentComponent = "tDBClose_2";

				java.sql.Connection conn_tDBClose_2 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");
				if (conn_tDBClose_2 != null && !conn_tDBClose_2.isClosed()) {
					conn_tDBClose_2.close();
				}

				tos_count_tDBClose_2++;

				/**
				 * [tDBClose_2 main ] stop
				 */

				/**
				 * [tDBClose_2 process_data_begin ] start
				 */

				currentComponent = "tDBClose_2";

				/**
				 * [tDBClose_2 process_data_begin ] stop
				 */

				/**
				 * [tDBClose_2 process_data_end ] start
				 */

				currentComponent = "tDBClose_2";

				/**
				 * [tDBClose_2 process_data_end ] stop
				 */

				/**
				 * [tDBClose_2 end ] start
				 */

				currentComponent = "tDBClose_2";

				ok_Hash.put("tDBClose_2", true);
				end_Hash.put("tDBClose_2", System.currentTimeMillis());

				/**
				 * [tDBClose_2 end ] stop
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
				 * [tDBClose_2 finally ] start
				 */

				currentComponent = "tDBClose_2";

				/**
				 * [tDBClose_2 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBClose_2_SUBPROCESS_STATE", 1);
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

				String dbProperties_tDBConnection_1 = "";
				String url_tDBConnection_1 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "stagingCV";

				if (dbProperties_tDBConnection_1 != null && !"".equals(dbProperties_tDBConnection_1.trim())) {
					url_tDBConnection_1 = url_tDBConnection_1 + "?" + dbProperties_tDBConnection_1;
				}
				String dbUser_tDBConnection_1 = "postgres";

				final String decryptedPassword_tDBConnection_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:as6Wd6BfNxKV+uQaXirhGeeLeJNZpOubdKYi0twWxg0=");
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

				globalMap.put("schema_" + "tDBConnection_1", "");

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
				tWarn_5Process(globalMap);

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

	public void tWarn_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tWarn_5_SUBPROCESS_STATE", 0);

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
				 * [tWarn_5 begin ] start
				 */

				ok_Hash.put("tWarn_5", false);
				start_Hash.put("tWarn_5", System.currentTimeMillis());

				currentComponent = "tWarn_5";

				int tos_count_tWarn_5 = 0;

				/**
				 * [tWarn_5 begin ] stop
				 */

				/**
				 * [tWarn_5 main ] start
				 */

				currentComponent = "tWarn_5";

				try {

					resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_5", "", Thread.currentThread().getId() + "", "WARN",
							"", " --------------  CITYVIZ : Start loading data warehouse -------------- ", "", "");
					tLogCatcher_1.addMessage("tWarn", "tWarn_5", 4,
							" --------------  CITYVIZ : Start loading data warehouse -------------- ", 42);
					tLogCatcher_1Process(globalMap);
					globalMap.put("tWarn_5_WARN_MESSAGES",
							" --------------  CITYVIZ : Start loading data warehouse -------------- ");
					globalMap.put("tWarn_5_WARN_PRIORITY", 4);
					globalMap.put("tWarn_5_WARN_CODE", 42);

				} catch (Exception e_tWarn_5) {
					globalMap.put("tWarn_5_ERROR_MESSAGE", e_tWarn_5.getMessage());
					logIgnoredError(
							String.format("tWarn_5 - tWarn failed to log message due to internal error: %s", e_tWarn_5),
							e_tWarn_5);
				}

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
				 * [tWarn_5 process_data_end ] start
				 */

				currentComponent = "tWarn_5";

				/**
				 * [tWarn_5 process_data_end ] stop
				 */

				/**
				 * [tWarn_5 end ] start
				 */

				currentComponent = "tWarn_5";

				ok_Hash.put("tWarn_5", true);
				end_Hash.put("tWarn_5", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk3", 0, "ok");
				}
				tDBInput_1Process(globalMap);

				/**
				 * [tWarn_5 end ] stop
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
				 * [tWarn_5 finally ] start
				 */

				currentComponent = "tWarn_5";

				/**
				 * [tWarn_5 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tWarn_5_SUBPROCESS_STATE", 1);
	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public Boolean siretetablissementvalide;

		public Boolean getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
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
			final row3Struct other = (row3Struct) obj;

			if (this.sirenetablissement == null) {
				if (other.sirenetablissement != null)
					return false;

			} else if (!this.sirenetablissement.equals(other.sirenetablissement))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.siretetablissement = this.siretetablissement;
			other.siretetablissementvalide = this.siretetablissementvalide;
			other.sirenetablissement = this.sirenetablissement;
			other.categorieetablissement = this.categorieetablissement;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;
			other.codecommuneetablissement = this.codecommuneetablissement;
			other.communeetablissement = this.communeetablissement;
			other.departementetablissement = this.departementetablissement;
			other.libelledepartementetablissement = this.libelledepartementetablissement;
			other.coderegionetablissement = this.coderegionetablissement;
			other.libelleregionetablissement = this.libelleregionetablissement;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.sirenetablissement = this.sirenetablissement;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.siretetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.siretetablissementvalide = null;
					} else {
						this.siretetablissementvalide = dis.readBoolean();
					}

					this.sirenetablissement = readString(dis);

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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.siretetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.siretetablissementvalide = null;
					} else {
						this.siretetablissementvalide = dis.readBoolean();
					}

					this.sirenetablissement = readString(dis);

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

				writeString(this.siretetablissement, dos);

				// Boolean

				if (this.siretetablissementvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.siretetablissementvalide);
				}

				// String

				writeString(this.sirenetablissement, dos);

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

				writeString(this.siretetablissement, dos);

				// Boolean

				if (this.siretetablissementvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.siretetablissementvalide);
				}

				// String

				writeString(this.sirenetablissement, dos);

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
			sb.append("siretetablissement=" + siretetablissement);
			sb.append(",siretetablissementvalide=" + String.valueOf(siretetablissementvalide));
			sb.append(",sirenetablissement=" + sirenetablissement);
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
		public int compareTo(row3Struct other) {

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
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public Boolean siretetablissementvalide;

		public Boolean getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
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

			other.siretetablissement = this.siretetablissement;
			other.siretetablissementvalide = this.siretetablissementvalide;
			other.sirenetablissement = this.sirenetablissement;
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.siretetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.siretetablissementvalide = null;
					} else {
						this.siretetablissementvalide = dis.readBoolean();
					}

					this.sirenetablissement = readString(dis);

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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.siretetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.siretetablissementvalide = null;
					} else {
						this.siretetablissementvalide = dis.readBoolean();
					}

					this.sirenetablissement = readString(dis);

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

				writeString(this.siretetablissement, dos);

				// Boolean

				if (this.siretetablissementvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.siretetablissementvalide);
				}

				// String

				writeString(this.sirenetablissement, dos);

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

				writeString(this.siretetablissement, dos);

				// Boolean

				if (this.siretetablissementvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.siretetablissementvalide);
				}

				// String

				writeString(this.sirenetablissement, dos);

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
			sb.append("siretetablissement=" + siretetablissement);
			sb.append(",siretetablissementvalide=" + String.valueOf(siretetablissementvalide));
			sb.append(",sirenetablissement=" + sirenetablissement);
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

	public static class VerifJointureStruct implements routines.system.IPersistableRow<VerifJointureStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String denominationsocialeetablissement;

		public String getDenominationsocialeetablissement() {
			return this.denominationsocialeetablissement;
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
			final VerifJointureStruct other = (VerifJointureStruct) obj;

			if (this.sirenetablissement == null) {
				if (other.sirenetablissement != null)
					return false;

			} else if (!this.sirenetablissement.equals(other.sirenetablissement))

				return false;

			return true;
		}

		public void copyDataTo(VerifJointureStruct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.denominationsocialeetablissement = this.denominationsocialeetablissement;

		}

		public void copyKeysDataTo(VerifJointureStruct other) {

			other.sirenetablissement = this.sirenetablissement;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.denominationsocialeetablissement = readString(dis);

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

				writeString(this.denominationsocialeetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.sirenetablissement, dos);

				// String

				writeString(this.denominationsocialeetablissement, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("sirenetablissement=" + sirenetablissement);
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(VerifJointureStruct other) {

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

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String idmarche;

		public String getIdmarche() {
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

		public String codedepartementexecution;

		public String getCodedepartementexecution() {
			return this.codedepartementexecution;
		}

		public String coderegionexecution;

		public String getCoderegionexecution() {
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

		public String codecpv_division;

		public String getCodecpv_division() {
			return this.codecpv_division;
		}

		public String refereneccpv;

		public String getRefereneccpv() {
			return this.refereneccpv;
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
			final row9Struct other = (row9Struct) obj;

			if (this.idmarche == null) {
				if (other.idmarche != null)
					return false;

			} else if (!this.idmarche.equals(other.idmarche))

				return false;

			return true;
		}

		public void copyDataTo(row9Struct other) {

			other.idmarche = this.idmarche;
			other.natureobjetmarche = this.natureobjetmarche;
			other.objetmarche = this.objetmarche;
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
			other.refereneccpv = this.refereneccpv;

		}

		public void copyKeysDataTo(row9Struct other) {

			other.idmarche = this.idmarche;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.idmarche = readString(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.codedepartementexecution = readString(dis);

					this.coderegionexecution = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readString(dis);

					this.refereneccpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.idmarche = readString(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.codedepartementexecution = readString(dis);

					this.coderegionexecution = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readString(dis);

					this.refereneccpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idmarche, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.objetmarche, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// String

				writeString(this.codedepartementexecution, dos);

				// String

				writeString(this.coderegionexecution, dos);

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

				// String

				writeString(this.codecpv_division, dos);

				// String

				writeString(this.refereneccpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idmarche, dos);

				// String

				writeString(this.natureobjetmarche, dos);

				// String

				writeString(this.objetmarche, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.lieuexecutionnom, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.lieuexecutiontypecode, dos);

				// String

				writeString(this.codedepartementexecution, dos);

				// String

				writeString(this.coderegionexecution, dos);

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

				// String

				writeString(this.codecpv_division, dos);

				// String

				writeString(this.refereneccpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idmarche=" + idmarche);
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",formeprix=" + formeprix);
			sb.append(",lieuexecutiontypecode=" + lieuexecutiontypecode);
			sb.append(",codedepartementexecution=" + codedepartementexecution);
			sb.append(",coderegionexecution=" + coderegionexecution);
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append(",nature=" + nature);
			sb.append(",procedure=" + procedure);
			sb.append(",codecpv=" + codecpv);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",codecpv_division=" + codecpv_division);
			sb.append(",refereneccpv=" + refereneccpv);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

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
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String idmarche;

		public String getIdmarche() {
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

		public String codedepartementexecution;

		public String getCodedepartementexecution() {
			return this.codedepartementexecution;
		}

		public String coderegionexecution;

		public String getCoderegionexecution() {
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

		public String codecpv_division;

		public String getCodecpv_division() {
			return this.codecpv_division;
		}

		public String refereneccpv;

		public String getRefereneccpv() {
			return this.refereneccpv;
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
			other.refereneccpv = this.refereneccpv;

		}

		public void copyKeysDataTo(marcheStruct other) {

			other.idmarche = this.idmarche;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.idmarche = readString(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.codedepartementexecution = readString(dis);

					this.coderegionexecution = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readString(dis);

					this.refereneccpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.idmarche = readString(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.codedepartementexecution = readString(dis);

					this.coderegionexecution = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readString(dis);

					this.refereneccpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idmarche, dos);

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

				// String

				writeString(this.codedepartementexecution, dos);

				// String

				writeString(this.coderegionexecution, dos);

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

				// String

				writeString(this.codecpv_division, dos);

				// String

				writeString(this.refereneccpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idmarche, dos);

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

				// String

				writeString(this.codedepartementexecution, dos);

				// String

				writeString(this.coderegionexecution, dos);

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

				// String

				writeString(this.codecpv_division, dos);

				// String

				writeString(this.refereneccpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idmarche=" + idmarche);
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",datenotification=" + datenotification);
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",formeprix=" + formeprix);
			sb.append(",lieuexecutiontypecode=" + lieuexecutiontypecode);
			sb.append(",codedepartementexecution=" + codedepartementexecution);
			sb.append(",coderegionexecution=" + coderegionexecution);
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append(",nature=" + nature);
			sb.append(",procedure=" + procedure);
			sb.append(",codecpv=" + codecpv);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",codecpv_division=" + codecpv_division);
			sb.append(",refereneccpv=" + refereneccpv);
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

	public static class doublonsStruct implements routines.system.IPersistableRow<doublonsStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String idmarche;

		public String getIdmarche() {
			return this.idmarche;
		}

		public String nature_objet;

		public String getNature_objet() {
			return this.nature_objet;
		}

		public String objet;

		public String getObjet() {
			return this.objet;
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
			final doublonsStruct other = (doublonsStruct) obj;

			if (this.idmarche == null) {
				if (other.idmarche != null)
					return false;

			} else if (!this.idmarche.equals(other.idmarche))

				return false;

			return true;
		}

		public void copyDataTo(doublonsStruct other) {

			other.idmarche = this.idmarche;
			other.nature_objet = this.nature_objet;
			other.objet = this.objet;

		}

		public void copyKeysDataTo(doublonsStruct other) {

			other.idmarche = this.idmarche;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.idmarche = readString(dis);

					this.nature_objet = readString(dis);

					this.objet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.idmarche = readString(dis);

					this.nature_objet = readString(dis);

					this.objet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idmarche, dos);

				// String

				writeString(this.nature_objet, dos);

				// String

				writeString(this.objet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idmarche, dos);

				// String

				writeString(this.nature_objet, dos);

				// String

				writeString(this.objet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idmarche=" + idmarche);
			sb.append(",nature_objet=" + nature_objet);
			sb.append(",objet=" + objet);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(doublonsStruct other) {

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

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
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
			final row6Struct other = (row6Struct) obj;

			if (this.idacheteur == null) {
				if (other.idacheteur != null)
					return false;

			} else if (!this.idacheteur.equals(other.idacheteur))

				return false;

			return true;
		}

		public void copyDataTo(row6Struct other) {

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

		public void copyKeysDataTo(row6Struct other) {

			other.idacheteur = this.idacheteur;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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
		public int compareTo(row6Struct other) {

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
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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

	public static class verifStruct implements routines.system.IPersistableRow<verifStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
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
			final verifStruct other = (verifStruct) obj;

			if (this.idacheteur == null) {
				if (other.idacheteur != null)
					return false;

			} else if (!this.idacheteur.equals(other.idacheteur))

				return false;

			return true;
		}

		public void copyDataTo(verifStruct other) {

			other.idacheteur = this.idacheteur;
			other.nomacheteur = this.nomacheteur;

		}

		public void copyKeysDataTo(verifStruct other) {

			other.idacheteur = this.idacheteur;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.idacheteur = readString(dis);

					this.nomacheteur = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.idacheteur = readString(dis);

					this.nomacheteur = readString(dis);

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
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(verifStruct other) {

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

	public static class DIM_ACHETEURStruct implements routines.system.IPersistableRow<DIM_ACHETEURStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];

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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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
		public int compareTo(DIM_ACHETEURStruct other) {

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

	public static class DIM_MARCHEStruct implements routines.system.IPersistableRow<DIM_MARCHEStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];

		public String idmarche;

		public String getIdmarche() {
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

		public String codedepartementexecution;

		public String getCodedepartementexecution() {
			return this.codedepartementexecution;
		}

		public String coderegionexecution;

		public String getCoderegionexecution() {
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

		public String codecpv_division;

		public String getCodecpv_division() {
			return this.codecpv_division;
		}

		public String refereneccpv;

		public String getRefereneccpv() {
			return this.refereneccpv;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.idmarche = readString(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.codedepartementexecution = readString(dis);

					this.coderegionexecution = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readString(dis);

					this.refereneccpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.idmarche = readString(dis);

					this.natureobjetmarche = readString(dis);

					this.objetmarche = readString(dis);

					this.datenotification = readString(dis);

					this.dureemois = readInteger(dis);

					this.lieuexecutionnom = readString(dis);

					this.formeprix = readString(dis);

					this.lieuexecutiontypecode = readString(dis);

					this.codedepartementexecution = readString(dis);

					this.coderegionexecution = readString(dis);

					this.libelleregionexecution = readString(dis);

					this.nature = readString(dis);

					this.procedure = readString(dis);

					this.codecpv = readString(dis);

					this.codecpv_original = readString(dis);

					this.codecpv_division = readString(dis);

					this.refereneccpv = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idmarche, dos);

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

				// String

				writeString(this.codedepartementexecution, dos);

				// String

				writeString(this.coderegionexecution, dos);

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

				// String

				writeString(this.codecpv_division, dos);

				// String

				writeString(this.refereneccpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idmarche, dos);

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

				// String

				writeString(this.codedepartementexecution, dos);

				// String

				writeString(this.coderegionexecution, dos);

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

				// String

				writeString(this.codecpv_division, dos);

				// String

				writeString(this.refereneccpv, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idmarche=" + idmarche);
			sb.append(",natureobjetmarche=" + natureobjetmarche);
			sb.append(",objetmarche=" + objetmarche);
			sb.append(",datenotification=" + datenotification);
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",lieuexecutionnom=" + lieuexecutionnom);
			sb.append(",formeprix=" + formeprix);
			sb.append(",lieuexecutiontypecode=" + lieuexecutiontypecode);
			sb.append(",codedepartementexecution=" + codedepartementexecution);
			sb.append(",coderegionexecution=" + coderegionexecution);
			sb.append(",libelleregionexecution=" + libelleregionexecution);
			sb.append(",nature=" + nature);
			sb.append(",procedure=" + procedure);
			sb.append(",codecpv=" + codecpv);
			sb.append(",codecpv_original=" + codecpv_original);
			sb.append(",codecpv_division=" + codecpv_division);
			sb.append(",refereneccpv=" + refereneccpv);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(DIM_MARCHEStruct other) {

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

	public static class DIM_ETABLISSEMENTStruct implements routines.system.IPersistableRow<DIM_ETABLISSEMENTStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];

		public String siretetablissement;

		public String getSiretetablissement() {
			return this.siretetablissement;
		}

		public Boolean siretetablissementvalide;

		public Boolean getSiretetablissementvalide() {
			return this.siretetablissementvalide;
		}

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.siretetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.siretetablissementvalide = null;
					} else {
						this.siretetablissementvalide = dis.readBoolean();
					}

					this.sirenetablissement = readString(dis);

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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.siretetablissement = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.siretetablissementvalide = null;
					} else {
						this.siretetablissementvalide = dis.readBoolean();
					}

					this.sirenetablissement = readString(dis);

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

				writeString(this.siretetablissement, dos);

				// Boolean

				if (this.siretetablissementvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.siretetablissementvalide);
				}

				// String

				writeString(this.sirenetablissement, dos);

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

				writeString(this.siretetablissement, dos);

				// Boolean

				if (this.siretetablissementvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.siretetablissementvalide);
				}

				// String

				writeString(this.sirenetablissement, dos);

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
			sb.append("siretetablissement=" + siretetablissement);
			sb.append(",siretetablissementvalide=" + String.valueOf(siretetablissementvalide));
			sb.append(",sirenetablissement=" + sirenetablissement);
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
		public int compareTo(DIM_ETABLISSEMENTStruct other) {

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
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];

		public String recordid;

		public String getRecordid() {
			return this.recordid;
		}

		public Boolean sirenetablissementvalide;

		public Boolean getSirenetablissementvalide() {
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

		public Integer lieuexecutioncode;

		public Integer getLieuexecutioncode() {
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

		public String idmarche;

		public String getIdmarche() {
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

		public String montantcalcule;

		public String getMontantcalcule() {
			return this.montantcalcule;
		}

		public String libelledepartementacheteur;

		public String getLibelledepartementacheteur() {
			return this.libelledepartementacheteur;
		}

		public String codecpv_division;

		public String getCodecpv_division() {
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

		public java.util.Date datepublicationdonnees;

		public java.util.Date getDatepublicationdonnees() {
			return this.datepublicationdonnees;
		}

		public Boolean sirenacheteurvalide;

		public Boolean getSirenacheteurvalide() {
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

		public String codedepartementexecution;

		public String getCodedepartementexecution() {
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

		public String coderegionexecution;

		public String getCoderegionexecution() {
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.sirenetablissementvalide = null;
					} else {
						this.sirenetablissementvalide = dis.readBoolean();
					}

					this.denominationsocialeetablissement = readString(dis);

					this.libellecommuneacheteur = readString(dis);

					this.lieuexecutioncode = readInteger(dis);

					this.adresseetablissement = readString(dis);

					this.codepostalacheteur = readString(dis);

					this.libelleregionacheteur = readString(dis);

					this.departementacheteur = readString(dis);

					this.idmarche = readString(dis);

					this.dureemois = readInteger(dis);

					this.datenotification = readString(dis);

					this.montantcalcule = readString(dis);

					this.libelledepartementacheteur = readString(dis);

					this.codecpv_division = readString(dis);

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

					this.datepublicationdonnees = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.sirenacheteurvalide = null;
					} else {
						this.sirenacheteurvalide = dis.readBoolean();
					}

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.formeprix = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.codedepartementexecution = readString(dis);

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

					this.coderegionexecution = readString(dis);

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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.sirenetablissementvalide = null;
					} else {
						this.sirenetablissementvalide = dis.readBoolean();
					}

					this.denominationsocialeetablissement = readString(dis);

					this.libellecommuneacheteur = readString(dis);

					this.lieuexecutioncode = readInteger(dis);

					this.adresseetablissement = readString(dis);

					this.codepostalacheteur = readString(dis);

					this.libelleregionacheteur = readString(dis);

					this.departementacheteur = readString(dis);

					this.idmarche = readString(dis);

					this.dureemois = readInteger(dis);

					this.datenotification = readString(dis);

					this.montantcalcule = readString(dis);

					this.libelledepartementacheteur = readString(dis);

					this.codecpv_division = readString(dis);

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

					this.datepublicationdonnees = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.sirenacheteurvalide = null;
					} else {
						this.sirenacheteurvalide = dis.readBoolean();
					}

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.formeprix = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.codedepartementexecution = readString(dis);

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

					this.coderegionexecution = readString(dis);

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

				// Boolean

				if (this.sirenetablissementvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.sirenetablissementvalide);
				}

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.libellecommuneacheteur, dos);

				// Integer

				writeInteger(this.lieuexecutioncode, dos);

				// String

				writeString(this.adresseetablissement, dos);

				// String

				writeString(this.codepostalacheteur, dos);

				// String

				writeString(this.libelleregionacheteur, dos);

				// String

				writeString(this.departementacheteur, dos);

				// String

				writeString(this.idmarche, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.datenotification, dos);

				// String

				writeString(this.montantcalcule, dos);

				// String

				writeString(this.libelledepartementacheteur, dos);

				// String

				writeString(this.codecpv_division, dos);

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

				// java.util.Date

				writeDate(this.datepublicationdonnees, dos);

				// Boolean

				if (this.sirenacheteurvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.sirenacheteurvalide);
				}

				// String

				writeString(this.moisnotification, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.codedepartementexecution, dos);

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

				// String

				writeString(this.coderegionexecution, dos);

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

				// Boolean

				if (this.sirenetablissementvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.sirenetablissementvalide);
				}

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.libellecommuneacheteur, dos);

				// Integer

				writeInteger(this.lieuexecutioncode, dos);

				// String

				writeString(this.adresseetablissement, dos);

				// String

				writeString(this.codepostalacheteur, dos);

				// String

				writeString(this.libelleregionacheteur, dos);

				// String

				writeString(this.departementacheteur, dos);

				// String

				writeString(this.idmarche, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.datenotification, dos);

				// String

				writeString(this.montantcalcule, dos);

				// String

				writeString(this.libelledepartementacheteur, dos);

				// String

				writeString(this.codecpv_division, dos);

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

				// java.util.Date

				writeDate(this.datepublicationdonnees, dos);

				// Boolean

				if (this.sirenacheteurvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.sirenacheteurvalide);
				}

				// String

				writeString(this.moisnotification, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.codedepartementexecution, dos);

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

				// String

				writeString(this.coderegionexecution, dos);

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
			sb.append(",sirenetablissementvalide=" + String.valueOf(sirenetablissementvalide));
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",libellecommuneacheteur=" + libellecommuneacheteur);
			sb.append(",lieuexecutioncode=" + String.valueOf(lieuexecutioncode));
			sb.append(",adresseetablissement=" + adresseetablissement);
			sb.append(",codepostalacheteur=" + codepostalacheteur);
			sb.append(",libelleregionacheteur=" + libelleregionacheteur);
			sb.append(",departementacheteur=" + departementacheteur);
			sb.append(",idmarche=" + idmarche);
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",datenotification=" + datenotification);
			sb.append(",montantcalcule=" + montantcalcule);
			sb.append(",libelledepartementacheteur=" + libelledepartementacheteur);
			sb.append(",codecpv_division=" + codecpv_division);
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
			sb.append(",datepublicationdonnees=" + String.valueOf(datepublicationdonnees));
			sb.append(",sirenacheteurvalide=" + String.valueOf(sirenacheteurvalide));
			sb.append(",moisnotification=" + moisnotification);
			sb.append(",coderegionetablissement=" + coderegionetablissement);
			sb.append(",formeprix=" + formeprix);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",codedepartementexecution=" + codedepartementexecution);
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
			sb.append(",coderegionexecution=" + coderegionexecution);
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
				DIM_ACHETEURStruct DIM_ACHETEUR = new DIM_ACHETEURStruct();
				acheteurStruct acheteur = new acheteurStruct();
				row6Struct row6 = new row6Struct();
				verifStruct verif = new verifStruct();
				DIM_MARCHEStruct DIM_MARCHE = new DIM_MARCHEStruct();
				marcheStruct marche = new marcheStruct();
				row9Struct row9 = new row9Struct();
				doublonsStruct doublons = new doublonsStruct();
				DIM_ETABLISSEMENTStruct DIM_ETABLISSEMENT = new DIM_ETABLISSEMENTStruct();
				etablissementStruct etablissement = new etablissementStruct();
				row3Struct row3 = new row3Struct();
				VerifJointureStruct VerifJointure = new VerifJointureStruct();

				/**
				 * [tDBCommit_2 begin ] start
				 */

				ok_Hash.put("tDBCommit_2", false);
				start_Hash.put("tDBCommit_2", System.currentTimeMillis());

				currentComponent = "tDBCommit_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
				}

				int tos_count_tDBCommit_2 = 0;

				/**
				 * [tDBCommit_2 begin ] stop
				 */

				/**
				 * [tDBOutput_5 begin ] start
				 */

				ok_Hash.put("tDBOutput_5", false);
				start_Hash.put("tDBOutput_5", System.currentTimeMillis());

				currentComponent = "tDBOutput_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "acheteur");
				}

				int tos_count_tDBOutput_5 = 0;

				String dbschema_tDBOutput_5 = null;
				dbschema_tDBOutput_5 = (String) globalMap.get("schema_" + "tDBConnection_2");

				String tableName_tDBOutput_5 = null;
				if (dbschema_tDBOutput_5 == null || dbschema_tDBOutput_5.trim().length() == 0) {
					tableName_tDBOutput_5 = ("dim_acheteur");
				} else {
					tableName_tDBOutput_5 = dbschema_tDBOutput_5 + "\".\"" + ("dim_acheteur");
				}

				int updateKeyCount_tDBOutput_5 = 1;
				if (updateKeyCount_tDBOutput_5 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_5 == 9 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_5 = 0;
				int nb_line_update_tDBOutput_5 = 0;
				int nb_line_inserted_tDBOutput_5 = 0;
				int nb_line_deleted_tDBOutput_5 = 0;
				int nb_line_rejected_tDBOutput_5 = 0;

				int deletedCount_tDBOutput_5 = 0;
				int updatedCount_tDBOutput_5 = 0;
				int insertedCount_tDBOutput_5 = 0;
				int rowsToCommitCount_tDBOutput_5 = 0;
				int rejectedCount_tDBOutput_5 = 0;

				boolean whetherReject_tDBOutput_5 = false;

				java.sql.Connection conn_tDBOutput_5 = null;
				String dbUser_tDBOutput_5 = null;

				conn_tDBOutput_5 = (java.sql.Connection) globalMap.get("conn_tDBConnection_2");

				int count_tDBOutput_5 = 0;
				java.sql.DatabaseMetaData dbMetaData_tDBOutput_5 = conn_tDBOutput_5.getMetaData();
				boolean whetherExist_tDBOutput_5 = false;
				try (java.sql.ResultSet rsTable_tDBOutput_5 = dbMetaData_tDBOutput_5.getTables(null, null, null,
						new String[] { "TABLE" })) {
					String defaultSchema_tDBOutput_5 = "public";
					if (dbschema_tDBOutput_5 == null || dbschema_tDBOutput_5.trim().length() == 0) {
						try (java.sql.Statement stmtSchema_tDBOutput_5 = conn_tDBOutput_5.createStatement();
								java.sql.ResultSet rsSchema_tDBOutput_5 = stmtSchema_tDBOutput_5
										.executeQuery("select current_schema() ")) {
							while (rsSchema_tDBOutput_5.next()) {
								defaultSchema_tDBOutput_5 = rsSchema_tDBOutput_5.getString("current_schema");
							}
						}
					}
					while (rsTable_tDBOutput_5.next()) {
						String table_tDBOutput_5 = rsTable_tDBOutput_5.getString("TABLE_NAME");
						String schema_tDBOutput_5 = rsTable_tDBOutput_5.getString("TABLE_SCHEM");
						if (table_tDBOutput_5.equals(("dim_acheteur"))
								&& (schema_tDBOutput_5.equals(dbschema_tDBOutput_5)
										|| ((dbschema_tDBOutput_5 == null || dbschema_tDBOutput_5.trim().length() == 0)
												&& defaultSchema_tDBOutput_5.equals(schema_tDBOutput_5)))) {
							whetherExist_tDBOutput_5 = true;
							break;
						}
					}
				}
				if (!whetherExist_tDBOutput_5) {
					try (java.sql.Statement stmtCreate_tDBOutput_5 = conn_tDBOutput_5.createStatement()) {
						stmtCreate_tDBOutput_5.execute("CREATE TABLE \"" + tableName_tDBOutput_5
								+ "\"(\"idAcheteur\" VARCHAR ,\"nomAcheteur\" VARCHAR ,\"codePostalAcheteur\" VARCHAR ,\"codePostalCommune\" VARCHAR ,\"nomCommune\" VARCHAR ,\"codeDepartement\" VARCHAR ,\"libelleDepartement\" VARCHAR ,\"codeRegion\" VARCHAR ,\"libelleRegion\" VARCHAR ,primary key(\"idAcheteur\"))");
					}
				}
				java.sql.PreparedStatement pstmt_tDBOutput_5 = conn_tDBOutput_5.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_5 + "\" WHERE \"idAcheteur\" = ?");
				resourceMap.put("pstmt_tDBOutput_5", pstmt_tDBOutput_5);
				String insert_tDBOutput_5 = "INSERT INTO \"" + tableName_tDBOutput_5
						+ "\" (\"idAcheteur\",\"nomAcheteur\",\"codePostalAcheteur\",\"codePostalCommune\",\"nomCommune\",\"codeDepartement\",\"libelleDepartement\",\"codeRegion\",\"libelleRegion\") VALUES (?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_5 = conn_tDBOutput_5
						.prepareStatement(insert_tDBOutput_5);
				resourceMap.put("pstmtInsert_tDBOutput_5", pstmtInsert_tDBOutput_5);
				String update_tDBOutput_5 = "UPDATE \"" + tableName_tDBOutput_5
						+ "\" SET \"nomAcheteur\" = ?,\"codePostalAcheteur\" = ?,\"codePostalCommune\" = ?,\"nomCommune\" = ?,\"codeDepartement\" = ?,\"libelleDepartement\" = ?,\"codeRegion\" = ?,\"libelleRegion\" = ? WHERE \"idAcheteur\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_5 = conn_tDBOutput_5
						.prepareStatement(update_tDBOutput_5);
				resourceMap.put("pstmtUpdate_tDBOutput_5", pstmtUpdate_tDBOutput_5);

				/**
				 * [tDBOutput_5 begin ] stop
				 */

				/**
				 * [tWarn_2 begin ] start
				 */

				ok_Hash.put("tWarn_2", false);
				start_Hash.put("tWarn_2", System.currentTimeMillis());

				currentComponent = "tWarn_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "verif");
				}

				int tos_count_tWarn_2 = 0;

				/**
				 * [tWarn_2 begin ] stop
				 */

				/**
				 * [tMap_3 begin ] start
				 */

				ok_Hash.put("tMap_3", false);
				start_Hash.put("tMap_3", System.currentTimeMillis());

				currentComponent = "tMap_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "DIM_ACHETEUR");
				}

				int tos_count_tMap_3 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = null;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_Cache_row5 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row5Struct>getLookup(
								org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH);

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_Real_row5 = null;

				row5Struct row5HashKey = new row5Struct();
				row5Struct row5Default = new row5Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_3__Struct {
					String nom_acheteur;
					String libelle_departement;
					String code_region;
					String libelle_region;
				}
				Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
				acheteurStruct acheteur_tmp = new acheteurStruct();
				verifStruct verif_tmp = new verifStruct();
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
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row9");
				}

				int tos_count_tDBCommit_3 = 0;

				/**
				 * [tDBCommit_3 begin ] stop
				 */

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "marche");
				}

				int tos_count_tDBOutput_1 = 0;

				String dbschema_tDBOutput_1 = null;
				dbschema_tDBOutput_1 = (String) globalMap.get("schema_" + "tDBConnection_2");

				String tableName_tDBOutput_1 = null;
				if (dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
					tableName_tDBOutput_1 = ("dim_marche");
				} else {
					tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "\".\"" + ("dim_marche");
				}

				int updateKeyCount_tDBOutput_1 = 1;
				if (updateKeyCount_tDBOutput_1 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_1 == 16 && true) {
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

				conn_tDBOutput_1 = (java.sql.Connection) globalMap.get("conn_tDBConnection_2");

				int count_tDBOutput_1 = 0;
				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_1 + "\" WHERE \"idMarche\" = ?");
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);
				String insert_tDBOutput_1 = "INSERT INTO \"" + tableName_tDBOutput_1
						+ "\" (\"idMarche\",\"natureObjetMarche\",\"objetMarche\",\"dureeMois\",\"lieuExecutionNom\",\"formePrix\",\"lieuExecutionTypeCode\",\"codeDepartementExecution\",\"codeRegionExecution\",\"libelleRegionExecution\",\"nature\",\"procedure\",\"codecpv\",\"codecpvOriginal\",\"codecpvDidivision\",\"referencecpv\") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmtInsert_tDBOutput_1", pstmtInsert_tDBOutput_1);
				String update_tDBOutput_1 = "UPDATE \"" + tableName_tDBOutput_1
						+ "\" SET \"natureObjetMarche\" = ?,\"objetMarche\" = ?,\"dureeMois\" = ?,\"lieuExecutionNom\" = ?,\"formePrix\" = ?,\"lieuExecutionTypeCode\" = ?,\"codeDepartementExecution\" = ?,\"codeRegionExecution\" = ?,\"libelleRegionExecution\" = ?,\"nature\" = ?,\"procedure\" = ?,\"codecpv\" = ?,\"codecpvOriginal\" = ?,\"codecpvDidivision\" = ?,\"referencecpv\" = ? WHERE \"idMarche\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(update_tDBOutput_1);
				resourceMap.put("pstmtUpdate_tDBOutput_1", pstmtUpdate_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tWarn_4 begin ] start
				 */

				ok_Hash.put("tWarn_4", false);
				start_Hash.put("tWarn_4", System.currentTimeMillis());

				currentComponent = "tWarn_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "doublons");
				}

				int tos_count_tWarn_4 = 0;

				/**
				 * [tWarn_4 begin ] stop
				 */

				/**
				 * [tMap_5 begin ] start
				 */

				ok_Hash.put("tMap_5", false);
				start_Hash.put("tMap_5", System.currentTimeMillis());

				currentComponent = "tMap_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "DIM_MARCHE");
				}

				int tos_count_tMap_5 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = null;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_Cache_row8 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row8Struct>getLookup(
								org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH);

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_Real_row8 = null;

				row8Struct row8HashKey = new row8Struct();
				row8Struct row8Default = new row8Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_5__Struct {
				}
				Var__tMap_5__Struct Var__tMap_5 = new Var__tMap_5__Struct();
// ###############################

// ###############################
// # Outputs initialization
				marcheStruct marche_tmp = new marcheStruct();
				doublonsStruct doublons_tmp = new doublonsStruct();
// ###############################

				/**
				 * [tMap_5 begin ] stop
				 */

				/**
				 * [tDBCommit_1 begin ] start
				 */

				ok_Hash.put("tDBCommit_1", false);
				start_Hash.put("tDBCommit_1", System.currentTimeMillis());

				currentComponent = "tDBCommit_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tDBCommit_1 = 0;

				/**
				 * [tDBCommit_1 begin ] stop
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
				dbschema_tDBOutput_3 = (String) globalMap.get("schema_" + "tDBConnection_2");

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

				conn_tDBOutput_3 = (java.sql.Connection) globalMap.get("conn_tDBConnection_2");

				int count_tDBOutput_3 = 0;
				java.sql.DatabaseMetaData dbMetaData_tDBOutput_3 = conn_tDBOutput_3.getMetaData();
				boolean whetherExist_tDBOutput_3 = false;
				try (java.sql.ResultSet rsTable_tDBOutput_3 = dbMetaData_tDBOutput_3.getTables(null, null, null,
						new String[] { "TABLE" })) {
					String defaultSchema_tDBOutput_3 = "public";
					if (dbschema_tDBOutput_3 == null || dbschema_tDBOutput_3.trim().length() == 0) {
						try (java.sql.Statement stmtSchema_tDBOutput_3 = conn_tDBOutput_3.createStatement();
								java.sql.ResultSet rsSchema_tDBOutput_3 = stmtSchema_tDBOutput_3
										.executeQuery("select current_schema() ")) {
							while (rsSchema_tDBOutput_3.next()) {
								defaultSchema_tDBOutput_3 = rsSchema_tDBOutput_3.getString("current_schema");
							}
						}
					}
					while (rsTable_tDBOutput_3.next()) {
						String table_tDBOutput_3 = rsTable_tDBOutput_3.getString("TABLE_NAME");
						String schema_tDBOutput_3 = rsTable_tDBOutput_3.getString("TABLE_SCHEM");
						if (table_tDBOutput_3.equals(("dim_etablissement"))
								&& (schema_tDBOutput_3.equals(dbschema_tDBOutput_3)
										|| ((dbschema_tDBOutput_3 == null || dbschema_tDBOutput_3.trim().length() == 0)
												&& defaultSchema_tDBOutput_3.equals(schema_tDBOutput_3)))) {
							whetherExist_tDBOutput_3 = true;
							break;
						}
					}
				}
				if (!whetherExist_tDBOutput_3) {
					try (java.sql.Statement stmtCreate_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
						stmtCreate_tDBOutput_3.execute("CREATE TABLE \"" + tableName_tDBOutput_3
								+ "\"(\"siretEtablissement\" VARCHAR ,\"siretEtablissementValide\" BOOL ,\"sirenEtablissement\" VARCHAR ,\"categorieEtablissement\" VARCHAR ,\"denominationSocialeEtablissement\" VARCHAR ,\"codePostalCommune\" VARCHAR ,\"nomCommune\" VARCHAR ,\"codeDepartement\" VARCHAR ,\"libelleDepartement\" VARCHAR ,\"codeRegion\" VARCHAR ,\"libelleRegion\" VARCHAR ,primary key(\"sirenEtablissement\"))");
					}
				}
				java.sql.PreparedStatement pstmt_tDBOutput_3 = conn_tDBOutput_3.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_3 + "\" WHERE \"sirenEtablissement\" = ?");
				resourceMap.put("pstmt_tDBOutput_3", pstmt_tDBOutput_3);
				String insert_tDBOutput_3 = "INSERT INTO \"" + tableName_tDBOutput_3
						+ "\" (\"siretEtablissement\",\"siretEtablissementValide\",\"sirenEtablissement\",\"categorieEtablissement\",\"denominationSocialeEtablissement\",\"codePostalCommune\",\"nomCommune\",\"codeDepartement\",\"libelleDepartement\",\"codeRegion\",\"libelleRegion\") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_3 = conn_tDBOutput_3
						.prepareStatement(insert_tDBOutput_3);
				resourceMap.put("pstmtInsert_tDBOutput_3", pstmtInsert_tDBOutput_3);
				String update_tDBOutput_3 = "UPDATE \"" + tableName_tDBOutput_3
						+ "\" SET \"siretEtablissement\" = ?,\"siretEtablissementValide\" = ?,\"categorieEtablissement\" = ?,\"denominationSocialeEtablissement\" = ?,\"codePostalCommune\" = ?,\"nomCommune\" = ?,\"codeDepartement\" = ?,\"libelleDepartement\" = ?,\"codeRegion\" = ?,\"libelleRegion\" = ? WHERE \"sirenEtablissement\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_3 = conn_tDBOutput_3
						.prepareStatement(update_tDBOutput_3);
				resourceMap.put("pstmtUpdate_tDBOutput_3", pstmtUpdate_tDBOutput_3);

				/**
				 * [tDBOutput_3 begin ] stop
				 */

				/**
				 * [tWarn_1 begin ] start
				 */

				ok_Hash.put("tWarn_1", false);
				start_Hash.put("tWarn_1", System.currentTimeMillis());

				currentComponent = "tWarn_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "VerifJointure");
				}

				int tos_count_tWarn_1 = 0;

				/**
				 * [tWarn_1 begin ] stop
				 */

				/**
				 * [tMap_2 begin ] start
				 */

				ok_Hash.put("tMap_2", false);
				start_Hash.put("tMap_2", System.currentTimeMillis());

				currentComponent = "tMap_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "DIM_ETABLISSEMENT");
				}

				int tos_count_tMap_2 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = null;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_Cache_row2 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row2Struct>getLookup(
								org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH);

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_Real_row2 = null;

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
				etablissementStruct etablissement_tmp = new etablissementStruct();
				VerifJointureStruct VerifJointure_tmp = new VerifJointureStruct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
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
					boolean siret_valide;
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				DIM_ACHETEURStruct DIM_ACHETEUR_tmp = new DIM_ACHETEURStruct();
				DIM_MARCHEStruct DIM_MARCHE_tmp = new DIM_MARCHEStruct();
				DIM_ETABLISSEMENTStruct DIM_ETABLISSEMENT_tmp = new DIM_ETABLISSEMENTStruct();
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
				conn_tDBInput_1 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "select * FROM \"marche\" LIMIT 5000";

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

							row1.sirenetablissementvalide = rs_tDBInput_1.getBoolean(2);
							if (rs_tDBInput_1.wasNull()) {
								row1.sirenetablissementvalide = null;
							}
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

							row1.lieuexecutioncode = rs_tDBInput_1.getInt(5);
							if (rs_tDBInput_1.wasNull()) {
								row1.lieuexecutioncode = null;
							}
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

							row1.idmarche = routines.system.JDBCUtil.getString(rs_tDBInput_1, 10, false);
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

							row1.montantcalcule = routines.system.JDBCUtil.getString(rs_tDBInput_1, 13, false);
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

							row1.codecpv_division = routines.system.JDBCUtil.getString(rs_tDBInput_1, 15, false);
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

							row1.datepublicationdonnees = routines.system.JDBCUtil.getDate(rs_tDBInput_1, 40);
						}
						if (colQtyInRs_tDBInput_1 < 41) {
							row1.sirenacheteurvalide = null;
						} else {

							row1.sirenacheteurvalide = rs_tDBInput_1.getBoolean(41);
							if (rs_tDBInput_1.wasNull()) {
								row1.sirenacheteurvalide = null;
							}
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

							row1.codedepartementexecution = routines.system.JDBCUtil.getString(rs_tDBInput_1, 46,
									false);
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

							row1.coderegionexecution = routines.system.JDBCUtil.getString(rs_tDBInput_1, 58, false);
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

							Var__tMap_1__Struct Var = Var__tMap_1;
							Var.siret_valide = API.check_boolean(row1.siretetablissementvalide, row1.recordid);// ###############################
							// ###############################
							// # Output tables

							DIM_ACHETEUR = null;
							DIM_MARCHE = null;
							DIM_ETABLISSEMENT = null;

// # Output table : 'DIM_ACHETEUR'
							DIM_ACHETEUR_tmp.idacheteur = row1.idacheteur;
							DIM_ACHETEUR_tmp.nomacheteur = row1.nomacheteur;
							DIM_ACHETEUR_tmp.codepostaleacheteur = row1.codepostalacheteur;
							DIM_ACHETEUR_tmp.codepostalecommune = row1.codecommuneacheteur;
							DIM_ACHETEUR_tmp.nomcommune = row1.libellecommuneacheteur;
							DIM_ACHETEUR_tmp.codedepartement = row1.departementacheteur;
							DIM_ACHETEUR_tmp.libelledepartement = row1.libelledepartementacheteur;
							DIM_ACHETEUR_tmp.coderegion = row1.coderegionacheteur;
							DIM_ACHETEUR_tmp.libelleregion = row1.libelleregionacheteur;
							DIM_ACHETEUR = DIM_ACHETEUR_tmp;

// # Output table : 'DIM_MARCHE'
							DIM_MARCHE_tmp.idmarche = row1.idmarche;
							DIM_MARCHE_tmp.natureobjetmarche = row1.natureobjetmarche;
							DIM_MARCHE_tmp.objetmarche = row1.objetmarche;
							DIM_MARCHE_tmp.datenotification = row1.datenotification;
							DIM_MARCHE_tmp.dureemois = row1.dureemois;
							DIM_MARCHE_tmp.lieuexecutionnom = row1.lieuexecutionnom;
							DIM_MARCHE_tmp.formeprix = row1.formeprix;
							DIM_MARCHE_tmp.lieuexecutiontypecode = row1.lieuexecutiontypecode;
							DIM_MARCHE_tmp.codedepartementexecution = row1.codedepartementexecution;
							DIM_MARCHE_tmp.coderegionexecution = row1.coderegionexecution;
							DIM_MARCHE_tmp.libelleregionexecution = row1.libelleregionexecution;
							DIM_MARCHE_tmp.nature = row1.nature;
							DIM_MARCHE_tmp.procedure = row1.procedure;
							DIM_MARCHE_tmp.codecpv = row1.codecpv;
							DIM_MARCHE_tmp.codecpv_original = row1.codecpv_original;
							DIM_MARCHE_tmp.codecpv_division = row1.codecpv_division;
							DIM_MARCHE_tmp.refereneccpv = row1.referencecpv;
							DIM_MARCHE = DIM_MARCHE_tmp;

// # Output table : 'DIM_ETABLISSEMENT'
							DIM_ETABLISSEMENT_tmp.siretetablissement = row1.siretetablissement;
							DIM_ETABLISSEMENT_tmp.siretetablissementvalide = Var.siret_valide;
							DIM_ETABLISSEMENT_tmp.sirenetablissement = row1.sirenetablissement;
							DIM_ETABLISSEMENT_tmp.categorieetablissement = row1.categorieetablissement;
							DIM_ETABLISSEMENT_tmp.denominationsocialeetablissement = row1.denominationsocialeetablissement;
							DIM_ETABLISSEMENT_tmp.codecommuneetablissement = row1.codecommuneetablissement;
							DIM_ETABLISSEMENT_tmp.communeetablissement = row1.communeetablissement;
							DIM_ETABLISSEMENT_tmp.departementetablissement = row1.departementetablissement;
							DIM_ETABLISSEMENT_tmp.libelledepartementetablissement = row1.libelledepartementetablissement;
							DIM_ETABLISSEMENT_tmp.coderegionetablissement = row1.coderegionetablissement;
							DIM_ETABLISSEMENT_tmp.libelleregionetablissement = row1.libelleregionetablissement;
							DIM_ETABLISSEMENT = DIM_ETABLISSEMENT_tmp;
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
// Start of branch "DIM_ACHETEUR"
						if (DIM_ACHETEUR != null) {

							/**
							 * [tMap_3 main ] start
							 */

							currentComponent = "tMap_3";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "DIM_ACHETEUR"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_3 = false;
							boolean mainRowRejected_tMap_3 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row5"
							///////////////////////////////////////////////

							boolean forceLooprow5 = false;

							row5Struct row5ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_3) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_3 = false;

								row5HashKey.id = DIM_ACHETEUR.idacheteur;

								row5HashKey.hashCodeDirty = true;

								tHash_Lookup_Cache_row5.lookup(row5HashKey);
								if (tHash_Lookup_Cache_row5.hasNext()) { // G_TM_M_835

									tHash_Lookup_row5 = tHash_Lookup_Cache_row5;

								} // G_TM_M_834
								else { // G_TM_M_835

									tDBInput_3Process(globalMap);

									tHash_Lookup_row5 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) globalMap
											.get("tHash_Lookup_row5"));

									tHash_Lookup_row5.initGet();

									tHash_Lookup_row5.lookup(row5HashKey);

								} // G_TM_M_835

								if (!tHash_Lookup_row5.hasNext()) { // G_TM_M_090

									rejectedInnerJoin_tMap_3 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							if (tHash_Lookup_row5 != null && tHash_Lookup_row5.getCount(row5HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row5'
								// and it contains more one result from keys : row5.id = '" + row5HashKey.id +
								// "'");
							} // G 071

							row5Struct row5 = null;

							row5Struct fromLookup_row5 = null;
							row5 = row5Default;

							if (tHash_Lookup_row5 != null && tHash_Lookup_row5.hasNext()) { // G 099

								fromLookup_row5 = tHash_Lookup_row5.next();

							} // G 099

							if (fromLookup_row5 != null) {
								row5 = fromLookup_row5;
							}

							if (tHash_Lookup_Cache_row5 != tHash_Lookup_row5) {
								tHash_Lookup_Cache_row5.put(row5);
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_3__Struct Var = Var__tMap_3;
								Var.nom_acheteur = API.fill_null(DIM_ACHETEUR.nomacheteur);
								Var.libelle_departement = API.fill_null(DIM_ACHETEUR.libelledepartement);
								Var.code_region = API.fill_null(DIM_ACHETEUR.coderegion);
								Var.libelle_region = API.fill_null(DIM_ACHETEUR.libelleregion);// ###############################
								// ###############################
								// # Output tables

								verif = null;
								acheteur = null;

								if (!rejectedInnerJoin_tMap_3) {

// # Output table : 'verif'
									verif_tmp.idacheteur = DIM_ACHETEUR.idacheteur;
									verif_tmp.nomacheteur = DIM_ACHETEUR.nomacheteur;
									verif = verif_tmp;
								} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'acheteur'
// # Filter conditions 
								if (rejectedInnerJoin_tMap_3) {
									acheteur_tmp.idacheteur = DIM_ACHETEUR.idacheteur;
									acheteur_tmp.nomacheteur = Var.nom_acheteur;
									acheteur_tmp.codepostaleacheteur = DIM_ACHETEUR.codepostaleacheteur;
									acheteur_tmp.codepostalecommune = DIM_ACHETEUR.codepostalecommune;
									acheteur_tmp.nomcommune = DIM_ACHETEUR.nomcommune;
									acheteur_tmp.codedepartement = DIM_ACHETEUR.codedepartement;
									acheteur_tmp.libelledepartement = Var.libelle_departement;
									acheteur_tmp.coderegion = Var.code_region;
									acheteur_tmp.libelleregion = Var.libelle_region;
									acheteur = acheteur_tmp;
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
// Start of branch "acheteur"
							if (acheteur != null) {

								/**
								 * [tDBOutput_5 main ] start
								 */

								currentComponent = "tDBOutput_5";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "acheteur"

									);
								}

								row6 = null;
								whetherReject_tDBOutput_5 = false;
								if (acheteur.idacheteur == null) {
									pstmt_tDBOutput_5.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_5.setString(1, acheteur.idacheteur);
								}

								int checkCount_tDBOutput_5 = -1;
								try (java.sql.ResultSet rs_tDBOutput_5 = pstmt_tDBOutput_5.executeQuery()) {
									while (rs_tDBOutput_5.next()) {
										checkCount_tDBOutput_5 = rs_tDBOutput_5.getInt(1);
									}
								}
								if (checkCount_tDBOutput_5 > 0) {
									if (acheteur.nomacheteur == null) {
										pstmtUpdate_tDBOutput_5.setNull(1, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_5.setString(1, acheteur.nomacheteur);
									}

									if (acheteur.codepostaleacheteur == null) {
										pstmtUpdate_tDBOutput_5.setNull(2, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_5.setString(2, acheteur.codepostaleacheteur);
									}

									if (acheteur.codepostalecommune == null) {
										pstmtUpdate_tDBOutput_5.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_5.setString(3, acheteur.codepostalecommune);
									}

									if (acheteur.nomcommune == null) {
										pstmtUpdate_tDBOutput_5.setNull(4, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_5.setString(4, acheteur.nomcommune);
									}

									if (acheteur.codedepartement == null) {
										pstmtUpdate_tDBOutput_5.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_5.setString(5, acheteur.codedepartement);
									}

									if (acheteur.libelledepartement == null) {
										pstmtUpdate_tDBOutput_5.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_5.setString(6, acheteur.libelledepartement);
									}

									if (acheteur.coderegion == null) {
										pstmtUpdate_tDBOutput_5.setNull(7, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_5.setString(7, acheteur.coderegion);
									}

									if (acheteur.libelleregion == null) {
										pstmtUpdate_tDBOutput_5.setNull(8, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_5.setString(8, acheteur.libelleregion);
									}

									if (acheteur.idacheteur == null) {
										pstmtUpdate_tDBOutput_5.setNull(9 + count_tDBOutput_5, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_5.setString(9 + count_tDBOutput_5, acheteur.idacheteur);
									}

									try {

										int processedCount_tDBOutput_5 = pstmtUpdate_tDBOutput_5.executeUpdate();
										updatedCount_tDBOutput_5 += processedCount_tDBOutput_5;
										rowsToCommitCount_tDBOutput_5 += processedCount_tDBOutput_5;
										nb_line_tDBOutput_5++;

									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_5_ERROR_MESSAGE", e.getMessage());

										whetherReject_tDBOutput_5 = true;
										nb_line_tDBOutput_5++;
										System.err.print(e.getMessage());
									}
								} else {
									if (acheteur.idacheteur == null) {
										pstmtInsert_tDBOutput_5.setNull(1, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_5.setString(1, acheteur.idacheteur);
									}

									if (acheteur.nomacheteur == null) {
										pstmtInsert_tDBOutput_5.setNull(2, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_5.setString(2, acheteur.nomacheteur);
									}

									if (acheteur.codepostaleacheteur == null) {
										pstmtInsert_tDBOutput_5.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_5.setString(3, acheteur.codepostaleacheteur);
									}

									if (acheteur.codepostalecommune == null) {
										pstmtInsert_tDBOutput_5.setNull(4, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_5.setString(4, acheteur.codepostalecommune);
									}

									if (acheteur.nomcommune == null) {
										pstmtInsert_tDBOutput_5.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_5.setString(5, acheteur.nomcommune);
									}

									if (acheteur.codedepartement == null) {
										pstmtInsert_tDBOutput_5.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_5.setString(6, acheteur.codedepartement);
									}

									if (acheteur.libelledepartement == null) {
										pstmtInsert_tDBOutput_5.setNull(7, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_5.setString(7, acheteur.libelledepartement);
									}

									if (acheteur.coderegion == null) {
										pstmtInsert_tDBOutput_5.setNull(8, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_5.setString(8, acheteur.coderegion);
									}

									if (acheteur.libelleregion == null) {
										pstmtInsert_tDBOutput_5.setNull(9, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_5.setString(9, acheteur.libelleregion);
									}

									try {

										int processedCount_tDBOutput_5 = pstmtInsert_tDBOutput_5.executeUpdate();
										insertedCount_tDBOutput_5 += processedCount_tDBOutput_5;
										rowsToCommitCount_tDBOutput_5 += processedCount_tDBOutput_5;
										nb_line_tDBOutput_5++;

									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_5_ERROR_MESSAGE", e.getMessage());

										whetherReject_tDBOutput_5 = true;
										nb_line_tDBOutput_5++;
										System.err.print(e.getMessage());
									}
								}
								if (!whetherReject_tDBOutput_5) {
									row6 = new row6Struct();
									row6.idacheteur = acheteur.idacheteur;
									row6.nomacheteur = acheteur.nomacheteur;
									row6.codepostaleacheteur = acheteur.codepostaleacheteur;
									row6.codepostalecommune = acheteur.codepostalecommune;
									row6.nomcommune = acheteur.nomcommune;
									row6.codedepartement = acheteur.codedepartement;
									row6.libelledepartement = acheteur.libelledepartement;
									row6.coderegion = acheteur.coderegion;
									row6.libelleregion = acheteur.libelleregion;
								}

								tos_count_tDBOutput_5++;

								/**
								 * [tDBOutput_5 main ] stop
								 */

								/**
								 * [tDBOutput_5 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_5";

								/**
								 * [tDBOutput_5 process_data_begin ] stop
								 */
// Start of branch "row6"
								if (row6 != null) {

									/**
									 * [tDBCommit_2 main ] start
									 */

									currentComponent = "tDBCommit_2";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row6"

										);
									}

									java.sql.Connection conn_tDBCommit_2 = (java.sql.Connection) globalMap
											.get("conn_tDBConnection_2");
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

								} // End of branch "row6"

								/**
								 * [tDBOutput_5 process_data_end ] start
								 */

								currentComponent = "tDBOutput_5";

								/**
								 * [tDBOutput_5 process_data_end ] stop
								 */

							} // End of branch "acheteur"

// Start of branch "verif"
							if (verif != null) {

								/**
								 * [tWarn_2 main ] start
								 */

								currentComponent = "tWarn_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "verif"

									);
								}

								try {

									resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_2", "",
											Thread.currentThread().getId() + "", "WARN", "",
											"DIM_acheteur : insertion de doublon. Valeur => " + verif.idacheteur + " / "
													+ verif.nomacheteur,
											"", "");
									tLogCatcher_1.addMessage("tWarn", "tWarn_2", 4,
											"DIM_acheteur : insertion de doublon. Valeur => " + verif.idacheteur + " / "
													+ verif.nomacheteur,
											42);
									tLogCatcher_1Process(globalMap);
									globalMap.put("tWarn_2_WARN_MESSAGES",
											"DIM_acheteur : insertion de doublon. Valeur => " + verif.idacheteur + " / "
													+ verif.nomacheteur);
									globalMap.put("tWarn_2_WARN_PRIORITY", 4);
									globalMap.put("tWarn_2_WARN_CODE", 42);

								} catch (Exception e_tWarn_2) {
									globalMap.put("tWarn_2_ERROR_MESSAGE", e_tWarn_2.getMessage());
									logIgnoredError(String.format(
											"tWarn_2 - tWarn failed to log message due to internal error: %s",
											e_tWarn_2), e_tWarn_2);
								}

								tos_count_tWarn_2++;

								/**
								 * [tWarn_2 main ] stop
								 */

								/**
								 * [tWarn_2 process_data_begin ] start
								 */

								currentComponent = "tWarn_2";

								/**
								 * [tWarn_2 process_data_begin ] stop
								 */

								/**
								 * [tWarn_2 process_data_end ] start
								 */

								currentComponent = "tWarn_2";

								/**
								 * [tWarn_2 process_data_end ] stop
								 */

							} // End of branch "verif"

							/**
							 * [tMap_3 process_data_end ] start
							 */

							currentComponent = "tMap_3";

							/**
							 * [tMap_3 process_data_end ] stop
							 */

						} // End of branch "DIM_ACHETEUR"

// Start of branch "DIM_MARCHE"
						if (DIM_MARCHE != null) {

							/**
							 * [tMap_5 main ] start
							 */

							currentComponent = "tMap_5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "DIM_MARCHE"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_5 = false;
							boolean mainRowRejected_tMap_5 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row8"
							///////////////////////////////////////////////

							boolean forceLooprow8 = false;

							row8Struct row8ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_5) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_5 = false;

								row8HashKey.id = DIM_MARCHE.idmarche;

								row8HashKey.hashCodeDirty = true;

								tHash_Lookup_Cache_row8.lookup(row8HashKey);
								if (tHash_Lookup_Cache_row8.hasNext()) { // G_TM_M_835

									tHash_Lookup_row8 = tHash_Lookup_Cache_row8;

								} // G_TM_M_834
								else { // G_TM_M_835

									tDBInput_5Process(globalMap);

									tHash_Lookup_row8 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) globalMap
											.get("tHash_Lookup_row8"));

									tHash_Lookup_row8.initGet();

									tHash_Lookup_row8.lookup(row8HashKey);

								} // G_TM_M_835

								if (!tHash_Lookup_row8.hasNext()) { // G_TM_M_090

									rejectedInnerJoin_tMap_5 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							if (tHash_Lookup_row8 != null && tHash_Lookup_row8.getCount(row8HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row8'
								// and it contains more one result from keys : row8.id = '" + row8HashKey.id +
								// "'");
							} // G 071

							row8Struct row8 = null;

							row8Struct fromLookup_row8 = null;
							row8 = row8Default;

							if (tHash_Lookup_row8 != null && tHash_Lookup_row8.hasNext()) { // G 099

								fromLookup_row8 = tHash_Lookup_row8.next();

							} // G 099

							if (fromLookup_row8 != null) {
								row8 = fromLookup_row8;
							}

							if (tHash_Lookup_Cache_row8 != tHash_Lookup_row8) {
								tHash_Lookup_Cache_row8.put(row8);
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_5__Struct Var = Var__tMap_5;// ###############################
								// ###############################
								// # Output tables

								doublons = null;
								marche = null;

								if (!rejectedInnerJoin_tMap_5) {

// # Output table : 'doublons'
									doublons_tmp.idmarche = DIM_MARCHE.idmarche;
									doublons_tmp.nature_objet = DIM_MARCHE.natureobjetmarche;
									doublons_tmp.objet = DIM_MARCHE.objetmarche;
									doublons = doublons_tmp;
								} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'marche'
// # Filter conditions 
								if (rejectedInnerJoin_tMap_5) {
									marche_tmp.idmarche = DIM_MARCHE.idmarche;
									marche_tmp.natureobjetmarche = DIM_MARCHE.natureobjetmarche;
									marche_tmp.objetmarche = API.fill_null(DIM_MARCHE.objetmarche);
									marche_tmp.datenotification = DIM_MARCHE.datenotification;
									marche_tmp.dureemois = DIM_MARCHE.dureemois;
									marche_tmp.lieuexecutionnom = API.fill_null(DIM_MARCHE.lieuexecutionnom);
									marche_tmp.formeprix = API.fill_null(DIM_MARCHE.formeprix);
									marche_tmp.lieuexecutiontypecode = API.fill_null(DIM_MARCHE.lieuexecutiontypecode);
									marche_tmp.codedepartementexecution = API
											.fill_null(DIM_MARCHE.codedepartementexecution);
									marche_tmp.coderegionexecution = API.fill_null(DIM_MARCHE.coderegionexecution);
									marche_tmp.libelleregionexecution = API
											.fill_null(DIM_MARCHE.libelleregionexecution);
									marche_tmp.nature = API.fill_null(DIM_MARCHE.nature);
									marche_tmp.procedure = API.fill_null(DIM_MARCHE.procedure);
									marche_tmp.codecpv = API.fill_null(DIM_MARCHE.codecpv);
									marche_tmp.codecpv_original = API.fill_null(DIM_MARCHE.codecpv_original);
									marche_tmp.codecpv_division = API.fill_null(DIM_MARCHE.codecpv_division);
									marche_tmp.refereneccpv = API.fill_null(DIM_MARCHE.refereneccpv);
									marche = marche_tmp;
								} // closing filter/reject
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
// Start of branch "marche"
							if (marche != null) {

								/**
								 * [tDBOutput_1 main ] start
								 */

								currentComponent = "tDBOutput_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "marche"

									);
								}

								row9 = null;
								whetherReject_tDBOutput_1 = false;
								if (marche.idmarche == null) {
									pstmt_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(1, marche.idmarche);
								}

								int checkCount_tDBOutput_1 = -1;
								try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
									while (rs_tDBOutput_1.next()) {
										checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
									}
								}
								if (checkCount_tDBOutput_1 > 0) {
									if (marche.natureobjetmarche == null) {
										pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(1, marche.natureobjetmarche);
									}

									if (marche.objetmarche == null) {
										pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(2, marche.objetmarche);
									}

									if (marche.dureemois == null) {
										pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(3, marche.dureemois);
									}

									if (marche.lieuexecutionnom == null) {
										pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(4, marche.lieuexecutionnom);
									}

									if (marche.formeprix == null) {
										pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(5, marche.formeprix);
									}

									if (marche.lieuexecutiontypecode == null) {
										pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(6, marche.lieuexecutiontypecode);
									}

									if (marche.codedepartementexecution == null) {
										pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(7, marche.codedepartementexecution);
									}

									if (marche.coderegionexecution == null) {
										pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(8, marche.coderegionexecution);
									}

									if (marche.libelleregionexecution == null) {
										pstmtUpdate_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(9, marche.libelleregionexecution);
									}

									if (marche.nature == null) {
										pstmtUpdate_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(10, marche.nature);
									}

									if (marche.procedure == null) {
										pstmtUpdate_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(11, marche.procedure);
									}

									if (marche.codecpv == null) {
										pstmtUpdate_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(12, marche.codecpv);
									}

									if (marche.codecpv_original == null) {
										pstmtUpdate_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(13, marche.codecpv_original);
									}

									if (marche.codecpv_division == null) {
										pstmtUpdate_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(14, marche.codecpv_division);
									}

									if (marche.refereneccpv == null) {
										pstmtUpdate_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(15, marche.refereneccpv);
									}

									if (marche.idmarche == null) {
										pstmtUpdate_tDBOutput_1.setNull(16 + count_tDBOutput_1, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(16 + count_tDBOutput_1, marche.idmarche);
									}

									try {

										int processedCount_tDBOutput_1 = pstmtUpdate_tDBOutput_1.executeUpdate();
										updatedCount_tDBOutput_1 += processedCount_tDBOutput_1;
										rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
										nb_line_tDBOutput_1++;

									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

										whetherReject_tDBOutput_1 = true;
										nb_line_tDBOutput_1++;
										System.err.print(e.getMessage());
									}
								} else {
									if (marche.idmarche == null) {
										pstmtInsert_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(1, marche.idmarche);
									}

									if (marche.natureobjetmarche == null) {
										pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(2, marche.natureobjetmarche);
									}

									if (marche.objetmarche == null) {
										pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(3, marche.objetmarche);
									}

									if (marche.dureemois == null) {
										pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(4, marche.dureemois);
									}

									if (marche.lieuexecutionnom == null) {
										pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(5, marche.lieuexecutionnom);
									}

									if (marche.formeprix == null) {
										pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(6, marche.formeprix);
									}

									if (marche.lieuexecutiontypecode == null) {
										pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(7, marche.lieuexecutiontypecode);
									}

									if (marche.codedepartementexecution == null) {
										pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(8, marche.codedepartementexecution);
									}

									if (marche.coderegionexecution == null) {
										pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(9, marche.coderegionexecution);
									}

									if (marche.libelleregionexecution == null) {
										pstmtInsert_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(10, marche.libelleregionexecution);
									}

									if (marche.nature == null) {
										pstmtInsert_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(11, marche.nature);
									}

									if (marche.procedure == null) {
										pstmtInsert_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(12, marche.procedure);
									}

									if (marche.codecpv == null) {
										pstmtInsert_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(13, marche.codecpv);
									}

									if (marche.codecpv_original == null) {
										pstmtInsert_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(14, marche.codecpv_original);
									}

									if (marche.codecpv_division == null) {
										pstmtInsert_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(15, marche.codecpv_division);
									}

									if (marche.refereneccpv == null) {
										pstmtInsert_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(16, marche.refereneccpv);
									}

									try {

										int processedCount_tDBOutput_1 = pstmtInsert_tDBOutput_1.executeUpdate();
										insertedCount_tDBOutput_1 += processedCount_tDBOutput_1;
										rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
										nb_line_tDBOutput_1++;

									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

										whetherReject_tDBOutput_1 = true;
										nb_line_tDBOutput_1++;
										System.err.print(e.getMessage());
									}
								}
								if (!whetherReject_tDBOutput_1) {
									row9 = new row9Struct();
									row9.idmarche = marche.idmarche;
									row9.natureobjetmarche = marche.natureobjetmarche;
									row9.objetmarche = marche.objetmarche;
									row9.dureemois = marche.dureemois;
									row9.lieuexecutionnom = marche.lieuexecutionnom;
									row9.formeprix = marche.formeprix;
									row9.lieuexecutiontypecode = marche.lieuexecutiontypecode;
									row9.codedepartementexecution = marche.codedepartementexecution;
									row9.coderegionexecution = marche.coderegionexecution;
									row9.libelleregionexecution = marche.libelleregionexecution;
									row9.nature = marche.nature;
									row9.procedure = marche.procedure;
									row9.codecpv = marche.codecpv;
									row9.codecpv_original = marche.codecpv_original;
									row9.codecpv_division = marche.codecpv_division;
									row9.refereneccpv = marche.refereneccpv;
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
// Start of branch "row9"
								if (row9 != null) {

									/**
									 * [tDBCommit_3 main ] start
									 */

									currentComponent = "tDBCommit_3";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row9"

										);
									}

									java.sql.Connection conn_tDBCommit_3 = (java.sql.Connection) globalMap
											.get("conn_tDBConnection_2");
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

								} // End of branch "row9"

								/**
								 * [tDBOutput_1 process_data_end ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_end ] stop
								 */

							} // End of branch "marche"

// Start of branch "doublons"
							if (doublons != null) {

								/**
								 * [tWarn_4 main ] start
								 */

								currentComponent = "tWarn_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "doublons"

									);
								}

								try {

									resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_4", "",
											Thread.currentThread().getId() + "", "WARN", "",
											"DIM_marche : insertion de doublon. Valeur => " + doublons.idmarche + " / "
													+ doublons.objet,
											"", "");
									tLogCatcher_1.addMessage("tWarn", "tWarn_4", 4,
											"DIM_marche : insertion de doublon. Valeur => " + doublons.idmarche + " / "
													+ doublons.objet,
											42);
									tLogCatcher_1Process(globalMap);
									globalMap.put("tWarn_4_WARN_MESSAGES",
											"DIM_marche : insertion de doublon. Valeur => " + doublons.idmarche + " / "
													+ doublons.objet);
									globalMap.put("tWarn_4_WARN_PRIORITY", 4);
									globalMap.put("tWarn_4_WARN_CODE", 42);

								} catch (Exception e_tWarn_4) {
									globalMap.put("tWarn_4_ERROR_MESSAGE", e_tWarn_4.getMessage());
									logIgnoredError(String.format(
											"tWarn_4 - tWarn failed to log message due to internal error: %s",
											e_tWarn_4), e_tWarn_4);
								}

								tos_count_tWarn_4++;

								/**
								 * [tWarn_4 main ] stop
								 */

								/**
								 * [tWarn_4 process_data_begin ] start
								 */

								currentComponent = "tWarn_4";

								/**
								 * [tWarn_4 process_data_begin ] stop
								 */

								/**
								 * [tWarn_4 process_data_end ] start
								 */

								currentComponent = "tWarn_4";

								/**
								 * [tWarn_4 process_data_end ] stop
								 */

							} // End of branch "doublons"

							/**
							 * [tMap_5 process_data_end ] start
							 */

							currentComponent = "tMap_5";

							/**
							 * [tMap_5 process_data_end ] stop
							 */

						} // End of branch "DIM_MARCHE"

// Start of branch "DIM_ETABLISSEMENT"
						if (DIM_ETABLISSEMENT != null) {

							/**
							 * [tMap_2 main ] start
							 */

							currentComponent = "tMap_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "DIM_ETABLISSEMENT"

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

								row2HashKey.siren = DIM_ETABLISSEMENT.sirenetablissement;

								row2HashKey.hashCodeDirty = true;

								tHash_Lookup_Cache_row2.lookup(row2HashKey);
								if (tHash_Lookup_Cache_row2.hasNext()) { // G_TM_M_835

									tHash_Lookup_row2 = tHash_Lookup_Cache_row2;

								} // G_TM_M_834
								else { // G_TM_M_835

									tDBInput_2Process(globalMap);

									tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) globalMap
											.get("tHash_Lookup_row2"));

									tHash_Lookup_row2.initGet();

									tHash_Lookup_row2.lookup(row2HashKey);

								} // G_TM_M_835

								if (!tHash_Lookup_row2.hasNext()) { // G_TM_M_090

									rejectedInnerJoin_tMap_2 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							if (tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2'
								// and it contains more one result from keys : row2.siren = '" +
								// row2HashKey.siren + "'");
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

							if (tHash_Lookup_Cache_row2 != tHash_Lookup_row2) {
								tHash_Lookup_Cache_row2.put(row2);
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
								// ###############################
								// # Output tables

								VerifJointure = null;
								etablissement = null;

								if (!rejectedInnerJoin_tMap_2) {

// # Output table : 'VerifJointure'
									VerifJointure_tmp.sirenetablissement = DIM_ETABLISSEMENT.sirenetablissement;
									VerifJointure_tmp.denominationsocialeetablissement = DIM_ETABLISSEMENT.denominationsocialeetablissement;
									VerifJointure = VerifJointure_tmp;
								} // closing inner join bracket (1)
// ###### START REJECTS ##### 

// # Output reject table : 'etablissement'
// # Filter conditions 
								if (rejectedInnerJoin_tMap_2) {
									etablissement_tmp.siretetablissement = DIM_ETABLISSEMENT.siretetablissement;
									etablissement_tmp.siretetablissementvalide = DIM_ETABLISSEMENT.siretetablissementvalide;
									etablissement_tmp.sirenetablissement = DIM_ETABLISSEMENT.sirenetablissement;
									etablissement_tmp.categorieetablissement = DIM_ETABLISSEMENT.categorieetablissement;
									etablissement_tmp.denominationsocialeetablissement = DIM_ETABLISSEMENT.denominationsocialeetablissement;
									etablissement_tmp.codecommuneetablissement = DIM_ETABLISSEMENT.codecommuneetablissement;
									etablissement_tmp.communeetablissement = DIM_ETABLISSEMENT.communeetablissement;
									etablissement_tmp.departementetablissement = DIM_ETABLISSEMENT.departementetablissement;
									etablissement_tmp.libelledepartementetablissement = DIM_ETABLISSEMENT.libelledepartementetablissement;
									etablissement_tmp.coderegionetablissement = DIM_ETABLISSEMENT.coderegionetablissement;
									etablissement_tmp.libelleregionetablissement = DIM_ETABLISSEMENT.libelleregionetablissement;
									etablissement = etablissement_tmp;
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

								row3 = null;
								whetherReject_tDBOutput_3 = false;
								if (etablissement.sirenetablissement == null) {
									pstmt_tDBOutput_3.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(1, etablissement.sirenetablissement);
								}

								int checkCount_tDBOutput_3 = -1;
								try (java.sql.ResultSet rs_tDBOutput_3 = pstmt_tDBOutput_3.executeQuery()) {
									while (rs_tDBOutput_3.next()) {
										checkCount_tDBOutput_3 = rs_tDBOutput_3.getInt(1);
									}
								}
								if (checkCount_tDBOutput_3 > 0) {
									if (etablissement.siretetablissement == null) {
										pstmtUpdate_tDBOutput_3.setNull(1, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_3.setString(1, etablissement.siretetablissement);
									}

									if (etablissement.siretetablissementvalide == null) {
										pstmtUpdate_tDBOutput_3.setNull(2, java.sql.Types.BOOLEAN);
									} else {
										pstmtUpdate_tDBOutput_3.setBoolean(2, etablissement.siretetablissementvalide);
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
										pstmtUpdate_tDBOutput_3.setString(8,
												etablissement.libelledepartementetablissement);
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

										int processedCount_tDBOutput_3 = pstmtUpdate_tDBOutput_3.executeUpdate();
										updatedCount_tDBOutput_3 += processedCount_tDBOutput_3;
										rowsToCommitCount_tDBOutput_3 += processedCount_tDBOutput_3;
										nb_line_tDBOutput_3++;

									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());

										whetherReject_tDBOutput_3 = true;
										nb_line_tDBOutput_3++;
										System.err.print(e.getMessage());
									}
								} else {
									if (etablissement.siretetablissement == null) {
										pstmtInsert_tDBOutput_3.setNull(1, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_3.setString(1, etablissement.siretetablissement);
									}

									if (etablissement.siretetablissementvalide == null) {
										pstmtInsert_tDBOutput_3.setNull(2, java.sql.Types.BOOLEAN);
									} else {
										pstmtInsert_tDBOutput_3.setBoolean(2, etablissement.siretetablissementvalide);
									}

									if (etablissement.sirenetablissement == null) {
										pstmtInsert_tDBOutput_3.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_3.setString(3, etablissement.sirenetablissement);
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
										pstmtInsert_tDBOutput_3.setString(6, etablissement.codecommuneetablissement);
									}

									if (etablissement.communeetablissement == null) {
										pstmtInsert_tDBOutput_3.setNull(7, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_3.setString(7, etablissement.communeetablissement);
									}

									if (etablissement.departementetablissement == null) {
										pstmtInsert_tDBOutput_3.setNull(8, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_3.setString(8, etablissement.departementetablissement);
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
										pstmtInsert_tDBOutput_3.setString(10, etablissement.coderegionetablissement);
									}

									if (etablissement.libelleregionetablissement == null) {
										pstmtInsert_tDBOutput_3.setNull(11, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_3.setString(11, etablissement.libelleregionetablissement);
									}

									try {

										int processedCount_tDBOutput_3 = pstmtInsert_tDBOutput_3.executeUpdate();
										insertedCount_tDBOutput_3 += processedCount_tDBOutput_3;
										rowsToCommitCount_tDBOutput_3 += processedCount_tDBOutput_3;
										nb_line_tDBOutput_3++;

									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());

										whetherReject_tDBOutput_3 = true;
										nb_line_tDBOutput_3++;
										System.err.print(e.getMessage());
									}
								}
								if (!whetherReject_tDBOutput_3) {
									row3 = new row3Struct();
									row3.siretetablissement = etablissement.siretetablissement;
									row3.siretetablissementvalide = etablissement.siretetablissementvalide;
									row3.sirenetablissement = etablissement.sirenetablissement;
									row3.categorieetablissement = etablissement.categorieetablissement;
									row3.denominationsocialeetablissement = etablissement.denominationsocialeetablissement;
									row3.codecommuneetablissement = etablissement.codecommuneetablissement;
									row3.communeetablissement = etablissement.communeetablissement;
									row3.departementetablissement = etablissement.departementetablissement;
									row3.libelledepartementetablissement = etablissement.libelledepartementetablissement;
									row3.coderegionetablissement = etablissement.coderegionetablissement;
									row3.libelleregionetablissement = etablissement.libelleregionetablissement;
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
// Start of branch "row3"
								if (row3 != null) {

									/**
									 * [tDBCommit_1 main ] start
									 */

									currentComponent = "tDBCommit_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row3"

										);
									}

									java.sql.Connection conn_tDBCommit_1 = (java.sql.Connection) globalMap
											.get("conn_tDBConnection_2");
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

								} // End of branch "row3"

								/**
								 * [tDBOutput_3 process_data_end ] start
								 */

								currentComponent = "tDBOutput_3";

								/**
								 * [tDBOutput_3 process_data_end ] stop
								 */

							} // End of branch "etablissement"

// Start of branch "VerifJointure"
							if (VerifJointure != null) {

								/**
								 * [tWarn_1 main ] start
								 */

								currentComponent = "tWarn_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "VerifJointure"

									);
								}

								try {

									resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_1", "",
											Thread.currentThread().getId() + "", "TRACE", "",
											"DIM_etablissement : tentative d'insertion de doublon. Valeur => "
													+ VerifJointure.sirenetablissement + " / "
													+ VerifJointure.denominationsocialeetablissement,
											"", "");
									tLogCatcher_1.addMessage("tWarn", "tWarn_1", 1,
											"DIM_etablissement : tentative d'insertion de doublon. Valeur => "
													+ VerifJointure.sirenetablissement + " / "
													+ VerifJointure.denominationsocialeetablissement,
											42);
									tLogCatcher_1Process(globalMap);
									globalMap.put("tWarn_1_WARN_MESSAGES",
											"DIM_etablissement : tentative d'insertion de doublon. Valeur => "
													+ VerifJointure.sirenetablissement + " / "
													+ VerifJointure.denominationsocialeetablissement);
									globalMap.put("tWarn_1_WARN_PRIORITY", 1);
									globalMap.put("tWarn_1_WARN_CODE", 42);

								} catch (Exception e_tWarn_1) {
									globalMap.put("tWarn_1_ERROR_MESSAGE", e_tWarn_1.getMessage());
									logIgnoredError(String.format(
											"tWarn_1 - tWarn failed to log message due to internal error: %s",
											e_tWarn_1), e_tWarn_1);
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

							} // End of branch "VerifJointure"

							/**
							 * [tMap_2 process_data_end ] start
							 */

							currentComponent = "tMap_2";

							/**
							 * [tMap_2 process_data_end ] stop
							 */

						} // End of branch "DIM_ETABLISSEMENT"

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
				 * [tMap_3 end ] start
				 */

				currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row5 != null) {
					tHash_Lookup_row5.endGet();
				}
				globalMap.remove("tHash_Lookup_row5");

				tHash_Lookup_Cache_row5.endGet();
				tHash_Lookup_Cache_row5 = null;
				tHash_Lookup_Real_row5 = null;

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "DIM_ACHETEUR");
				}

				ok_Hash.put("tMap_3", true);
				end_Hash.put("tMap_3", System.currentTimeMillis());

				/**
				 * [tMap_3 end ] stop
				 */

				/**
				 * [tDBOutput_5 end ] start
				 */

				currentComponent = "tDBOutput_5";

				if (pstmtUpdate_tDBOutput_5 != null) {
					pstmtUpdate_tDBOutput_5.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_5");
				}
				if (pstmtInsert_tDBOutput_5 != null) {
					pstmtInsert_tDBOutput_5.close();
					resourceMap.remove("pstmtInsert_tDBOutput_5");
				}
				if (pstmt_tDBOutput_5 != null) {
					pstmt_tDBOutput_5.close();
					resourceMap.remove("pstmt_tDBOutput_5");
				}
				resourceMap.put("statementClosed_tDBOutput_5", true);

				nb_line_deleted_tDBOutput_5 = nb_line_deleted_tDBOutput_5 + deletedCount_tDBOutput_5;
				nb_line_update_tDBOutput_5 = nb_line_update_tDBOutput_5 + updatedCount_tDBOutput_5;
				nb_line_inserted_tDBOutput_5 = nb_line_inserted_tDBOutput_5 + insertedCount_tDBOutput_5;
				nb_line_rejected_tDBOutput_5 = nb_line_rejected_tDBOutput_5 + rejectedCount_tDBOutput_5;

				globalMap.put("tDBOutput_5_NB_LINE", nb_line_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_UPDATED", nb_line_update_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_DELETED", nb_line_deleted_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_5);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "acheteur");
				}

				ok_Hash.put("tDBOutput_5", true);
				end_Hash.put("tDBOutput_5", System.currentTimeMillis());

				/**
				 * [tDBOutput_5 end ] stop
				 */

				/**
				 * [tDBCommit_2 end ] start
				 */

				currentComponent = "tDBCommit_2";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
				}

				ok_Hash.put("tDBCommit_2", true);
				end_Hash.put("tDBCommit_2", System.currentTimeMillis());

				/**
				 * [tDBCommit_2 end ] stop
				 */

				/**
				 * [tWarn_2 end ] start
				 */

				currentComponent = "tWarn_2";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "verif");
				}

				ok_Hash.put("tWarn_2", true);
				end_Hash.put("tWarn_2", System.currentTimeMillis());

				/**
				 * [tWarn_2 end ] stop
				 */

				/**
				 * [tMap_5 end ] start
				 */

				currentComponent = "tMap_5";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row8 != null) {
					tHash_Lookup_row8.endGet();
				}
				globalMap.remove("tHash_Lookup_row8");

				tHash_Lookup_Cache_row8.endGet();
				tHash_Lookup_Cache_row8 = null;
				tHash_Lookup_Real_row8 = null;

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "DIM_MARCHE");
				}

				ok_Hash.put("tMap_5", true);
				end_Hash.put("tMap_5", System.currentTimeMillis());

				/**
				 * [tMap_5 end ] stop
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
				if (pstmt_tDBOutput_1 != null) {
					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "marche");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

				/**
				 * [tDBCommit_3 end ] start
				 */

				currentComponent = "tDBCommit_3";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row9");
				}

				ok_Hash.put("tDBCommit_3", true);
				end_Hash.put("tDBCommit_3", System.currentTimeMillis());

				/**
				 * [tDBCommit_3 end ] stop
				 */

				/**
				 * [tWarn_4 end ] start
				 */

				currentComponent = "tWarn_4";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "doublons");
				}

				ok_Hash.put("tWarn_4", true);
				end_Hash.put("tWarn_4", System.currentTimeMillis());

				/**
				 * [tWarn_4 end ] stop
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

				tHash_Lookup_Cache_row2.endGet();
				tHash_Lookup_Cache_row2 = null;
				tHash_Lookup_Real_row2 = null;

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "DIM_ETABLISSEMENT");
				}

				ok_Hash.put("tMap_2", true);
				end_Hash.put("tMap_2", System.currentTimeMillis());

				/**
				 * [tMap_2 end ] stop
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
				if (pstmt_tDBOutput_3 != null) {
					pstmt_tDBOutput_3.close();
					resourceMap.remove("pstmt_tDBOutput_3");
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
				 * [tDBCommit_1 end ] start
				 */

				currentComponent = "tDBCommit_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tDBCommit_1", true);
				end_Hash.put("tDBCommit_1", System.currentTimeMillis());

				/**
				 * [tDBCommit_1 end ] stop
				 */

				/**
				 * [tWarn_1 end ] start
				 */

				currentComponent = "tWarn_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "VerifJointure");
				}

				ok_Hash.put("tWarn_1", true);
				end_Hash.put("tWarn_1", System.currentTimeMillis());

				/**
				 * [tWarn_1 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tDBInput_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
			}

			tDBInput_4Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row2");

			// free memory for "tMap_5"
			globalMap.remove("tHash_Lookup_row8");

			// free memory for "tMap_3"
			globalMap.remove("tHash_Lookup_row5");

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
				 * [tMap_3 finally ] start
				 */

				currentComponent = "tMap_3";

				/**
				 * [tMap_3 finally ] stop
				 */

				/**
				 * [tDBOutput_5 finally ] start
				 */

				currentComponent = "tDBOutput_5";

				if (resourceMap.get("statementClosed_tDBOutput_5") == null) {
					java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_5 = null;
					if ((pstmtUpdateToClose_tDBOutput_5 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmtUpdate_tDBOutput_5")) != null) {
						pstmtUpdateToClose_tDBOutput_5.close();
					}
					java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_5 = null;
					if ((pstmtInsertToClose_tDBOutput_5 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmtInsert_tDBOutput_5")) != null) {
						pstmtInsertToClose_tDBOutput_5.close();
					}
					java.sql.PreparedStatement pstmtToClose_tDBOutput_5 = null;
					if ((pstmtToClose_tDBOutput_5 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_tDBOutput_5")) != null) {
						pstmtToClose_tDBOutput_5.close();
					}
				}

				/**
				 * [tDBOutput_5 finally ] stop
				 */

				/**
				 * [tDBCommit_2 finally ] start
				 */

				currentComponent = "tDBCommit_2";

				/**
				 * [tDBCommit_2 finally ] stop
				 */

				/**
				 * [tWarn_2 finally ] start
				 */

				currentComponent = "tWarn_2";

				/**
				 * [tWarn_2 finally ] stop
				 */

				/**
				 * [tMap_5 finally ] start
				 */

				currentComponent = "tMap_5";

				/**
				 * [tMap_5 finally ] stop
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
					java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
					if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_tDBOutput_1")) != null) {
						pstmtToClose_tDBOutput_1.close();
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

				/**
				 * [tDBCommit_3 finally ] start
				 */

				currentComponent = "tDBCommit_3";

				/**
				 * [tDBCommit_3 finally ] stop
				 */

				/**
				 * [tWarn_4 finally ] start
				 */

				currentComponent = "tWarn_4";

				/**
				 * [tWarn_4 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
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
					java.sql.PreparedStatement pstmtToClose_tDBOutput_3 = null;
					if ((pstmtToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_tDBOutput_3")) != null) {
						pstmtToClose_tDBOutput_3.close();
					}
				}

				/**
				 * [tDBOutput_3 finally ] stop
				 */

				/**
				 * [tDBCommit_1 finally ] start
				 */

				currentComponent = "tDBCommit_1";

				/**
				 * [tDBCommit_1 finally ] stop
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

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}

	public static class row11Struct implements routines.system.IPersistableRow<row11Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
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

		public java.util.Date dateNotification;

		public java.util.Date getDateNotification() {
			return this.dateNotification;
		}

		public String idmarche;

		public String getIdmarche() {
			return this.idmarche;
		}

		public String montantmarche;

		public String getMontantmarche() {
			return this.montantmarche;
		}

		public String montantcalculemarche;

		public String getMontantcalculemarche() {
			return this.montantcalculemarche;
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
			final row11Struct other = (row11Struct) obj;

			if (this.idmarche == null) {
				if (other.idmarche != null)
					return false;

			} else if (!this.idmarche.equals(other.idmarche))

				return false;

			return true;
		}

		public void copyDataTo(row11Struct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.idacheteur = this.idacheteur;
			other.dateNotification = this.dateNotification;
			other.idmarche = this.idmarche;
			other.montantmarche = this.montantmarche;
			other.montantcalculemarche = this.montantcalculemarche;

		}

		public void copyKeysDataTo(row11Struct other) {

			other.idmarche = this.idmarche;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.idacheteur = readString(dis);

					this.dateNotification = readDate(dis);

					this.idmarche = readString(dis);

					this.montantmarche = readString(dis);

					this.montantcalculemarche = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.idacheteur = readString(dis);

					this.dateNotification = readDate(dis);

					this.idmarche = readString(dis);

					this.montantmarche = readString(dis);

					this.montantcalculemarche = readString(dis);

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

				// java.util.Date

				writeDate(this.dateNotification, dos);

				// String

				writeString(this.idmarche, dos);

				// String

				writeString(this.montantmarche, dos);

				// String

				writeString(this.montantcalculemarche, dos);

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

				// java.util.Date

				writeDate(this.dateNotification, dos);

				// String

				writeString(this.idmarche, dos);

				// String

				writeString(this.montantmarche, dos);

				// String

				writeString(this.montantcalculemarche, dos);

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
			sb.append(",dateNotification=" + String.valueOf(dateNotification));
			sb.append(",idmarche=" + idmarche);
			sb.append(",montantmarche=" + montantmarche);
			sb.append(",montantcalculemarche=" + montantcalculemarche);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row11Struct other) {

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

	public static class row12Struct implements routines.system.IPersistableRow<row12Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
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

		public java.util.Date dateNotification;

		public java.util.Date getDateNotification() {
			return this.dateNotification;
		}

		public String idmarche;

		public String getIdmarche() {
			return this.idmarche;
		}

		public String montantmarche;

		public String getMontantmarche() {
			return this.montantmarche;
		}

		public String montantcalculemarche;

		public String getMontantcalculemarche() {
			return this.montantcalculemarche;
		}

		public String errorCode;

		public String getErrorCode() {
			return this.errorCode;
		}

		public String errorMessage;

		public String getErrorMessage() {
			return this.errorMessage;
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
			final row12Struct other = (row12Struct) obj;

			if (this.idmarche == null) {
				if (other.idmarche != null)
					return false;

			} else if (!this.idmarche.equals(other.idmarche))

				return false;

			return true;
		}

		public void copyDataTo(row12Struct other) {

			other.sirenetablissement = this.sirenetablissement;
			other.idacheteur = this.idacheteur;
			other.dateNotification = this.dateNotification;
			other.idmarche = this.idmarche;
			other.montantmarche = this.montantmarche;
			other.montantcalculemarche = this.montantcalculemarche;
			other.errorCode = this.errorCode;
			other.errorMessage = this.errorMessage;

		}

		public void copyKeysDataTo(row12Struct other) {

			other.idmarche = this.idmarche;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.idacheteur = readString(dis);

					this.dateNotification = readDate(dis);

					this.idmarche = readString(dis);

					this.montantmarche = readString(dis);

					this.montantcalculemarche = readString(dis);

					this.errorCode = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.idacheteur = readString(dis);

					this.dateNotification = readDate(dis);

					this.idmarche = readString(dis);

					this.montantmarche = readString(dis);

					this.montantcalculemarche = readString(dis);

					this.errorCode = readString(dis);

					this.errorMessage = readString(dis);

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

				// java.util.Date

				writeDate(this.dateNotification, dos);

				// String

				writeString(this.idmarche, dos);

				// String

				writeString(this.montantmarche, dos);

				// String

				writeString(this.montantcalculemarche, dos);

				// String

				writeString(this.errorCode, dos);

				// String

				writeString(this.errorMessage, dos);

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

				// java.util.Date

				writeDate(this.dateNotification, dos);

				// String

				writeString(this.idmarche, dos);

				// String

				writeString(this.montantmarche, dos);

				// String

				writeString(this.montantcalculemarche, dos);

				// String

				writeString(this.errorCode, dos);

				// String

				writeString(this.errorMessage, dos);

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
			sb.append(",dateNotification=" + String.valueOf(dateNotification));
			sb.append(",idmarche=" + idmarche);
			sb.append(",montantmarche=" + montantmarche);
			sb.append(",montantcalculemarche=" + montantcalculemarche);
			sb.append(",errorCode=" + errorCode);
			sb.append(",errorMessage=" + errorMessage);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row12Struct other) {

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

	public static class F_MARCHEStruct implements routines.system.IPersistableRow<F_MARCHEStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];

		public String sirenetablissement;

		public String getSirenetablissement() {
			return this.sirenetablissement;
		}

		public String idacheteur;

		public String getIdacheteur() {
			return this.idacheteur;
		}

		public java.util.Date dateNotification;

		public java.util.Date getDateNotification() {
			return this.dateNotification;
		}

		public String idmarche;

		public String getIdmarche() {
			return this.idmarche;
		}

		public String montantmarche;

		public String getMontantmarche() {
			return this.montantmarche;
		}

		public String montantcalculemarche;

		public String getMontantcalculemarche() {
			return this.montantcalculemarche;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.idacheteur = readString(dis);

					this.dateNotification = readDate(dis);

					this.idmarche = readString(dis);

					this.montantmarche = readString(dis);

					this.montantcalculemarche = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.sirenetablissement = readString(dis);

					this.idacheteur = readString(dis);

					this.dateNotification = readDate(dis);

					this.idmarche = readString(dis);

					this.montantmarche = readString(dis);

					this.montantcalculemarche = readString(dis);

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

				// java.util.Date

				writeDate(this.dateNotification, dos);

				// String

				writeString(this.idmarche, dos);

				// String

				writeString(this.montantmarche, dos);

				// String

				writeString(this.montantcalculemarche, dos);

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

				// java.util.Date

				writeDate(this.dateNotification, dos);

				// String

				writeString(this.idmarche, dos);

				// String

				writeString(this.montantmarche, dos);

				// String

				writeString(this.montantcalculemarche, dos);

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
			sb.append(",dateNotification=" + String.valueOf(dateNotification));
			sb.append(",idmarche=" + idmarche);
			sb.append(",montantmarche=" + montantmarche);
			sb.append(",montantcalculemarche=" + montantcalculemarche);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(F_MARCHEStruct other) {

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
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];

		public String recordid;

		public String getRecordid() {
			return this.recordid;
		}

		public Boolean sirenetablissementvalide;

		public Boolean getSirenetablissementvalide() {
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

		public Integer lieuexecutioncode;

		public Integer getLieuexecutioncode() {
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

		public String idmarche;

		public String getIdmarche() {
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

		public String montantcalcule;

		public String getMontantcalcule() {
			return this.montantcalcule;
		}

		public String libelledepartementacheteur;

		public String getLibelledepartementacheteur() {
			return this.libelledepartementacheteur;
		}

		public String codecpv_division;

		public String getCodecpv_division() {
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

		public java.util.Date datepublicationdonnees;

		public java.util.Date getDatepublicationdonnees() {
			return this.datepublicationdonnees;
		}

		public Boolean sirenacheteurvalide;

		public Boolean getSirenacheteurvalide() {
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

		public String codedepartementexecution;

		public String getCodedepartementexecution() {
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

		public String coderegionexecution;

		public String getCoderegionexecution() {
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.sirenetablissementvalide = null;
					} else {
						this.sirenetablissementvalide = dis.readBoolean();
					}

					this.denominationsocialeetablissement = readString(dis);

					this.libellecommuneacheteur = readString(dis);

					this.lieuexecutioncode = readInteger(dis);

					this.adresseetablissement = readString(dis);

					this.codepostalacheteur = readString(dis);

					this.libelleregionacheteur = readString(dis);

					this.departementacheteur = readString(dis);

					this.idmarche = readString(dis);

					this.dureemois = readInteger(dis);

					this.datenotification = readString(dis);

					this.montantcalcule = readString(dis);

					this.libelledepartementacheteur = readString(dis);

					this.codecpv_division = readString(dis);

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

					this.datepublicationdonnees = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.sirenacheteurvalide = null;
					} else {
						this.sirenacheteurvalide = dis.readBoolean();
					}

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.formeprix = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.codedepartementexecution = readString(dis);

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

					this.coderegionexecution = readString(dis);

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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.recordid = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.sirenetablissementvalide = null;
					} else {
						this.sirenetablissementvalide = dis.readBoolean();
					}

					this.denominationsocialeetablissement = readString(dis);

					this.libellecommuneacheteur = readString(dis);

					this.lieuexecutioncode = readInteger(dis);

					this.adresseetablissement = readString(dis);

					this.codepostalacheteur = readString(dis);

					this.libelleregionacheteur = readString(dis);

					this.departementacheteur = readString(dis);

					this.idmarche = readString(dis);

					this.dureemois = readInteger(dis);

					this.datenotification = readString(dis);

					this.montantcalcule = readString(dis);

					this.libelledepartementacheteur = readString(dis);

					this.codecpv_division = readString(dis);

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

					this.datepublicationdonnees = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.sirenacheteurvalide = null;
					} else {
						this.sirenacheteurvalide = dis.readBoolean();
					}

					this.moisnotification = readString(dis);

					this.coderegionetablissement = readString(dis);

					this.formeprix = readString(dis);

					this.siretetablissementvalide = readString(dis);

					this.codedepartementexecution = readString(dis);

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

					this.coderegionexecution = readString(dis);

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

				// Boolean

				if (this.sirenetablissementvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.sirenetablissementvalide);
				}

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.libellecommuneacheteur, dos);

				// Integer

				writeInteger(this.lieuexecutioncode, dos);

				// String

				writeString(this.adresseetablissement, dos);

				// String

				writeString(this.codepostalacheteur, dos);

				// String

				writeString(this.libelleregionacheteur, dos);

				// String

				writeString(this.departementacheteur, dos);

				// String

				writeString(this.idmarche, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.datenotification, dos);

				// String

				writeString(this.montantcalcule, dos);

				// String

				writeString(this.libelledepartementacheteur, dos);

				// String

				writeString(this.codecpv_division, dos);

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

				// java.util.Date

				writeDate(this.datepublicationdonnees, dos);

				// Boolean

				if (this.sirenacheteurvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.sirenacheteurvalide);
				}

				// String

				writeString(this.moisnotification, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.codedepartementexecution, dos);

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

				// String

				writeString(this.coderegionexecution, dos);

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

				// Boolean

				if (this.sirenetablissementvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.sirenetablissementvalide);
				}

				// String

				writeString(this.denominationsocialeetablissement, dos);

				// String

				writeString(this.libellecommuneacheteur, dos);

				// Integer

				writeInteger(this.lieuexecutioncode, dos);

				// String

				writeString(this.adresseetablissement, dos);

				// String

				writeString(this.codepostalacheteur, dos);

				// String

				writeString(this.libelleregionacheteur, dos);

				// String

				writeString(this.departementacheteur, dos);

				// String

				writeString(this.idmarche, dos);

				// Integer

				writeInteger(this.dureemois, dos);

				// String

				writeString(this.datenotification, dos);

				// String

				writeString(this.montantcalcule, dos);

				// String

				writeString(this.libelledepartementacheteur, dos);

				// String

				writeString(this.codecpv_division, dos);

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

				// java.util.Date

				writeDate(this.datepublicationdonnees, dos);

				// Boolean

				if (this.sirenacheteurvalide == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.sirenacheteurvalide);
				}

				// String

				writeString(this.moisnotification, dos);

				// String

				writeString(this.coderegionetablissement, dos);

				// String

				writeString(this.formeprix, dos);

				// String

				writeString(this.siretetablissementvalide, dos);

				// String

				writeString(this.codedepartementexecution, dos);

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

				// String

				writeString(this.coderegionexecution, dos);

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
			sb.append(",sirenetablissementvalide=" + String.valueOf(sirenetablissementvalide));
			sb.append(",denominationsocialeetablissement=" + denominationsocialeetablissement);
			sb.append(",libellecommuneacheteur=" + libellecommuneacheteur);
			sb.append(",lieuexecutioncode=" + String.valueOf(lieuexecutioncode));
			sb.append(",adresseetablissement=" + adresseetablissement);
			sb.append(",codepostalacheteur=" + codepostalacheteur);
			sb.append(",libelleregionacheteur=" + libelleregionacheteur);
			sb.append(",departementacheteur=" + departementacheteur);
			sb.append(",idmarche=" + idmarche);
			sb.append(",dureemois=" + String.valueOf(dureemois));
			sb.append(",datenotification=" + datenotification);
			sb.append(",montantcalcule=" + montantcalcule);
			sb.append(",libelledepartementacheteur=" + libelledepartementacheteur);
			sb.append(",codecpv_division=" + codecpv_division);
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
			sb.append(",datepublicationdonnees=" + String.valueOf(datepublicationdonnees));
			sb.append(",sirenacheteurvalide=" + String.valueOf(sirenacheteurvalide));
			sb.append(",moisnotification=" + moisnotification);
			sb.append(",coderegionetablissement=" + coderegionetablissement);
			sb.append(",formeprix=" + formeprix);
			sb.append(",siretetablissementvalide=" + siretetablissementvalide);
			sb.append(",codedepartementexecution=" + codedepartementexecution);
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
			sb.append(",coderegionexecution=" + coderegionexecution);
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

				row4Struct row4 = new row4Struct();
				F_MARCHEStruct F_MARCHE = new F_MARCHEStruct();
				row11Struct row11 = new row11Struct();
				row12Struct row12 = new row12Struct();

				/**
				 * [tDBCommit_4 begin ] start
				 */

				ok_Hash.put("tDBCommit_4", false);
				start_Hash.put("tDBCommit_4", System.currentTimeMillis());

				currentComponent = "tDBCommit_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row11");
				}

				int tos_count_tDBCommit_4 = 0;

				/**
				 * [tDBCommit_4 begin ] stop
				 */

				/**
				 * [tWarn_7 begin ] start
				 */

				ok_Hash.put("tWarn_7", false);
				start_Hash.put("tWarn_7", System.currentTimeMillis());

				currentComponent = "tWarn_7";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row12");
				}

				int tos_count_tWarn_7 = 0;

				/**
				 * [tWarn_7 begin ] stop
				 */

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "F_MARCHE");
				}

				int tos_count_tDBOutput_2 = 0;

				String dbschema_tDBOutput_2 = null;
				dbschema_tDBOutput_2 = (String) globalMap.get("schema_" + "tDBConnection_2");

				String tableName_tDBOutput_2 = null;
				if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
					tableName_tDBOutput_2 = ("fait_marche");
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "\".\"" + ("fait_marche");
				}

				int updateKeyCount_tDBOutput_2 = 1;
				if (updateKeyCount_tDBOutput_2 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_2 == 6 && true) {
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

				conn_tDBOutput_2 = (java.sql.Connection) globalMap.get("conn_tDBConnection_2");

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
						if (table_tDBOutput_2.equals(("fait_marche"))
								&& (schema_tDBOutput_2.equals(dbschema_tDBOutput_2)
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
								+ "\"(\"sirenEtablissement\" VARCHAR ,\"idAcheteur\" VARCHAR ,\"dateNotification\" TIMESTAMP ,\"idMarche\" VARCHAR ,\"montantMarche\" VARCHAR ,\"montantCalculeMarche\" VARCHAR ,primary key(\"idMarche\"))");
					}
				}
				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(
						"SELECT COUNT(1) FROM \"" + tableName_tDBOutput_2 + "\" WHERE \"idMarche\" = ?");
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);
				String insert_tDBOutput_2 = "INSERT INTO \"" + tableName_tDBOutput_2
						+ "\" (\"sirenEtablissement\",\"idAcheteur\",\"dateNotification\",\"idMarche\",\"montantMarche\",\"montantCalculeMarche\") VALUES (?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmtInsert_tDBOutput_2 = conn_tDBOutput_2
						.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmtInsert_tDBOutput_2", pstmtInsert_tDBOutput_2);
				String update_tDBOutput_2 = "UPDATE \"" + tableName_tDBOutput_2
						+ "\" SET \"sirenEtablissement\" = ?,\"idAcheteur\" = ?,\"dateNotification\" = ?,\"montantMarche\" = ?,\"montantCalculeMarche\" = ? WHERE \"idMarche\" = ?";
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_2 = conn_tDBOutput_2
						.prepareStatement(update_tDBOutput_2);
				resourceMap.put("pstmtUpdate_tDBOutput_2", pstmtUpdate_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tMap_6 begin ] start
				 */

				ok_Hash.put("tMap_6", false);
				start_Hash.put("tMap_6", System.currentTimeMillis());

				currentComponent = "tMap_6";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tMap_6 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_6__Struct {
				}
				Var__tMap_6__Struct Var__tMap_6 = new Var__tMap_6__Struct();
// ###############################

// ###############################
// # Outputs initialization
				F_MARCHEStruct F_MARCHE_tmp = new F_MARCHEStruct();
// ###############################

				/**
				 * [tMap_6 begin ] stop
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

				String dbquery_tDBInput_4 = "select * FROM \"marche\" LIMIT 5000";

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
							row4.recordid = null;
						} else {

							row4.recordid = routines.system.JDBCUtil.getString(rs_tDBInput_4, 1, false);
						}
						if (colQtyInRs_tDBInput_4 < 2) {
							row4.sirenetablissementvalide = null;
						} else {

							row4.sirenetablissementvalide = rs_tDBInput_4.getBoolean(2);
							if (rs_tDBInput_4.wasNull()) {
								row4.sirenetablissementvalide = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 3) {
							row4.denominationsocialeetablissement = null;
						} else {

							row4.denominationsocialeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 3,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 4) {
							row4.libellecommuneacheteur = null;
						} else {

							row4.libellecommuneacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_4, 4, false);
						}
						if (colQtyInRs_tDBInput_4 < 5) {
							row4.lieuexecutioncode = null;
						} else {

							row4.lieuexecutioncode = rs_tDBInput_4.getInt(5);
							if (rs_tDBInput_4.wasNull()) {
								row4.lieuexecutioncode = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 6) {
							row4.adresseetablissement = null;
						} else {

							row4.adresseetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 6, false);
						}
						if (colQtyInRs_tDBInput_4 < 7) {
							row4.codepostalacheteur = null;
						} else {

							row4.codepostalacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_4, 7, false);
						}
						if (colQtyInRs_tDBInput_4 < 8) {
							row4.libelleregionacheteur = null;
						} else {

							row4.libelleregionacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_4, 8, false);
						}
						if (colQtyInRs_tDBInput_4 < 9) {
							row4.departementacheteur = null;
						} else {

							row4.departementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_4, 9, false);
						}
						if (colQtyInRs_tDBInput_4 < 10) {
							row4.idmarche = null;
						} else {

							row4.idmarche = routines.system.JDBCUtil.getString(rs_tDBInput_4, 10, false);
						}
						if (colQtyInRs_tDBInput_4 < 11) {
							row4.dureemois = null;
						} else {

							row4.dureemois = rs_tDBInput_4.getInt(11);
							if (rs_tDBInput_4.wasNull()) {
								row4.dureemois = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 12) {
							row4.datenotification = null;
						} else {

							row4.datenotification = routines.system.JDBCUtil.getString(rs_tDBInput_4, 12, false);
						}
						if (colQtyInRs_tDBInput_4 < 13) {
							row4.montantcalcule = null;
						} else {

							row4.montantcalcule = routines.system.JDBCUtil.getString(rs_tDBInput_4, 13, false);
						}
						if (colQtyInRs_tDBInput_4 < 14) {
							row4.libelledepartementacheteur = null;
						} else {

							row4.libelledepartementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_4, 14,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 15) {
							row4.codecpv_division = null;
						} else {

							row4.codecpv_division = routines.system.JDBCUtil.getString(rs_tDBInput_4, 15, false);
						}
						if (colQtyInRs_tDBInput_4 < 16) {
							row4.lieuexecutionnom = null;
						} else {

							row4.lieuexecutionnom = routines.system.JDBCUtil.getString(rs_tDBInput_4, 16, false);
						}
						if (colQtyInRs_tDBInput_4 < 17) {
							row4.libellearrondissementacheteur = null;
						} else {

							row4.libellearrondissementacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_4, 17,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 18) {
							row4.populationcommuneetablissement = null;
						} else {

							row4.populationcommuneetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 18,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 19) {
							row4.codecommuneacheteur = null;
						} else {

							row4.codecommuneacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_4, 19, false);
						}
						if (colQtyInRs_tDBInput_4 < 20) {
							row4.superficiecommuneetablissement = null;
						} else {

							row4.superficiecommuneetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 20,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 21) {
							row4.nicetablissement = null;
						} else {

							row4.nicetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 21, false);
						}
						if (colQtyInRs_tDBInput_4 < 22) {
							row4.geoloccommuneetablissement___ = null;
						} else {

							row4.geoloccommuneetablissement___ = routines.system.JDBCUtil.getString(rs_tDBInput_4, 22,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 23) {
							row4.objetmarche = null;
						} else {

							row4.objetmarche = routines.system.JDBCUtil.getString(rs_tDBInput_4, 23, false);
						}
						if (colQtyInRs_tDBInput_4 < 24) {
							row4.nature = null;
						} else {

							row4.nature = routines.system.JDBCUtil.getString(rs_tDBInput_4, 24, false);
						}
						if (colQtyInRs_tDBInput_4 < 25) {
							row4.nombretitulairesurmarchepresume = null;
						} else {

							row4.nombretitulairesurmarchepresume = routines.system.JDBCUtil.getString(rs_tDBInput_4, 25,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 26) {
							row4.libelleregionetablissement = null;
						} else {

							row4.libelleregionetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 26,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 27) {
							row4.id = null;
						} else {

							row4.id = routines.system.JDBCUtil.getString(rs_tDBInput_4, 27, false);
						}
						if (colQtyInRs_tDBInput_4 < 28) {
							row4.source = null;
						} else {

							row4.source = routines.system.JDBCUtil.getString(rs_tDBInput_4, 28, false);
						}
						if (colQtyInRs_tDBInput_4 < 29) {
							row4.dureemoisestimee = null;
						} else {

							row4.dureemoisestimee = routines.system.JDBCUtil.getString(rs_tDBInput_4, 29, false);
						}
						if (colQtyInRs_tDBInput_4 < 30) {
							row4.nomacheteur = null;
						} else {

							row4.nomacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_4, 30, false);
						}
						if (colQtyInRs_tDBInput_4 < 31) {
							row4.referencecpv = null;
						} else {

							row4.referencecpv = routines.system.JDBCUtil.getString(rs_tDBInput_4, 31, false);
						}
						if (colQtyInRs_tDBInput_4 < 32) {
							row4.type = null;
						} else {

							row4.type = routines.system.JDBCUtil.getString(rs_tDBInput_4, 32, false);
						}
						if (colQtyInRs_tDBInput_4 < 33) {
							row4.sirenetablissement = null;
						} else {

							row4.sirenetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 33, false);
						}
						if (colQtyInRs_tDBInput_4 < 34) {
							row4.superficiecommuneacheteur = null;
						} else {

							row4.superficiecommuneacheteur = rs_tDBInput_4.getFloat(34);
							if (rs_tDBInput_4.wasNull()) {
								row4.superficiecommuneacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 35) {
							row4.codearrondissementacheteur = null;
						} else {

							row4.codearrondissementacheteur = rs_tDBInput_4.getInt(35);
							if (rs_tDBInput_4.wasNull()) {
								row4.codearrondissementacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 36) {
							row4.distanceacheteuretablissement = null;
						} else {

							row4.distanceacheteuretablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 36,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 37) {
							row4.communeetablissement = null;
						} else {

							row4.communeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 37, false);
						}
						if (colQtyInRs_tDBInput_4 < 38) {
							row4.montant = null;
						} else {

							row4.montant = routines.system.JDBCUtil.getString(rs_tDBInput_4, 38, false);
						}
						if (colQtyInRs_tDBInput_4 < 39) {
							row4.departementetablissement = null;
						} else {

							row4.departementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 39,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 40) {
							row4.datepublicationdonnees = null;
						} else {

							row4.datepublicationdonnees = routines.system.JDBCUtil.getDate(rs_tDBInput_4, 40);
						}
						if (colQtyInRs_tDBInput_4 < 41) {
							row4.sirenacheteurvalide = null;
						} else {

							row4.sirenacheteurvalide = rs_tDBInput_4.getBoolean(41);
							if (rs_tDBInput_4.wasNull()) {
								row4.sirenacheteurvalide = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 42) {
							row4.moisnotification = null;
						} else {

							row4.moisnotification = routines.system.JDBCUtil.getString(rs_tDBInput_4, 42, false);
						}
						if (colQtyInRs_tDBInput_4 < 43) {
							row4.coderegionetablissement = null;
						} else {

							row4.coderegionetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 43, false);
						}
						if (colQtyInRs_tDBInput_4 < 44) {
							row4.formeprix = null;
						} else {

							row4.formeprix = routines.system.JDBCUtil.getString(rs_tDBInput_4, 44, false);
						}
						if (colQtyInRs_tDBInput_4 < 45) {
							row4.siretetablissementvalide = null;
						} else {

							row4.siretetablissementvalide = routines.system.JDBCUtil.getString(rs_tDBInput_4, 45,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 46) {
							row4.codedepartementexecution = null;
						} else {

							row4.codedepartementexecution = routines.system.JDBCUtil.getString(rs_tDBInput_4, 46,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 47) {
							row4.dist = null;
						} else {

							row4.dist = rs_tDBInput_4.getFloat(47);
							if (rs_tDBInput_4.wasNull()) {
								row4.dist = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 48) {
							row4.coderegionacheteur = null;
						} else {

							row4.coderegionacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_4, 48, false);
						}
						if (colQtyInRs_tDBInput_4 < 49) {
							row4.lieuexecutiontypecode = null;
						} else {

							row4.lieuexecutiontypecode = routines.system.JDBCUtil.getString(rs_tDBInput_4, 49, false);
						}
						if (colQtyInRs_tDBInput_4 < 50) {
							row4.codecommuneetablissement = null;
						} else {

							row4.codecommuneetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 50,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 51) {
							row4.populationcommuneacheteur = null;
						} else {

							row4.populationcommuneacheteur = rs_tDBInput_4.getFloat(51);
							if (rs_tDBInput_4.wasNull()) {
								row4.populationcommuneacheteur = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 52) {
							row4.idacheteur = null;
						} else {

							row4.idacheteur = routines.system.JDBCUtil.getString(rs_tDBInput_4, 52, false);
						}
						if (colQtyInRs_tDBInput_4 < 53) {
							row4.codecpv_original = null;
						} else {

							row4.codecpv_original = routines.system.JDBCUtil.getString(rs_tDBInput_4, 53, false);
						}
						if (colQtyInRs_tDBInput_4 < 54) {
							row4.siretetablissement = null;
						} else {

							row4.siretetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 54, false);
						}
						if (colQtyInRs_tDBInput_4 < 55) {
							row4.libelledepartementetablissement = null;
						} else {

							row4.libelledepartementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 55,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 56) {
							row4.typeidentifiantetablissement = null;
						} else {

							row4.typeidentifiantetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 56,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 57) {
							row4.categorieetablissement = null;
						} else {

							row4.categorieetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 57, false);
						}
						if (colQtyInRs_tDBInput_4 < 58) {
							row4.coderegionexecution = null;
						} else {

							row4.coderegionexecution = routines.system.JDBCUtil.getString(rs_tDBInput_4, 58, false);
						}
						if (colQtyInRs_tDBInput_4 < 59) {
							row4.codepostaletablissement = null;
						} else {

							row4.codepostaletablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 59, false);
						}
						if (colQtyInRs_tDBInput_4 < 60) {
							row4.natureobjetmarche = null;
						} else {

							row4.natureobjetmarche = routines.system.JDBCUtil.getString(rs_tDBInput_4, 60, false);
						}
						if (colQtyInRs_tDBInput_4 < 61) {
							row4.dureemoiscalculee = null;
						} else {

							row4.dureemoiscalculee = rs_tDBInput_4.getFloat(61);
							if (rs_tDBInput_4.wasNull()) {
								row4.dureemoiscalculee = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 62) {
							row4.codetypeetablissement = null;
						} else {

							row4.codetypeetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 62, false);
						}
						if (colQtyInRs_tDBInput_4 < 63) {
							row4.geoloccommuneacheteur___ = null;
						} else {

							row4.geoloccommuneacheteur___ = routines.system.JDBCUtil.getString(rs_tDBInput_4, 63,
									false);
						}
						if (colQtyInRs_tDBInput_4 < 64) {
							row4.codecpv = null;
						} else {

							row4.codecpv = routines.system.JDBCUtil.getString(rs_tDBInput_4, 64, false);
						}
						if (colQtyInRs_tDBInput_4 < 65) {
							row4.anneenotification = null;
						} else {

							row4.anneenotification = routines.system.JDBCUtil.getString(rs_tDBInput_4, 65, false);
						}
						if (colQtyInRs_tDBInput_4 < 66) {
							row4.libelleregionexecution = null;
						} else {

							row4.libelleregionexecution = routines.system.JDBCUtil.getString(rs_tDBInput_4, 66, false);
						}
						if (colQtyInRs_tDBInput_4 < 67) {
							row4.procedure = null;
						} else {

							row4.procedure = routines.system.JDBCUtil.getString(rs_tDBInput_4, 67, false);
						}
						if (colQtyInRs_tDBInput_4 < 68) {
							row4.libellearrondissementetablissement = null;
						} else {

							row4.libellearrondissementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4,
									68, false);
						}
						if (colQtyInRs_tDBInput_4 < 69) {
							row4.codearrondissementetablissement = null;
						} else {

							row4.codearrondissementetablissement = routines.system.JDBCUtil.getString(rs_tDBInput_4, 69,
									false);
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
						 * [tMap_6 main ] start
						 */

						currentComponent = "tMap_6";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row4"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_6 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_6 = false;
						boolean mainRowRejected_tMap_6 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_6__Struct Var = Var__tMap_6;// ###############################
							// ###############################
							// # Output tables

							F_MARCHE = null;

// # Output table : 'F_MARCHE'
							F_MARCHE_tmp.sirenetablissement = row4.sirenetablissement;
							F_MARCHE_tmp.idacheteur = row4.idacheteur;
							F_MARCHE_tmp.dateNotification = row4.datepublicationdonnees;
							F_MARCHE_tmp.idmarche = row4.idmarche;
							F_MARCHE_tmp.montantmarche = row4.montant;
							F_MARCHE_tmp.montantcalculemarche = row4.montantcalcule;
							F_MARCHE = F_MARCHE_tmp;
// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_6 = false;

						tos_count_tMap_6++;

						/**
						 * [tMap_6 main ] stop
						 */

						/**
						 * [tMap_6 process_data_begin ] start
						 */

						currentComponent = "tMap_6";

						/**
						 * [tMap_6 process_data_begin ] stop
						 */
// Start of branch "F_MARCHE"
						if (F_MARCHE != null) {
							row12 = null;

							/**
							 * [tDBOutput_2 main ] start
							 */

							currentComponent = "tDBOutput_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "F_MARCHE"

								);
							}

							row11 = null;
							row12 = null;
							whetherReject_tDBOutput_2 = false;
							if (F_MARCHE.idmarche == null) {
								pstmt_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
							} else {
								pstmt_tDBOutput_2.setString(1, F_MARCHE.idmarche);
							}

							int checkCount_tDBOutput_2 = -1;
							try (java.sql.ResultSet rs_tDBOutput_2 = pstmt_tDBOutput_2.executeQuery()) {
								while (rs_tDBOutput_2.next()) {
									checkCount_tDBOutput_2 = rs_tDBOutput_2.getInt(1);
								}
							}
							if (checkCount_tDBOutput_2 > 0) {
								if (F_MARCHE.sirenetablissement == null) {
									pstmtUpdate_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(1, F_MARCHE.sirenetablissement);
								}

								if (F_MARCHE.idacheteur == null) {
									pstmtUpdate_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(2, F_MARCHE.idacheteur);
								}

								if (F_MARCHE.dateNotification != null) {
									pstmtUpdate_tDBOutput_2.setTimestamp(3,
											new java.sql.Timestamp(F_MARCHE.dateNotification.getTime()));
								} else {
									pstmtUpdate_tDBOutput_2.setNull(3, java.sql.Types.TIMESTAMP);
								}

								if (F_MARCHE.montantmarche == null) {
									pstmtUpdate_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(4, F_MARCHE.montantmarche);
								}

								if (F_MARCHE.montantcalculemarche == null) {
									pstmtUpdate_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(5, F_MARCHE.montantcalculemarche);
								}

								if (F_MARCHE.idmarche == null) {
									pstmtUpdate_tDBOutput_2.setNull(6 + count_tDBOutput_2, java.sql.Types.VARCHAR);
								} else {
									pstmtUpdate_tDBOutput_2.setString(6 + count_tDBOutput_2, F_MARCHE.idmarche);
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
									row12 = new row12Struct();
									row12.sirenetablissement = F_MARCHE.sirenetablissement;
									row12.idacheteur = F_MARCHE.idacheteur;
									row12.dateNotification = F_MARCHE.dateNotification;
									row12.idmarche = F_MARCHE.idmarche;
									row12.montantmarche = F_MARCHE.montantmarche;
									row12.montantcalculemarche = F_MARCHE.montantcalculemarche;
									rejectedCount_tDBOutput_2 = rejectedCount_tDBOutput_2 + 1;
									row12.errorCode = ((java.sql.SQLException) e).getSQLState();
									row12.errorMessage = e.getMessage() + " - Line: " + tos_count_tDBOutput_2;
								}
							} else {
								if (F_MARCHE.sirenetablissement == null) {
									pstmtInsert_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(1, F_MARCHE.sirenetablissement);
								}

								if (F_MARCHE.idacheteur == null) {
									pstmtInsert_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(2, F_MARCHE.idacheteur);
								}

								if (F_MARCHE.dateNotification != null) {
									pstmtInsert_tDBOutput_2.setTimestamp(3,
											new java.sql.Timestamp(F_MARCHE.dateNotification.getTime()));
								} else {
									pstmtInsert_tDBOutput_2.setNull(3, java.sql.Types.TIMESTAMP);
								}

								if (F_MARCHE.idmarche == null) {
									pstmtInsert_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(4, F_MARCHE.idmarche);
								}

								if (F_MARCHE.montantmarche == null) {
									pstmtInsert_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(5, F_MARCHE.montantmarche);
								}

								if (F_MARCHE.montantcalculemarche == null) {
									pstmtInsert_tDBOutput_2.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmtInsert_tDBOutput_2.setString(6, F_MARCHE.montantcalculemarche);
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
									row12 = new row12Struct();
									row12.sirenetablissement = F_MARCHE.sirenetablissement;
									row12.idacheteur = F_MARCHE.idacheteur;
									row12.dateNotification = F_MARCHE.dateNotification;
									row12.idmarche = F_MARCHE.idmarche;
									row12.montantmarche = F_MARCHE.montantmarche;
									row12.montantcalculemarche = F_MARCHE.montantcalculemarche;
									rejectedCount_tDBOutput_2 = rejectedCount_tDBOutput_2 + 1;
									row12.errorCode = ((java.sql.SQLException) e).getSQLState();
									row12.errorMessage = e.getMessage() + " - Line: " + tos_count_tDBOutput_2;
								}
							}
							if (!whetherReject_tDBOutput_2) {
								row11 = new row11Struct();
								row11.sirenetablissement = F_MARCHE.sirenetablissement;
								row11.idacheteur = F_MARCHE.idacheteur;
								row11.dateNotification = F_MARCHE.dateNotification;
								row11.idmarche = F_MARCHE.idmarche;
								row11.montantmarche = F_MARCHE.montantmarche;
								row11.montantcalculemarche = F_MARCHE.montantcalculemarche;
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
// Start of branch "row11"
							if (row11 != null) {

								/**
								 * [tDBCommit_4 main ] start
								 */

								currentComponent = "tDBCommit_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row11"

									);
								}

								java.sql.Connection conn_tDBCommit_4 = (java.sql.Connection) globalMap
										.get("conn_tDBConnection_2");
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

							} // End of branch "row11"

// Start of branch "row12"
							if (row12 != null) {

								/**
								 * [tWarn_7 main ] start
								 */

								currentComponent = "tWarn_7";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row12"

									);
								}

								try {

									resumeUtil.addLog("USER_DEF_LOG", "NODE:tWarn_7", "",
											Thread.currentThread().getId() + "", "WARN", "", row12.errorMessage, "",
											"");
									tLogCatcher_1.addMessage("tWarn", "tWarn_7", 4, row12.errorMessage, 42);
									tLogCatcher_1Process(globalMap);
									globalMap.put("tWarn_7_WARN_MESSAGES", row12.errorMessage);
									globalMap.put("tWarn_7_WARN_PRIORITY", 4);
									globalMap.put("tWarn_7_WARN_CODE", 42);

								} catch (Exception e_tWarn_7) {
									globalMap.put("tWarn_7_ERROR_MESSAGE", e_tWarn_7.getMessage());
									logIgnoredError(String.format(
											"tWarn_7 - tWarn failed to log message due to internal error: %s",
											e_tWarn_7), e_tWarn_7);
								}

								tos_count_tWarn_7++;

								/**
								 * [tWarn_7 main ] stop
								 */

								/**
								 * [tWarn_7 process_data_begin ] start
								 */

								currentComponent = "tWarn_7";

								/**
								 * [tWarn_7 process_data_begin ] stop
								 */

								/**
								 * [tWarn_7 process_data_end ] start
								 */

								currentComponent = "tWarn_7";

								/**
								 * [tWarn_7 process_data_end ] stop
								 */

							} // End of branch "row12"

							/**
							 * [tDBOutput_2 process_data_end ] start
							 */

							currentComponent = "tDBOutput_2";

							/**
							 * [tDBOutput_2 process_data_end ] stop
							 */

						} // End of branch "F_MARCHE"

						/**
						 * [tMap_6 process_data_end ] start
						 */

						currentComponent = "tMap_6";

						/**
						 * [tMap_6 process_data_end ] stop
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
				 * [tMap_6 end ] start
				 */

				currentComponent = "tMap_6";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tMap_6", true);
				end_Hash.put("tMap_6", System.currentTimeMillis());

				/**
				 * [tMap_6 end ] stop
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "F_MARCHE");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

				/**
				 * [tDBCommit_4 end ] start
				 */

				currentComponent = "tDBCommit_4";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row11");
				}

				ok_Hash.put("tDBCommit_4", true);
				end_Hash.put("tDBCommit_4", System.currentTimeMillis());

				/**
				 * [tDBCommit_4 end ] stop
				 */

				/**
				 * [tWarn_7 end ] start
				 */

				currentComponent = "tWarn_7";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row12");
				}

				ok_Hash.put("tWarn_7", true);
				end_Hash.put("tWarn_7", System.currentTimeMillis());

				/**
				 * [tWarn_7 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tDBInput_4:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk2", 0, "ok");
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
				 * [tDBInput_4 finally ] start
				 */

				currentComponent = "tDBInput_4";

				/**
				 * [tDBInput_4 finally ] stop
				 */

				/**
				 * [tMap_6 finally ] start
				 */

				currentComponent = "tMap_6";

				/**
				 * [tMap_6 finally ] stop
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
				 * [tDBCommit_4 finally ] start
				 */

				currentComponent = "tDBCommit_4";

				/**
				 * [tDBCommit_4 finally ] stop
				 */

				/**
				 * [tWarn_7 finally ] start
				 */

				currentComponent = "tWarn_7";

				/**
				 * [tWarn_7 finally ] stop
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
							"", " --------------  CITYVIZ : Data warehouse loaded with success -------------- \n "
									+ API.getwrongBoolean(),
							"", "");
					tLogCatcher_1.addMessage("tWarn", "tWarn_6", 4,
							" --------------  CITYVIZ : Data warehouse loaded with success -------------- \n "
									+ API.getwrongBoolean(),
							42);
					tLogCatcher_1Process(globalMap);
					globalMap.put("tWarn_6_WARN_MESSAGES",
							" --------------  CITYVIZ : Data warehouse loaded with success -------------- \n "
									+ API.getwrongBoolean());
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
					runStat.updateStatOnConnection("OnComponentOk4", 0, "ok");
				}
				tDBClose_1Process(globalMap);

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

				String dbProperties_tDBConnection_2 = "";
				String url_tDBConnection_2 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "dwh";

				if (dbProperties_tDBConnection_2 != null && !"".equals(dbProperties_tDBConnection_2.trim())) {
					url_tDBConnection_2 = url_tDBConnection_2 + "?" + dbProperties_tDBConnection_2;
				}
				String dbUser_tDBConnection_2 = "postgres";

				final String decryptedPassword_tDBConnection_2 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:6zCnJ/GZ5oUKZGu2AmhYTnNsHikzFJfVP88pUOLFJGM=");
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

				globalMap.put("schema_" + "tDBConnection_2", "");

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
				tDBConnection_1Process(globalMap);

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
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
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
			final row2Struct other = (row2Struct) obj;

			if (this.siren == null) {
				if (other.siren != null)
					return false;

			} else if (!this.siren.equals(other.siren))

				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.siren = this.siren;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.siren = this.siren;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.siren = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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
		public int compareTo(row2Struct other) {

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
				// linked node: tMap_2 - inputs:(DIM_ETABLISSEMENT,row2)
				// outputs:(etablissement,VerifJointure)

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
				conn_tDBInput_2 = (java.sql.Connection) globalMap.get("conn_tDBConnection_2");

				java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

				String dbquery_tDBInput_2 = "SELECT \"sirenEtablissement\" FROM \"dim_etablissement\"";

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
							row2.siren = null;
						} else {

							row2.siren = routines.system.JDBCUtil.getString(rs_tDBInput_2, 1, false);
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

						row2_HashRow.siren = row2.siren;

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

	public static class row5Struct implements routines.system.IPersistableComparableLookupRow<row5Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
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
			final row5Struct other = (row5Struct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.id = this.id;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.id = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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
		public int compareTo(row5Struct other) {

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

				row5Struct row5 = new row5Struct();

				/**
				 * [tAdvancedHash_row5 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row5", false);
				start_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tAdvancedHash_row5 = 0;

				// connection name:row5
				// source node:tDBInput_3 - inputs:() outputs:(row5,row5) | target
				// node:tAdvancedHash_row5 - inputs:(row5) outputs:()
				// linked node: tMap_3 - inputs:(DIM_ACHETEUR,row5) outputs:(acheteur,verif)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row5Struct>getLookup(matchingModeEnum_row5);

				globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);

				/**
				 * [tAdvancedHash_row5 begin ] stop
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
				conn_tDBInput_3 = (java.sql.Connection) globalMap.get("conn_tDBConnection_2");

				java.sql.Statement stmt_tDBInput_3 = conn_tDBInput_3.createStatement();

				String dbquery_tDBInput_3 = "SELECT \"idAcheteur\" FROM \"dim_acheteur\"";

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
							row5.id = null;
						} else {

							row5.id = routines.system.JDBCUtil.getString(rs_tDBInput_3, 1, false);
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
						 * [tAdvancedHash_row5 main ] start
						 */

						currentComponent = "tAdvancedHash_row5";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row5"

							);
						}

						row5Struct row5_HashRow = new row5Struct();

						row5_HashRow.id = row5.id;

						tHash_Lookup_row5.put(row5_HashRow);

						tos_count_tAdvancedHash_row5++;

						/**
						 * [tAdvancedHash_row5 main ] stop
						 */

						/**
						 * [tAdvancedHash_row5 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row5";

						/**
						 * [tAdvancedHash_row5 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row5 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row5";

						/**
						 * [tAdvancedHash_row5 process_data_end ] stop
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
				 * [tAdvancedHash_row5 end ] start
				 */

				currentComponent = "tAdvancedHash_row5";

				tHash_Lookup_row5.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tAdvancedHash_row5", true);
				end_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row5 end ] stop
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
				 * [tAdvancedHash_row5 finally ] start
				 */

				currentComponent = "tAdvancedHash_row5";

				/**
				 * [tAdvancedHash_row5 finally ] stop
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

	public static class row8Struct implements routines.system.IPersistableComparableLookupRow<row8Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];
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
			final row8Struct other = (row8Struct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(row8Struct other) {

			other.id = this.id;

		}

		public void copyKeysDataTo(row8Struct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.id = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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
		public int compareTo(row8Struct other) {

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

				/**
				 * [tAdvancedHash_row8 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row8", false);
				start_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row8";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row8");
				}

				int tos_count_tAdvancedHash_row8 = 0;

				// connection name:row8
				// source node:tDBInput_5 - inputs:() outputs:(row8,row8) | target
				// node:tAdvancedHash_row8 - inputs:(row8) outputs:()
				// linked node: tMap_5 - inputs:(DIM_MARCHE,row8) outputs:(marche,doublons)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row8 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row8Struct>getLookup(matchingModeEnum_row8);

				globalMap.put("tHash_Lookup_row8", tHash_Lookup_row8);

				/**
				 * [tAdvancedHash_row8 begin ] stop
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

				String dbquery_tDBInput_5 = "SELECT \"idMarche\" FROM \"dim_marche\"";

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
							row8.id = null;
						} else {

							row8.id = routines.system.JDBCUtil.getString(rs_tDBInput_5, 1, false);
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
						 * [tAdvancedHash_row8 main ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row8"

							);
						}

						row8Struct row8_HashRow = new row8Struct();

						row8_HashRow.id = row8.id;

						tHash_Lookup_row8.put(row8_HashRow);

						tos_count_tAdvancedHash_row8++;

						/**
						 * [tAdvancedHash_row8 main ] stop
						 */

						/**
						 * [tAdvancedHash_row8 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						/**
						 * [tAdvancedHash_row8 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row8 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						/**
						 * [tAdvancedHash_row8 process_data_end ] stop
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
				 * [tAdvancedHash_row8 end ] start
				 */

				currentComponent = "tAdvancedHash_row8";

				tHash_Lookup_row8.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row8");
				}

				ok_Hash.put("tAdvancedHash_row8", true);
				end_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row8 end ] stop
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
				 * [tDBInput_5 finally ] start
				 */

				currentComponent = "tDBInput_5";

				/**
				 * [tDBInput_5 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row8 finally ] start
				 */

				currentComponent = "tAdvancedHash_row8";

				/**
				 * [tAdvancedHash_row8 finally ] stop
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

	public static class outStruct implements routines.system.IPersistableRow<outStruct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];

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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

				try {

					int length = 0;

					this.message = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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
		public int compareTo(outStruct other) {

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

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_CITYVIZ_alim_DWH = new byte[0];
		static byte[] commonByteArray_CITYVIZ_alim_DWH = new byte[0];

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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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
				if (length > commonByteArray_CITYVIZ_alim_DWH.length) {
					if (length < 1024 && commonByteArray_CITYVIZ_alim_DWH.length == 0) {
						commonByteArray_CITYVIZ_alim_DWH = new byte[1024];
					} else {
						commonByteArray_CITYVIZ_alim_DWH = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_CITYVIZ_alim_DWH, 0, length);
				strReturn = new String(commonByteArray_CITYVIZ_alim_DWH, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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

			synchronized (commonByteArrayLock_CITYVIZ_alim_DWH) {

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
		public int compareTo(row7Struct other) {

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

				row7Struct row7 = new row7Struct();
				outStruct out = new outStruct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out");
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
				 * [tMap_4 begin ] start
				 */

				ok_Hash.put("tMap_4", false);
				start_Hash.put("tMap_4", System.currentTimeMillis());

				currentComponent = "tMap_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row7");
				}

				int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				outStruct out_tmp = new outStruct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
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
						row7.type = lcm.getType();
						row7.origin = (lcm.getOrigin() == null || lcm.getOrigin().length() < 1 ? null
								: lcm.getOrigin());
						row7.priority = lcm.getPriority();
						row7.message = lcm.getMessage();
						row7.code = lcm.getCode();

						row7.moment = java.util.Calendar.getInstance().getTime();

						row7.pid = pid;
						row7.root_pid = rootPid;
						row7.father_pid = fatherPid;

						row7.project = projectName;
						row7.job = jobName;
						row7.context = contextStr;

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
						 * [tMap_4 main ] start
						 */

						currentComponent = "tMap_4";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row7"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_4 = false;
						boolean mainRowRejected_tMap_4 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
							// ###############################
							// # Output tables

							out = null;

// # Output table : 'out'
							out_tmp.message = row7.message;
							out = out_tmp;
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
// Start of branch "out"
						if (out != null) {

							/**
							 * [tLogRow_1 main ] start
							 */

							currentComponent = "tLogRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "out"

								);
							}

///////////////////////		

							strBuffer_tLogRow_1 = new StringBuilder();

							if (out.message != null) { //

								strBuffer_tLogRow_1.append(String.valueOf(out.message));

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

						} // End of branch "out"

						/**
						 * [tMap_4 process_data_end ] start
						 */

						currentComponent = "tMap_4";

						/**
						 * [tMap_4 process_data_end ] stop
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
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row7");
				}

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out");
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
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
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
		final alim_DWH alim_DWHClass = new alim_DWH();

		int exitCode = alim_DWHClass.runJobInTOS(args);

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
			java.io.InputStream inContext = alim_DWH.class.getClassLoader()
					.getResourceAsStream("cityviz/alim_dwh_1_0/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = alim_DWH.class.getClassLoader()
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
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
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
			tDBConnection_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBConnection_2) {
			globalMap.put("tDBConnection_2_SUBPROCESS_STATE", -1);

			e_tDBConnection_2.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : alim_DWH");
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
 * 536776 characters generated by Talend Open Studio for Data Integration on the
 * 6 mars 2022 à 16:22:50 CET
 ************************************************************************************************/