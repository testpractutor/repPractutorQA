package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CodingPnl_WebE
{

	/** navigation menu on header to navigate coding panel */
	@FindBy(id = "navigationMenu")
	public WebElement		lst_navigationMenu;

	/** to click on Diagnosis in right side menu */
	@FindBy(partialLinkText = "Diagnosis")
	public WebElement		lnk_diagnosis;

	/** to click on admitting diagnosis in right side menu */
	@FindBy(partialLinkText = "Admitting Diagnosis")
	public WebElement		lnk_admitDiagnosis;

	/** to click on primary diagnosis in right side menu */
	@FindBy(partialLinkText = "Primary Diagnosis")
	public WebElement		lnk_primDiagnosis;

	/** to click on secondary diagnosis in right side menu */
	@FindBy(partialLinkText = "Secondary Diagnosis")
	public WebElement		lnk_secondDiagnosis;

	/** to click on procedure in right side menu */
	@FindBy(partialLinkText = "Procedures")
	public WebElement		lnk_procedure;

	/** to click on list of procedure */
	@FindBy(css = "#showProcedure>li>a")
	public WebElement		lnk_procedureInDropDown;

	/** to scroll coding panel */
	@FindBy(xpath = "(//div[@class='mCSB_dragger_bar'])[5]")
	public WebElement		coding_Dragger;

	/** to click on add admitting diagnosis code in coding panel in ICD - 9 */
	@FindBy(id = "add-icd9-admit-diagnosis-code")
	public WebElement		lnk_addAdmit_DignosisCode_ICD9;

	/** to click on add diagnosis code in coding panel ICD - 9 */
	@FindBy(id = "add-icd9-diagnosis-code")
	public WebElement		lnk_addDignosisCode_ICD9;

	/** to click on add procedure code in coding panel ICD - 9 */
	@FindBy(id = "add-icd9-procedure-code")
	public WebElement		lnk_addProcedureCode_ICD9;

	/** to select Evidence found in admitting code in coding panel ICD-9 */
	@FindBy(id = "add-admitdiagnosis-select-box")
	public WebElement		lst_admit_Diagnosis_ICD9;

	/** to select Evidence found in diagnosis code in coding panel ICD-9 */
	@FindBy(id = "icd9-add-diagnosis-select-box")
	public WebElement		lst_secondDiagnosis_ICD9;

	/** to select Evidence found in procedure code in coding panel ICD-9 */
	@FindBy(id = "icd9-add-procedure-select-box")
	public WebElement		lst_procedure_ICD9;

	/** to click on save selected admitting code in ICD-9 */
	@FindBy(id = "add-admitdiagnosis-code-save")
	public WebElement		btn_saveAdmitDignosis_ICD9;

	/** to click on save selected secondary diagnosis code in ICD-9 */
	@FindBy(id = "icd9-add-diagnosis-code-save")
	public WebElement		btn_saveDignosis_ICD9;

	/** to click on save added procedure code in ICD-9 */
	@FindBy(id = "icd9-add-procedure-code-save")
	public WebElement		btn_saveProcedure_ICD9;

	/** to click on add admitting diagnosis code in coding panel in ICD - 10 */
	@FindBy(id = "add-icd10-admit-diagnosis-code")
	public WebElement		lnk_addAdmit_DignosisCode_ICD10;

	/** to click on add diagnosis code in coding panel ICD - 10 */
	@FindBy(id = "add-icd10-diagnosis-code")
	//@FindBy(linkText = "Add Diagnosis Code")
	public WebElement		lnk_addDignosisCode_ICD10;

	/** to click on add procedure code in coding panel ICD - 10 */
	@FindBy(id = "add-icd10-procedure-code")
	public WebElement		lnk_addProcedureCode_ICD10;

	/** to select Evidence found in admitting code in coding panel ICD-10 */
	//@FindBy(id = "add-admitdiagnosis-select-box")
	@FindBy(xpath = "(//select[@id='add-admitdiagnosis-select-box'])[2]")
	public WebElement		lst_admit_Diagnosis_ICD10;

	/** to select Evidence found in diagnosis code in coding panel ICD-10 */
	@FindBy(id = "icd10-add-diagnosis-select-box")
	public WebElement		lst_secondDiagnosis_ICD10;

	/** to select Evidence found in procedure code in coding panel ICD-10 */
	@FindBy(id = "icd10-add-procedure-select-box")
	public WebElement		lst_procedure_ICD10;

	/** to click on save selected admitting code in ICD-10 */
	//@FindBy(id = "add-admitdiagnosis-code-save")
	@FindBy(xpath = "(//input[@id='add-admitdiagnosis-code-save'])[2]")
	public WebElement		btn_saveAdmitDignosis_ICD10;

	/** to click on save selected secondary diagnosis code in ICD-10 */
	@FindBy(id = "icd10-add-diagnosis-code-save")
	public WebElement		btn_saveDignosis_ICD10;

	/** to click on save added procedure code in ICD-10 */
	@FindBy(id = "icd10-add-procedure-code-save")
	public WebElement		btn_saveProcedure_ICD10;

	/** to click on select list on ICD 9_10 in menu in coding panel */
	@FindBy(id = "icd9-10-display")
	public WebElement		lst_codeType;

	/** to click on ICD-9 In menu in coding panel */
	@FindBy(id = "icd-9-menu")
	public WebElement		lnk_ICD9_Menu;

	/** to click on ICD-10 In menu in coding panel */
	@FindBy(id = "icd-10-menu")
	public WebElement		lnk_ICD10_Menu;

	/** to click on add CPT code of out patient in coding panel in ICD-9 and ICD-10 */
	@FindBy(id = "add-cpt-code-link")
	public WebElement		lnk_addCPTcode;

	/** to select evidence found in CPT code ICD-9 and ICD-10 in coding panel */
	@FindBy(id = "add-cpt-select-box")
	public WebElement		lst_CPT;

	/** to save CPT code in coding panel ICD-9 and ICD-10 */
	@FindBy(id = "add-cpt-code-save")
	//	@FindBy(css = "input[id='add-cpt-code-save'][value='Save']")
	public WebElement		btn_saveCPT;

	/** to click on add E & M of out patient in coding panel in ICD-9 and ICD-10 */
	@FindBy(id = "add-enm-code-link")
	public WebElement		lnk_addEandMcode;

	/** to select evidence found in E & M ICD-9 and ICD-10 in coding panel */
	@FindBy(id = "add-enm-select-box")
	public WebElement		lst_EandM;

	/** to save E & M code in coding panel ICD-9 and ICD-10 */
	@FindBy(id = "add-enm-code-save")
	public WebElement		btn_saveEandM;

	/** to click on add HCPCS of out patient in coding panel in ICD-9 and ICD-10 */
	@FindBy(id = "add-hcpcs-code-link")
	public WebElement		lnk_addHCPCScode;

	/** to select evidence found in HCPCS ICD-9 and ICD-10 in coding panel */
	@FindBy(id = "add-hcpcs-select-box")
	public WebElement		lst_HCPCS;

	/** to save HCPCS code in coding panel */
	@FindBy(id = "add-hcpcs-code-save")
	public WebElement		btn_saveHCPCS;

	/** to click on add E & M Form link in coding panel */
	@FindBy(id = "add-enm-form")
	public WebElement		lnk_addEandMform;

	/** to select First select box in E and M form in coding panel */
	@FindBy(id = "firstSelect")
	public WebElement		lst_firstSelect_EandM;

	/** to select Second select box in E and M form in coding panel */
	@FindBy(id = "secondSelect")
	public WebElement		lst_secondSelect_EandM;

	/** to select Third select box in E and M form in coding panel */
	@FindBy(id = "thirdSelect")
	public WebElement		lst_thirdSelect_EandM;

	/** to select Forth select box in E and M form in coding panel */
	@FindBy(id = "forthSelect")
	public WebElement		lst_forthSelect_EandM;

	/** to click on check box of Times (mins) in E and M Form in coding panel */
	@FindBy(xpath = "//label[text()='Time']/preceding-sibling::input[1]")
	public WebElement		chk_time_EandM;

	/** to enter time in format of HH:MM only in E and M form in coding panel */
	@FindBy(id = "timeText")
	public WebElement		txt_timeBox_EandM;

	/** to click on E and M Done Button in E and M form in coding panel */
	@FindBy(id = "enmDone")
	public WebElement		btn_done_EandM;

	/** to click on Cancel button in E nad M form IN coding panel */
	@FindBy(id = "enmCancel")
	public WebElement		btn_cancel_EandM;

	/** to get text of CPT code in E and M Form */
	@FindBy(css = "td[class='cptCode']")
	public WebElement		lbl_CTPCode_EandM;

	/** to click on check box of Use E and Form in E and Form */
	@FindBy(xpath = "//label[text()='Use E & M Form']/preceding-sibling::input[1]")
	public WebElement		chk_useEandMForm;

	/** to click on first column and first row in Chief Complaint (CC), History table in E and M form */
	@FindBy(xpath = "(//table[@id='firstColunm']//td)[1]")
	public WebElement		tbl_ChiefComplaint_EandM;

	/** to click on second column and first row in History of present illness (HPI), History table in E and M form */
	@FindBy(xpath = "(//table[@id='secondColunm']//td)[1]")
	public WebElement		tbl_HPI_EandM;

	/** to click on Third column and First row in Past, family, social history (PFSH) ,History table in E and M form */
	@FindBy(xpath = "(//table[@id='thirdColunm']//td)[1]")
	public WebElement		tbl_PFSH_EandM;

	/** to click on Forth column and First row in Review of systems (ROS), History table in E and M form */
	@FindBy(xpath = "(//table[@id='forthColunm']//td)[1]")
	public WebElement		tbl_ROS_EandM;

	/** to click on fifth column and First row in History Type, History table in E and M form */
	@FindBy(xpath = "(//table[@id='fifthColunm']//td)[1]")
	public WebElement		tbl_HistoryType_EandM;

	/** to click on first column and First row in Examination Column in E and M form */
	@FindBy(xpath = "(//table[@id='examinationColunm']//td)[1]")
	public WebElement		tbl_examination_EandM;

	/** to click on fifth column and First row in MEDICAL DECISION MAKING table in E and M form */
	@FindBy(xpath = "(//table[@id='medicalColunm']//td)[1]")
	public WebElement		tbl_medicalDicision_EandM;

	/** to wait for this table is visible or not in E and M form in coding panel */
	@FindBy(css = "div[class='exam-table-container']")
	public WebElement		tbl_secondTable_EandM;

	/** to drage E and M from in coding panel */
	@FindBy(xpath = "(//div[@class='mCSB_dragger_bar'])[4]")
	public WebElement		enm_dragger;

	/** to click on first suggested admitting evidence in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[contains(@class,'brd code-status')])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_first_admit_evi_arrow;

	/** to click on query physician for first suggested evidence in admitting diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[contains(@class,'brd code-status')])[1]//a[@id='coding_panel_add_query']")
	public WebElement		lnk_query_first_admit_evi;

	/** to click on discuss with colleague for first suggested evidence in admitting diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[contains(@class,'brd code-status')])[1]//a[@id='coding_panel_add_discussion']")
	public WebElement		lnk_discuss_first_admit_evi;

	/** to click on first manual admitting evidence in coding panel */

	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_first_admit_man_evi_arrow;

	/** to click on query physician for first manual evidence in admitting diagnosis in coding panel */

	@FindBy(xpath = "(//h2[@id='admit']/..//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='coding_panel_add_query']")
	public WebElement		lnk_query_first_man_admit_evi;

	/** to click on discuss with colleague for first manual evidence in admitting diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='coding_panel_add_discussion']")
	public WebElement		lnk_discuss_first_man_admit_evi;

	/** to click on first suggested principal evidence in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[contains(@class,'brd code-status')])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_first_Principal_evi_arrow;

	/** to click on query physician for first suggested evidence in Principal diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[contains(@class,'brd code-status')])[1]//a[@id='coding_panel_add_query']")
	public WebElement		lnk_query_first_Principal_evi;

	/** to click on discuss with colleague for first suggested evidence in Principal diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[contains(@class,'brd code-status')])[1]//a[@id='coding_panel_add_discussion']")
	public WebElement		lnk_discuss_first_Principal_evi;

	/** to click on first manual Principal evidence in coding panel */
	//@FindBy(xpath = "(//h2[@id='primary']/..//span[contains(@class,'brd code-status manual')])[1]//span[@class='code-status-drop-down']")
	@FindBy(xpath = "(//h2[@id='primary']/..//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_first_Principal_man_evi_arrow;

	/** to click on query physician for first manual evidence in Principal diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='coding_panel_add_query']")
	public WebElement		lnk_query_first_man_Principal_evi;

	/** to click on discuss with colleague for first manual evidence in Principal diagnosis in coding panel */
	@FindBy(xpath = "(//h2[@id='primary']/..//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='coding_panel_add_discussion']")
	public WebElement		lnk_discuss_first_man_Principal_evi;

	/** to click on first suggested secondary evidence in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[contains(@class,'brd code-status')])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_first_secondary_evi_arrow;

	/** to click on query physician for first suggested evidence in secondary diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[contains(@class,'brd code-status')])[1]//a[@id='coding_panel_add_query']")
	public WebElement		lnk_query_first_secondary_evi;

	/** to click on discuss with colleague for first suggested evidence in secondary diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[contains(@class,'brd code-status')])[1]//a[@id='coding_panel_add_discussion']")
	public WebElement		lnk_discuss_first_secondary_evi;

	/** to click on first manual secondary evidence in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_first_secondary_man_evi_arrow;

	/** to click on query physician for first manual evidence in secondary diagnosis in coding panel */
	@FindBy(xpath = "(//h2[@id='secondary']/..//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='coding_panel_add_query']")
	public WebElement		lnk_query_first_man_secondary_evi;

	/** to click on discuss with colleague for first manual evidence in secondary diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='coding_panel_add_discussion']")
	public WebElement		lnk_discuss_first_man_secondary_evi;

	/** to click on first suggested procedure evidence in coding panel */
	//	@FindBy(xpath = "(//h2[@id='icd9-procedure']/..//span[contains(@class,'brd code-status')])[1]//span[@class='code-status-drop-down']")
	@FindBy(xpath = "(//div[@id='icd9-procedure-code' or @id='icd10-procedure-code']//span[contains(@class,'brd code-status')])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_first_procedure_evi_arrow;

	/** to click on query physician for first suggested evidence in procedure in coding panel */
	@FindBy(xpath = "(//div[@id='icd9-procedure-code' or @id='icd10-procedure-code']//span[contains(@class,'brd code-status')])[1]//a[@id='coding_panel_add_query']")
	public WebElement		lnk_query_first_procedure_evi;

	/** to click on discuss with colleague for first suggested evidence in procedure in coding panel */
	@FindBy(xpath = "(//div[@id='icd9-procedure-code' or @id='icd10-procedure-code']//span[contains(@class,'brd code-status')])[1]//a[@id='coding_panel_add_discussion']")
	public WebElement		lnk_discuss_first_procedure_evi;

	/** to click on first manual procedure evidence in coding panel */
	//@FindBy(xpath = "(//h2[@id='primary']/..//span[contains(@class,'brd code-status manual')])[1]//span[@class='code-status-drop-down']")
	@FindBy(xpath = "(//div[@id='icd9-procedure-code' or @id='icd10-procedure-code']//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_first_procedure_man_evi_arrow;

	/** to click on query physician for first manual evidence in procedure in coding panel */
	@FindBy(xpath = "(//div[@id='icd9-procedure-code' or @id='icd10-procedure-code']//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='coding_panel_add_query']")
	public WebElement		lnk_query_first_man_procedure_evi;

	/** to click on discuss with colleague for first manual evidence in procedure in coding panel */
	@FindBy(xpath = "(//div[@id='icd9-procedure-code' or @id='icd10-procedure-code']//span[contains(@class,'brd code-status') and contains(@class,'manual')])[1]//a[@id='coding_panel_add_discussion']")
	public WebElement		lnk_discuss_first_man_procedure_evi;

	/** the list of all codes which are accepted or not in coding panel */
	@FindBy(xpath = "//div[contains(@class,'num-container')]")
	public List<WebElement>	allCodes;

	/** the highlighted evidence dropdown in coding panel */
	@FindBy(xpath = "//span[@class='code-status-menu selectedEvidenceBGColor']/..//span[@class='code-status-drop-down']")
	public WebElement		highlgt_removeEvi_coding;

	/** the highlighted evidence remove evidence link in coding panel */
	@FindBy(xpath = "//span[contains(@class,'code-status-menu selectedEvidenceBGColor')]//a[@id='removEvidence']")
	public WebElement		highlgt_dropD_coding;

	/** to click on link of admitting diagnosis in Navigation Menu in coding panel */
	@FindBy(xpath = "//a[@id='admit']")
	public WebElement		lnk_admitNavi;

	/** to click on link of primary diagnosis in Navigation Menu in coding panel */
	@FindBy(xpath = "//a[@id='primary']")
	public WebElement		lnk_primaryNavi;

	/** to click on link of primary diagnosis in Navigation Menu in coding panel */
	@FindBy(xpath = "//a[@id='secondary']")
	public WebElement		lnk_secondaryNavi;

	/** to click on link of primary diagnosis in Navigation Menu in coding panel */
	@FindBy(xpath = "//a[@id='cpt']")
	public WebElement		lnk_CPTNavi;

	/** to click on link of primary diagnosis in Navigation Menu in coding panel */
	@FindBy(xpath = "//a[@id='emForm']")
	public WebElement		lnk_EandMNavi;

	/** to click on link of primary diagnosis in Navigation Menu in coding panel */
	@FindBy(xpath = "//a[@id='hcpcs']")
	public WebElement		lnk_hcpcsNavi;

	/** to click on link of procedure diagnosis in Navigation Menu in ICD9 in coding panel */
	@FindBy(id = "icd9-principal-procedure-code")
	public WebElement		lnk_procedureNavi_ICD9;

	/** to click on link of procedure diagnosis in Navigation Menu in ICD10 in coding panel */
	@FindBy(id = "icd10-procedure-code")
	public WebElement		lnk_procedureNavi_ICD10;

	/** to click downarrow of Diagnosis on Navigation menu for see all links in coding panel */
	@FindBy(xpath = "(//a[contains(@class,'nav-menu-data-arrow')])[1]")
	public WebElement		lnk_diaNavi;

	/** to click downarrow of Procedure on Navigation menu for see all links in coding panel */
	@FindBy(xpath = "(//a[contains(@class,'nav-menu-data-arrow')])[2]")
	public WebElement		lnk_procedureNavi;

	/** to click on downarrow of first evidence of associated code of admitting diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='num-container num accepted-code']/../..//span[@class='code-status-drop-down'])[1]")
	public WebElement		lnk_associatedAdmit_arrow;

	/** to click on query physician link of first evidence of associated code of admitting in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='num-container num accepted-code']/../../div[2]//a[@class='query-physician'])[1]")
	public WebElement		lnk_associatedAdmit_query;

	/** to click on downarrow of first evidence of associated code of principal diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[@class='num-container num accepted-code']/../..//span[@class='code-status-drop-down'])[1]")
	public WebElement		lnk_associatedPrincipal_arrow;

	/** to click on query physician link of first evidence of associated code of principal in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[@class='num-container num accepted-code']/../../div[2]//a[@class='query-physician'])[1]")
	public WebElement		lnk_associatedPrincipal_query;

	/** to click on downarrow of first evidence of associated code of secondary diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='num-container num accepted-code']/../..//span[@class='code-status-drop-down'])[1]")
	public WebElement		lnk_associatedSecondary_arrow;

	/** to click on query physician link of first evidence of associated code of secondary in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='num-container num accepted-code']/../../div[2]//a[@class='query-physician'])[1]")
	public WebElement		lnk_associatedSecondary_query;

	/** to click on downarrow of first evidence of associated code of procedure diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='num-container num accepted-code']/../..//span[@class='code-status-drop-down'])[1]")
	public WebElement		lnk_associatedProcedure_arrow;

	/** to click on query physician link of first evidence of associated code of procedure in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='num-container num accepted-code']/../../div[2]//a[@class='query-physician'])[1]")
	public WebElement		lnk_associatedProcedure_query;

	/** to expand Principal diagnosis code */
	@FindBy(css = "#icd9-primary-diagnosis-code > div")
	public WebElement		principalCode;

	/** to click on POA value of Principal code */
	@FindBy(xpath = "//*[contains(@id,'primary-diagnosis-code')]//span[contains(@id,'diagnosis-poa-y')]")
	public WebElement		txt_POAY;

	/** to click on Save button of Principal Code after expand it */
	@FindBy(xpath = "//*[contains(@id,'primary-diagnosis-code')]//input[contains(@id,'diagnosis-save')]")
	public WebElement		btn_Save;

	/** to expand first procedures code */
	@FindBy(xpath = "//div[contains(@id,'procedure-code')]//div[contains(@class,'num-container num')]")
	public WebElement		expand_Procedure;

	/** to expand first CPT code */
	@FindBy(xpath = "(//div[contains(@id,'cpt-code')]//div[contains(@class,'num-container num')])[1]")
	public WebElement		expand_CPT;

	/** to enter the values in Episode textbox of procedures codes */
	@FindBy(xpath = "//input[contains(@id,'episode-textbox')]")
	public WebElement		txt_Episode;

	/** to enter the values in Anesthesia Type textbox */
	@FindBy(xpath = "//input[contains(@id,'anesthesiatype-textbox')]")
	public WebElement		txt_AnesthesiaType;

	/** to enter values in Provider textbox */
	@FindBy(xpath = "(//input[contains(@id,'provider-textbox')])[1]")
	public WebElement		txt_Provider;

	/** to enter value in Anesthesia Provider textbox */
	@FindBy(xpath = "//input[contains(@id,'anesthesiaprovider-textbox')]")
	public WebElement		txt_AnesthesiaProvider;

	/** to enter value in Date field */
	@FindBy(xpath = "//input[contains(@class,'date-textbox datepicker_txt hasDatepicker')]")
	public WebElement		txt_Date;

	@FindBy(xpath = "//*[contains(@id,'anesthesiatime-textbox')]")
	public WebElement		txt_AnesthesiaTime;

	/** to click on accept code link of associated code of admitting in coding panel after first probable code is added */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[@id='code-accepted-disabled'])[2]")
	public WebElement		lnk_associatedAcceptAdmit;

	/** to click on accept code link of associated code of principal in coding panel after first probable code is added */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[@id='code-accepted-disabled'])[2]")
	public WebElement		lnk_associatedAcceptPrincipal;

	/** to click on accept code link of associated code of secondary in coding panel after first probable code is added */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[@id='code-accepted-disabled'])[2]")
	public WebElement		lnk_associatedAcceptSecondary;

	/** to click on accept code link of associated code of procedure in coding panel after first probable code is added */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//span[@id='code-accepted-disabled'])[2]")
	public WebElement		lnk_associatedAcceptProcedure;

	/** to click on accept code link of associated code of procedure in coding panel after first probable code is added */
	@FindBy(xpath = "(//div[contains(@id,'cpt-code')]//span[@id='code-accepted-disabled'])[2]")
	public WebElement		lnk_associatedAcceptCPT;

	/** to click on Save button of first procedure code in Inpatient case */
	@FindBy(xpath = "(//*[contains(@id,'procedure-save')])[1]")
	public WebElement		btn_Save_Procedure;

	/** to click on Save button of first CPT code in Outpatient case */
	@FindBy(xpath = "(//*[contains(@id,'cpt-save')])[1]")
	public WebElement		btn_Save_CPT;

	/** to click on Save button of EnM Code in Outpatient case */
	@FindBy(xpath = "(//*[contains(@id,'enm-save')])[1]")
	public WebElement		btn_Save_EnM;

	/** to click on Save button of HCPCS code in Outpatient case */
	@FindBy(xpath = "(//*[contains(@id,'hcpcs-save')])[1]")
	public WebElement		btn_Save_HCPCS;

	/** to expand principal code */
	@FindBy(xpath = "//div[contains(@id,'primary-diagnosis-code')]//div[contains(@class,'num-container')]")
	public WebElement		expand_Principal_Code;

	/** to accept first Admitting Diagnosis code */
	@FindBy(xpath = "//h2[@id='admit']/..//span[contains(@id,'code-accepted')]")
	public WebElement		lnk_Accept_Admitting;

	/** to accept principal code in coding panel */
	@FindBy(xpath = "//h2[@id='primary']/..//span[contains(@id,'code-accepted')]")
	public WebElement		lnk_Accept_Principal;

	/** to accept first secondary diagnosis code */
	@FindBy(xpath = "(//h2[@id='secondary']/..//span[1][contains(@id,'code-accepted')])[1]")
	public WebElement		lnk_First_Secondary;

	/** to reject Principal diagnosis code */
	@FindBy(xpath = "//h2[@id='primary']/..//span[contains(@id,'code-rejected')]")
	public WebElement		lnk_Reject_Principal;

	/** to reject Secondary diagnosis code */
	@FindBy(xpath = "(//h2[@id='secondary']/..//span[contains(@id,'code-rejected')])[1]")
	public WebElement		lnk_Reject_Secondary;

	/** to accept CPT code */
	@FindBy(xpath = "//h2[@id='cpt']/..//span[contains(@id,'code-accepted')]")
	public WebElement		lnk_Accept_CPT;

	/** to expand E & M code */
	@FindBy(xpath = "//div[contains(@id,'EnM-code')]//div[contains(@class,'num-container num')]")
	public WebElement		lnk_Expand_EnM;

	/** to expand HCPCS code */
	@FindBy(xpath = "//div[contains(@id,'hcpcs')]//div[contains(@class,'num-container num')]")
	public WebElement		lnk_Expand_HCPCS;

	/** label of diagnosis in coding panel */
	@FindBy(xpath = "//h5[contains(@id,'diagnosis')]")
	public WebElement		lbl_diagnosis;

	/** label of admitting diagnosis in coding panel */
	@FindBy(id = "admit")
	public WebElement		lbl_Admitdiagnosis;

	/** label of principal diagnosis in coding panel */
	@FindBy(id = "primary")
	public WebElement		lbl_Principaldiagnosis;

	/** label of secondary diagnosis in coding panel */
	@FindBy(id = "secondary")
	public WebElement		lbl_Secondarydiagnosis;

	/** label of procedure in coding panel */
	@FindBy(xpath = "//h5[contains(@id,'procedure')]")
	public WebElement		lbl_Procedure;

	/** label of cpt in coding panel */
	@FindBy(id = "cpt")
	public WebElement		lbl_CPT;

	/** label of E and M in coding panel */
	@FindBy(id = "emForm")
	public WebElement		lbl_EandM;

	/** label of HCPCS in coding panel */
	@FindBy(id = "hcpcs")
	public WebElement		lbl_HCPCS;

	/** to click on "P" icon of first secondary diagnosis code */
	@FindBy(xpath = "(//h2[@id='secondary']/..//span[5][contains(@id,'make-it-principal')])[1]")
	public WebElement		ico_Principal;

	/** list of all reference panel link in coding panel */
	@FindBy(id = "reference")
	public List<WebElement>	lst_referenceCodingPnl;

	/** label of reference menu in reference pane */
	@FindBy(id = "referencemenu")
	public WebElement		lbl_reference;

	/** to close reference panel */
	@FindBy(className = "close")
	public WebElement		btn_Close;

	/** get count of code number in secondary diagnosis */
	@FindBy(xpath = "//div[@id='icd9-secondary-diagnosis-code']//div[@class='code-number']")
	public List<WebElement>	lst_secondaryCode;

	/** get old code number in secondary diagnosis */
	@FindBy(xpath = "//div[@id='icd9-primary-diagnosis-code']//div[contains(@class,'num-container num')]")
	public WebElement		lbl_codeNumberSecondary;

	/** to click on make it principal in coding panel */
	@FindBy(id = "make-it-principal")
	public List<WebElement>	lnk_makeItPri;

	/** to get label of rank in primary diagnosis */
	@FindBy(xpath = "//div[@id='icd9-primary-diagnosis-code']//span[@class='evidance-seq']")
	public WebElement		lbl_principalRank;

	/** to get code number of principal code in coding panel */
	@FindBy(xpath = "//div[@id='icd9-primary-diagnosis-code']//div[contains(@class,'num-container num')]")
	public WebElement		lbl_principalCodeNum;

	/** to get list of accepted code in coding panel */
	@FindBy(xpath = "//div[@id='icd9-secondary-diagnosis-code']//div[@class='num-container num accepted-code']")
	public List<WebElement>	lst_acceptedCodeSecondary;

	/** to get first code number in admitting diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//div[contains(@class,'num-container')])[1]")
	public WebElement		lbl_firstAdmitCodeNum;

	/** to get first code number in principal diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[contains(@class,'num-container')])[1]")
	public WebElement		lbl_firstPrincipalCodeNum;

	/** to get first code number in secondary diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[contains(@class,'num-container')])[1]")
	public WebElement		lbl_firstSecondaryCodeNum;

	/** to get first code number in procedure diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[contains(@class,'num-container')])[1]")
	public WebElement		lbl_firstProcedureCodeNum;

	/** to get second code number in procedure diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[contains(@class,'num-container')])[2]")
	public WebElement		lbl_secondProcedureCodeNum;

	/** to click on Y of first code of admitting diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[contains(@id,'diagnosis-poa-y')])[1]")
	public WebElement		lnk_YPOA_firstAdmit;

	/** to click on N of first code of admitting diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[contains(@id,'diagnosis-poa-n')])[1]")
	public WebElement		lnk_NPOA_firstAdmit;

	/** to click on U of first code of admitting diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[contains(@id,'diagnosis-poa-u')])[1]")
	public WebElement		lnk_UPOA_firstAdmit;

	/** to click on W of first code of admitting diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[contains(@id,'diagnosis-poa-w')])[1]")
	public WebElement		lnk_WPOA_firstAdmit;

	/** to click on E of first code of admitting diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[contains(@id,'diagnosis-poa-e')])[1]")
	public WebElement		lnk_EPOA_firstAdmit;

	/** to click on Y of first code of Principal diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[contains(@id,'diagnosis-poa-y')])[1]")
	public WebElement		lnk_YPOA_firstPrincipal;

	/** to click on N of first code of Principal diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[contains(@id,'diagnosis-poa-n')])[1]")
	public WebElement		lnk_NPOA_firstPrincipal;

	/** to click on U of first code of Principal diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[contains(@id,'diagnosis-poa-u')])[1]")
	public WebElement		lnk_UPOA_firstPrincipal;

	/** to click on W of first code of Principal diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[contains(@id,'diagnosis-poa-w')])[1]")
	public WebElement		lnk_WPOA_firstPrincipal;

	/** to click on E of first code of Principal diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[contains(@id,'diagnosis-poa-e')])[1]")
	public WebElement		lnk_EPOA_firstPrincipal;

	/** to click on Y of first code of Secondary diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[contains(@id,'diagnosis-poa-y')])[1]")
	public WebElement		lnk_YPOA_firstSecondary;

	/** to click on N of first code of Secondary diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[contains(@id,'diagnosis-poa-n')])[1]")
	public WebElement		lnk_NPOA_firstSecondary;

	/** to click on U of first code of Secondary diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[contains(@id,'diagnosis-poa-u')])[1]")
	public WebElement		lnk_UPOA_firstSecondary;

	/** to click on W of first code of Secondary diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code)]//span[contains(@id,'diagnosis-poa-w')])[1]")
	public WebElement		lnk_WPOA_firstSecondary;

	/** to click on E of first code of Secondary diagnosis */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[contains(@id,'diagnosis-poa-e')])[1]")
	public WebElement		lnk_EPOA_firstSecondary;

	/** to check probable code of admitting diagnosis code is Accepted or not */
	@FindBy(xpath = "//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-accepted-enabled']")
	public WebElement		ico_Accepted_Probable_Admitting;

	/** to check Associated code of Admitting diagnosis code is Accepted or not */
	@FindBy(xpath = "//div[contains(@id,'admitting-diagnosis-code')]//div[@class='expand']//span[@id='code-accepted-enabled']")
	public WebElement		ico_Accepted_Associated_Admitting;

	/** to reject Associated code of Admitting Diagnosis code */
	@FindBy(xpath = "//div[contains(@id,'admitting-diagnosis-code')]//div[@class='expand']//span[@id='code-rejected-disabled']")
	public WebElement		ico_Reject_Associated_Admitting;

	/** to click on dropdown pointer of evidence of Admitting Diagnosis code */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[@class='brd code-status'])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_Admitting_Diagnosis;

	/** to click on Query Physician option of evidence in Admitting Diagnosis code */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='expand']//span[@class='code-status-menu'])[1]//a[@class='query-physician']")
	public WebElement		lst_QueryPhysician_Admittng;

	/** to check modify icon of Admitting diagnosis code is enabled or not */
	@FindBy(xpath = "//div[contains(@id,'admitting-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-enabled']")
	public WebElement		ico_Modify_Associated_Admitting;

	/** to click on dropdown pointer of evidence of Principal Diagnosis code */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[@class='brd code-status'])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_Principal_Diagnosis;

	/** to click on Query Physician option of evidence in Principal Diagnosis code */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[@class='expand']//span[@class='code-status-menu'])[1]//a[@class='query-physician']")
	public WebElement		lst_QueryPhysician_Principal;

	/** to check Associated Code Principal Code is Accepted or not */
	@FindBy(xpath = "//div[contains(@id,'primary-diagnosis-code')]//div[@class='expand']//span[@id='code-accepted-enabled']")
	public WebElement		ico_Accepted_Principal;

	/** to check Probable code of Principal diagnosis code is accepted or not */
	@FindBy(xpath = "//div[contains(@id,'primary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-accepted-enabled']")
	public WebElement		ico_Accpeted_Probable_Principal;

	/** to check modify icon of Principal Diagnosis code is enabled or not */
	@FindBy(xpath = "//div[contains(@id,'primary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-enabled']")
	public WebElement		ico_Modify_Associated_Principal;

	/** to reject principal diagnosis code */
	@FindBy(xpath = "//div[contains(@id,'primary-diagnosis-code')]//div[@class='expand']//span[@id='code-rejected-disabled']")
	public WebElement		ico_Reject_Associated_Principal;

	/** to click on dropdown pointer of first evidence in Secondary Diagnosis code */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[@class='brd code-status'])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_Secondary_Diagnosis;

	/** to click on dropdown pointer of evidence of last secondary diagnosis code */
	@FindBy(xpath = "((//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand'])[11]//span[@class='brd code-status'])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_Last_Secondary_Diagnosis;

	/** to click on Query Physician option of evidence of Secondary Diagnosis code */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@class='code-status-menu'])[1]//a[@class='query-physician']")
	public WebElement		lst_QueryPhysician_Secondary;

	/** to click on Query Physician option of evidence of last secodnary diagnosis code */
	@FindBy(xpath = "((//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand'])[11]//span[@class='code-status-menu'])[1]//a[@class='query-physician']")
	public WebElement		lst_QueryPhysician_Last_Secondary;

	/** to check Associated code of Secondary diagnosis code is accepted or not */
	@FindBy(xpath = "//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@id='code-accepted-enabled']")
	public WebElement		ico_Accepted_Secondary;

	/** to check Probable code of secondary diagnosis code is accepted or not */
	@FindBy(xpath = "//div[contains(@id,'secondary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-accepted-enabled']")
	public WebElement		ico_Accpeted_Probable_Secondary;

	/** to reject First Secondary diagnosis code */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@id='code-rejected-disabled'])[1]")
	public WebElement		ico_Reject_Associated_Secondary;

	/** to check secondary diagnosis is rejected or not */
	@FindBy(xpath = "//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@id='code-rejected-enabled']")
	public WebElement		ico_Reject_Secondary;

	/** to check modify icon of Secondary diagnosis is enabled or not */
	@FindBy(xpath = "//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-enabled']")
	public WebElement		ico_Modify_Associated_Secondary;

	/** to click on downarrow of second code of procedure diagnosis in coding panel */
	@FindBy(xpath = "((//div[@id='icd9-procedure-code' or @id='icd10-procedure-code']//div[@class='expand'])[2]//span[@class='code-status-drop-down'])[1]")
	public WebElement		lnk_assoSecondProcedure_arrow;

	/** to click on query physician link of second code of procedure in coding panel */
	@FindBy(xpath = "((//div[@id='icd9-procedure-code' or @id='icd10-procedure-code']//div[@class='expand'])[2]//a[@class='query-physician'])[1]")
	public WebElement		lnk_assoSecondProcedure_query;

	/** to get code number of second probable code of second code in procedure in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='probable-option expand'])[2]//div[contains(@class,'num num-container')]")
	public WebElement		lbl_secondProbCodeSecoProcedure;

	/** to get second code number in principal diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[contains(@class,'num-container')])[2]")
	public WebElement		lbl_secondPrincipalCodeNum;

	/** to click on make it principal in secondary first associated code in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@id='make-it-principal'])[1]")
	public WebElement		lnk_makeItPrincipalAssoSecondary;

	/** to get code number of secondary first associated code in coding panel for CDS user */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand'])[1]//div[contains(@class,'num-container num')]")
	public WebElement		lbl_codeNumAssoSecondary;

	/** to get code number of principal associated code in coding panel for CDS user */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[@class='expand'])[1]//div[contains(@class,'num-container num')]")
	public WebElement		lbl_codeNumAssoPrincipal;

	/** to check MNE edits and error are present on CPT code in coding panel */
	@FindBy(xpath = "//span[contains(text(),'Medical Necessary Edits')]")
	public WebElement		lbl_MNEeditCPTerror;

	/** to accept first procedure code in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//span[@id='code-accepted-disabled'])[1]")
	public WebElement		lnk_Accept_Procedure;

	/** to click on dropdown pointer of first evidence in Procedure code */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//span[@class='brd code-status'])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_Procedure;

	/** to click on Query Physician option of Procedure code */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@class='code-status-menu'])[1]//a[@class='query-physician']")
	public WebElement		lst_QueryPhysician_Procedure;

	/** to check Associated code of Procedure code is accepted or not */
	@FindBy(xpath = "//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@id='code-accepted-enabled']")
	public WebElement		ico_Accepted_Associated_Procedure;

	/** to check Probable code Procedure code is accepted or not */
	@FindBy(xpath = "//div[contains(@id,'procedure-code')]//div[@class='probable-option expand']//span[@id='code-accepted-enabled']")
	public WebElement		ico_Accepted_Probable_Procedure;

	/** to reject first Procedure code of Associated code */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@id='code-rejected-disabled'])[1]")
	public WebElement		ico_Reject_Procedure;

	/** to check procedure code is rejected or not */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@id='code-rejected-enabled'])[1]")
	public WebElement		ico_Reject_Enabled_Procedure;

	/** to check modify icon of Associated code in procedure code is enabled or not */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='expand'])[1]//span[@id='code-modified-enabled']")
	public WebElement		ico_Modify_Procedure;

	/** to click on dropdoen pointer of evidence in last procedure code */
	@FindBy(xpath = "((//div[contains(@id,'procedure-code')]//div[@class='expand'])[3]//span[@class='brd code-status'])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_Last_Procedure;

	/** to click on Query physician option in last procedure code */
	@FindBy(xpath = "((//div[contains(@id,'procedure-code')]//div[@class='expand'])[3]//span[@class='code-status-menu'])[1]//a[@class='query-physician']")
	public WebElement		lst_QueryPhsycian_Last_Procedure;

	/** to check edits and error are present in coding panel */
	@FindBy(id = "editico")
	public WebElement		ico_editCodingPanel;

	/** to click on Details link of MNE edits in coding panel */
	@FindBy(id = "mneData")
	public WebElement		lnk_MNEeditDetails;

	/** to check covered codes are present on MNE Details Reference Panel */
	@FindBy(css = "a[class='coveredCodes']")
	public WebElement		lnk_MNEcoveredCodes;

	/** to check non-covered codes are present on MNE Details Reference Panel */
	@FindBy(css = "a[class='noncovered']")
	public WebElement		lnk_MNEnonCoveredCodes;

	/** to click on first covered code on MNE Details Reference Panel */
	@FindBy(xpath = "(//a[@class='coveredCodes'])[1]")
	public WebElement		lnk_MNEFirstCoveredCode;

	/** to click on third covered code on MNE Details Reference Panel */
	@FindBy(xpath = "(//a[@class='coveredCodes'])[3]")
	public WebElement		lnk_MNEThirdCoveredCode;

	/** to scroll MNE Details Reference Panel */
	@FindBy(xpath = "(//div[@class='mCSB_dragger_bar'])[6]")
	public WebElement		MNEDetailsReferencePanel_Dragger;

	/** to click or get list of all admit probable code accepted button in coding panel */
	@FindBy(xpath = "//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-accepted-enabled']")
	public List<WebElement>	lst_acceptProbableAdmit;

	/** to click or get list of all principal probable code accepted button in coding panel */
	@FindBy(xpath = "//div[contains(@id,'primary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-accepted-enabled']")
	public List<WebElement>	lst_acceptProbablePrincipal;

	/** to click or get list of all secondary probable code accepted button in coding panel */
	@FindBy(xpath = "//div[contains(@id,'secondary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-accepted-enabled']")
	public List<WebElement>	lst_acceptProbableSecondary;

	/** to click or get list of all procedure probable code accepted button in coding panel */
	@FindBy(xpath = "//div[contains(@id,'procedure-code')]//div[@class='probable-option expand']//span[@id='code-accepted-enabled']")
	public List<WebElement>	lst_acceptProbableProcedure;

	/** to click on accept code link of associated code of admitting in coding panel after first probable code is added */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//span[@id='code-rejected-disabled'])[2]")
	public WebElement		lnk_associatedRejectAdmit;

	/** to click on accept code link of associated code of principal in coding panel after first probable code is added */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//span[@id='code-rejected-disabled'])[2]")
	public WebElement		lnk_associatedRejectPrincipal;

	/** to click on accept code link of associated code of secondary in coding panel after first probable code is added */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//span[@id='code-rejected-disabled'])[2]")
	public WebElement		lnk_associatedRejectSecondary;

	/** to click on accept code link of associated code of procedure in coding panel after first probable code is added */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//span[@id='code-rejected-disabled'])[2]")
	public WebElement		lnk_associatedRejectProcedure;

	/** to click on accept code link of associated code of procedure in coding panel after first probable code is added */
	@FindBy(xpath = "(//div[contains(@id,'cpt-code')]//span[@id='code-rejected-disabled'])[2]")
	public WebElement		lnk_associatedRejectCPT;

	/** to click on downarrow of first evidence of associated code of admitting diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand']/../..//div[@class='num-container num grey-code']/../..//span[@class='code-status-drop-down'])[1]")
	public WebElement		lnk_associatedRejectAdmit_arrow;

	/** to click on query physician link of first evidence of associated code of admitting in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand']/../..//div[@class='num-container num grey-code']/../..//a[@id='coding_panel_add_query'])[1]")
	public WebElement		lnk_associatedRejectAdmit_query;

	/** to click on downarrow of first evidence of associated code of principal diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[@class='probable-option expand']/../..//div[@class='num-container num grey-code']/../..//span[@class='code-status-drop-down'])[1]")
	public WebElement		lnk_associatedRejectPrincipal_arrow;

	/** to click on query physician link of first evidence of associated code of principal in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[@class='probable-option expand']/../..//div[@class='num-container num grey-code']/../..//a[@id='coding_panel_add_query'])[1]")
	public WebElement		lnk_associatedRejectPrincipal_query;

	/** to click on downarrow of first evidence of associated code of secondary diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='probable-option expand']/../..//div[@class='num-container num grey-code']/../..//span[@class='code-status-drop-down'])[1]")
	public WebElement		lnk_associatedRejectSecondary_arrow;

	/** to click on query physician link of first evidence of associated code of secondary in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='probable-option expand']/../..//div[@class='num-container num grey-code']/../..//a[@id='coding_panel_add_query'])[1]")
	public WebElement		lnk_associatedRejectSecondary_query;

	/** to click on downarrow of first evidence of associated code of procedure diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='probable-option expand']/../..//div[@class='num-container num grey-code']/../..//span[@class='code-status-drop-down'])[1]")
	public WebElement		lnk_associatedRejectProcedure_arrow;

	/** to click on query physician link of first evidence of associated code of procedure in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='probable-option expand']/../..//div[@class='num-container num grey-code']/../..//a[@id='coding_panel_add_query'])[1]")
	public WebElement		lnk_associatedRejectProcedure_query;

	/** to get link of rejected first probable code after add another probable code in admitting in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand'])[1]//span[contains(@id,'code-rejected')]")
	public WebElement		lnk_reject_probFirstAdmit;

	/** to get link of rejected first probable code after add another probable code in admitting in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[@class='probable-option expand'])[1]//span[contains(@id,'code-rejected')]")
	public WebElement		lnk_reject_probFirstPrimary;

	/** to get link of rejected first probable code after add another probable code in admitting in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='probable-option expand'])[1]//span[contains(@id,'code-rejected')]")
	public WebElement		lnk_reject_probFirstSecondary;

	/** to get link of rejected first probable code after add another probable code in admitting in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='probable-option expand'])[1]//span[contains(@id,'code-rejected')]")
	public WebElement		lnk_reject_probFirstProcdedure;

	/** to get link of rejected first probable code after add another probable code in admitting in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand'])[1]//span[contains(@id,'code-modified')]")
	public WebElement		lnk_modify_probFirstAdmit;

	/** to get link of rejected first probable code after add another probable code in admitting in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[@class='probable-option expand'])[1]//span[contains(@id,'code-modified')]")
	public WebElement		lnk_modify_probFirstPrimary;

	/** to get link of rejected first probable code after add another probable code in admitting in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='probable-option expand'])[1]//span[contains(@id,'code-modified')]")
	public WebElement		lnk_modify_probFirstSecondary;

	/** to get link of rejected first probable code after add another probable code in admitting in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='probable-option expand'])[1]//span[contains(@id,'code-modified')]")
	public WebElement		lnk_modify_probFirstProcdedure;

	/** get list or count of all probable code to the operation in admitting diagnosis in coding panel after CDS query */
	@FindBy(xpath = "//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand']")
	public List<WebElement>	lst_probableCount_Admit;

	/** to click on first disable modify link of first admitting code in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")
	public WebElement		lnk_modifyDesFirstAdmit;

	/** to click on first disable modify link of first principal code in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'primary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")
	public WebElement		lnk_modifyDesFirstPrincipal;

	/** to click on first disable modify link of first secondary code in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'secondary-diagnosis-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")
	public WebElement		lnk_modifyDesFirstSecondary;

	/** to click on first disable modify link of first procedure code in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[@class='expand']//span[@id='code-modified-disabled'])[1]")
	public WebElement		lnk_modifyDesFirstProcedure;

	/** to click on first modify link in probable code in admitting in coding panel */
	@FindBy(xpath = "//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")
	public WebElement		lnk_modifyProbDesFirstAdmit;

	/** to click on first modify link in probable code in principal in coding panel */
	@FindBy(xpath = "//div[contains(@id,'primary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")
	public WebElement		lnk_modifyProbDesFirstPrincipal;

	/** to click on first modify link in probable code in secondary in coding panel */
	@FindBy(xpath = "//div[contains(@id,'secondary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")
	public WebElement		lnk_modifyProbDesFirstSecondary;

	/** to click on first modify link in probable code in procedure in coding panel */
	@FindBy(xpath = "//div[contains(@id,'procedure-code')]//div[@class='probable-option expand']//span[@id='code-modified-disabled']")
	public WebElement		lnk_modifyProbDesFirstProcedure;
	/** to check Probable code of Principal diagnosis code is modified or not */
	@FindBy(xpath = "//div[contains(@id,'primary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-enabled']")
	public WebElement		ico_Modify_Probable_Principal;

	/** to check Probable code of Secondary diagnosis code is modified or not */
	@FindBy(xpath = "//div[contains(@id,'secondary-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-enabled']")
	public WebElement		ico_Modify_Probable_Secondary;

	/** to check Probable code of procedure code is modified or not */
	@FindBy(xpath = "//div[contains(@id,'procedure-code')]//div[@class='probable-option expand']//span[@id='code-modified-enabled']")
	public WebElement		ico_Modify_Probable_Procedure;

	/** to check Probable code of Admitting Diagnosis code is modified or not */
	@FindBy(xpath = "//div[contains(@id,'admitting-diagnosis-code')]//div[@class='probable-option expand']//span[@id='code-modified-enabled']")
	public WebElement		ico_Modify_Probable_Admitting;

	/** to get the rank of admiting diagnosis in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'admitting-diagnosis-code')]//div[@class='num-container num accepted-code'])[1]//ancestor::div[@class='expand']//span[@class='evidance-seq']")
	public WebElement		txt_admitingdiagnosisRank;

	/** to click on second Make it principal in Secondary dx in coding panel */
	@FindBy(xpath = "(//h2[@id='secondary']/..//span[@id='make-it-principal'])[2]")
	public WebElement		lnk_secondMakePrincipal;

	/** to click on copy evidence in first suggested evidence of secondary evidence in coding panel */
	@FindBy(xpath = "((//h2[@id='secondary']/..//div[@class='expand'])[1]//span[@class='brd code-status'])[1]//a[@id='copyEvidence']")
	public WebElement		lnk_copyEviSecondaryFirst;

	/** to click on drop down of first manual evidence in secondary Dx in coding panel */
	@FindBy(xpath = "((//h2[@id='secondary']/..//div[@class='expand'])[1]//span[@class='brd code-status manual'])[1]//span[@class='code-status-drop-down']")
	public WebElement		lst_man_firstSecond_evi_arrow;

	/** to click on copy evidence in first manual evidence of secondary evidence in coding panel */
	@FindBy(xpath = "((//h2[@id='secondary']/..//div[@class='expand'])[1]//span[@class='brd code-status manual'])[1]//a[@id='copyEvidence']")
	public WebElement		lnk_copyManEviSecondaryFirst;

	/** to click on accept button of Rejected principle diagnosis code */
	@FindBy(xpath = "//div[contains(@id,'primary-diagnosis-code')]//span[@id='code-accepted-disabled']")
	public WebElement		lnk_accept_principal_disabled;

	/** to get third code number in procedure diagnosis (for use in Inpatient ICD10) in coding panel */
	@FindBy(xpath = "(//div[contains(@id,'procedure-code')]//div[contains(@class,'num-container')])[3]")
	public WebElement		lbl_thirdProcedureCodeNum;
	static CodingPnl_WebE	INSTANCE	= null;

	public static CodingPnl_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, CodingPnl_WebE.class);
		}
		return INSTANCE;
	}

}
