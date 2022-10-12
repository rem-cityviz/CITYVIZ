package org.talend.designer.codegen.translators.file.management;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TChangeFileEncodingMainJava
{
  protected static String nl;
  public static synchronized TChangeFileEncodingMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TChangeFileEncodingMainJava result = new TChangeFileEncodingMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_3 = " - Retrieving records from the datasource.\");" + NL + "\t\t\t";
  protected final String TEXT_4 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_5 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_6 = " - Retrieved records count: \"+ globalMap.get(\"";
  protected final String TEXT_7 = "_NB_LINE\") + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_8 = " - Written records count: \" + nb_line_";
  protected final String TEXT_9 = NL + "\t\t\t\tfinal StringBuffer log4jSb_";
  protected final String TEXT_10 = " = new StringBuffer();" + NL + "\t\t\t";
  protected final String TEXT_11 = " - Retrieving the record \" + (nb_line_";
  protected final String TEXT_12 = ") + \".\");" + NL + "\t\t\t";
  protected final String TEXT_13 = " - Writing the record \" + nb_line_";
  protected final String TEXT_14 = " + \" to the file.\");" + NL + "\t\t\t";
  protected final String TEXT_15 = " - Processing the record \" + nb_line_";
  protected final String TEXT_16 = " + \".\");" + NL + "\t\t\t";
  protected final String TEXT_17 = " - Processed records count: \" + nb_line_";
  protected final String TEXT_18 = NL;
  protected final String TEXT_19 = NL + "String sOutFileN_";
  protected final String TEXT_20 = " = ";
  protected final String TEXT_21 = ", sOriFileN_";
  protected final String TEXT_22 = ";" + NL + "java.io.File outFile_";
  protected final String TEXT_23 = " = new java.io.File(sOutFileN_";
  protected final String TEXT_24 = ");" + NL;
  protected final String TEXT_25 = NL + "  //create directory only if not exists" + NL + "  java.io.File parentFile_";
  protected final String TEXT_26 = " = outFile_";
  protected final String TEXT_27 = ".getParentFile();" + NL + "" + NL + "  if (parentFile_";
  protected final String TEXT_28 = " != null && !parentFile_";
  protected final String TEXT_29 = ".exists()) {" + NL + "    parentFile_";
  protected final String TEXT_30 = ".mkdirs();" + NL + "  }";
  protected final String TEXT_31 = NL + "final java.io.BufferedWriter out";
  protected final String TEXT_32 = " = new java.io.BufferedWriter(" + NL + "  new java.io.OutputStreamWriter(" + NL + "    new java.io.FileOutputStream(sOutFileN_";
  protected final String TEXT_33 = ", false), ";
  protected final String TEXT_34 = NL + "));" + NL + "    " + NL + "java.io.BufferedReader in_";
  protected final String TEXT_35 = " = new java.io.BufferedReader(" + NL + "  new java.io.InputStreamReader(" + NL + "    new java.io.FileInputStream(sOriFileN_";
  protected final String TEXT_36 = "), ";
  protected final String TEXT_37 = NL + "));" + NL + "" + NL + "//8192: the default buffer size of BufferedReader" + NL + "char[] cbuf_";
  protected final String TEXT_38 = " = new char[8192];  " + NL + "int readSize_";
  protected final String TEXT_39 = " = 0;" + NL + "" + NL + "while ((readSize_";
  protected final String TEXT_40 = " = in_";
  protected final String TEXT_41 = ".read(cbuf_";
  protected final String TEXT_42 = ")) != -1) {" + NL + "  out";
  protected final String TEXT_43 = ".write(cbuf_";
  protected final String TEXT_44 = ", 0, readSize_";
  protected final String TEXT_45 = ");" + NL + "}" + NL + "out";
  protected final String TEXT_46 = ".flush();" + NL + "out";
  protected final String TEXT_47 = ".close();" + NL + "in_";
  protected final String TEXT_48 = ".close();" + NL;
  protected final String TEXT_49 = NL + "  if (outFile_";
  protected final String TEXT_50 = ".exists()) {" + NL + "    java.io.File oriFile_";
  protected final String TEXT_51 = " = new java.io.File(sOriFileN_";
  protected final String TEXT_52 = ");" + NL + "    " + NL + "    if (!oriFile_";
  protected final String TEXT_53 = ".exists() || (oriFile_";
  protected final String TEXT_54 = ".exists() && oriFile_";
  protected final String TEXT_55 = ".delete())) {" + NL + "      outFile_";
  protected final String TEXT_56 = ".renameTo(oriFile_";
  protected final String TEXT_57 = ");" + NL + "    }" + NL + "  }";
  protected final String TEXT_58 = NL + "globalMap.put(\"";
  protected final String TEXT_59 = "_ISEND\", true);" + NL;
  protected final String TEXT_60 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	//this util class use by set log4j debug paramters
	class DefaultLog4jFileUtil {
	
		INode node = null;
	    String cid = null;
 		boolean isLog4jEnabled = false;
 		String label = null;
 		
 		public DefaultLog4jFileUtil(){
 		}
 		public DefaultLog4jFileUtil(INode node) {
 			this.node = node;
 			this.cid = node.getUniqueName();
 			this.label = cid;
			this.isLog4jEnabled = ("true").equals(org.talend.core.model.process.ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
 		}
 		
 		public void setCid(String cid) {
 			this.cid = cid;
 		}
 		
		//for all tFileinput* components 
		public void startRetriveDataInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_3);
    
			}
		}
		
		public void retrievedDataNumberInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
			}
		}
		
		public void retrievedDataNumberInfoFromGlobalMap(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_7);
    
			}
		}
		
		//for all tFileinput* components 
		public void retrievedDataNumberInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
			}
		}
		
		public void writeDataFinishInfo(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    
			}
		}
		
 		//TODO delete it and remove all log4jSb parameter from components
		public void componentStartInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node,boolean hasIncreased) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(hasIncreased?"":"+1");
    stringBuffer.append(TEXT_12);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node) {
			debugRetriveData(node,true);
		}
		
		//TODO rename or delete it
		public void debugWriteData(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_14);
    
			}
		}
		
		public void logCurrentRowNumberInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_16);
    
			}
		}
		
		public void logDataCountInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    
			}
		}
	}
	
	final DefaultLog4jFileUtil log4jFileUtil = new DefaultLog4jFileUtil((INode)(((org.talend.designer.codegen.config.CodeGeneratorArgument)argument).getArgument()));
	
    stringBuffer.append(TEXT_18);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
String inFileName = ElementParameterParser.getValue(node, "__INFILE_NAME__");
String outFileName = ElementParameterParser.getValue(node, "__OUTFILE_NAME__");
String sInEncoding = ElementParameterParser.getValue(node, "__INENCODING__");
String sOutEncoding = ElementParameterParser.getValue(node, "__ENCODING__");
boolean bNeedEncode = "true".equals(ElementParameterParser.getValue(node, "__USE_INENCODING__"));
boolean bCreateDir = "true".equals(ElementParameterParser.getValue(node, "__CREATE__"));
String sTmpOutFileName = outFileName;

log4jFileUtil.componentStartInfo(node);
if (outFileName.equals(inFileName)){
  sTmpOutFileName = outFileName + "+ \"_tmp\"";
  bCreateDir = false;
}

    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(sTmpOutFileName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(inFileName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    if (bCreateDir) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    }
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(sOutEncoding);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(bNeedEncode ? sInEncoding : "System.getProperty(\"file.encoding\")");
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_48);
    if (outFileName.equals(inFileName)){
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    }
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_59);
    stringBuffer.append(TEXT_60);
    return stringBuffer.toString();
  }
}
