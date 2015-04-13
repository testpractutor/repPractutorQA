package com.ezdi.webelements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard_WebE
{
	/** to click on filter icon of Discharge Not Final Coded chart */
	@FindBy(xpath = "//div[@id='DischargeMainViewRender']/div/div[1]/div[4]")
	public WebElement	ico_dnfc;

	/** to click on filter of Coder Productivity chart */
	@FindBy(xpath = "//div[@id='coderProMain']/div/div[1]/div[4]")
	public WebElement	ico_Coder_Productivity;

	/** to click on filter of Query Response Rate chart for coder */
	@FindBy(xpath = "//div[@id='queryResponseMainViewRender']/div/div[1]/div[4]")
	public WebElement	ico_Query_Response_Rate;

	/** to click on filter of Top 10 DRG chart */
	@FindBy(xpath = "//div[@id='DRGMainViewRender']/div/div[1]/div[4]")
	public WebElement	ico_Top10_DRGs;

	/** to click on filter of Case Mix Index chart */
	@FindBy(xpath = "//div[@id='CaseMixMainViewRender']/div/div[1]/div[4]")
	public WebElement	ico_Case_Mix_Index;

	/** to click on filter of Accracy chart */
	@FindBy(xpath = "//div[@id='AccuracyMainViewRender']/div/div[1]/div[4]")
	public WebElement	ico_Accracy;

	/** to click on filter of Reviewer Productivity chart */
	@FindBy(xpath = "//div[@id='reviewerProMain']/div/div[1]/div[4]")
	public WebElement	ico_Reviewer_Productivity;

	/** to click on filter of CDI Query Status chart */
	@FindBy(xpath = "//div[@id='CDIqueryStatusMainViewRender']/div/div[1]/div[4]")
	public WebElement	ico_CDI_Query_Status;

	/** to click on filter of CDI Productivity chart */
	@FindBy(xpath = "//div[@id='CDIProductivityMainViewRender']/div/div[1]/div[4]")
	public WebElement	ico_CDI_Productivity;

	/** to click on filter of Query Response Rate chart for CDI user */
	@FindBy(xpath = "//div[@id='CDIqueryResponseMainViewRender']/div/div[1]/div[4]")
	public WebElement	ico_CDI_Query_Response_Rate;

	/** to click on filter of My Productivity Chart for coder and reviewer user role */
	@FindBy(xpath = "//div[@id='CoderMyproductivityMainViewRender']/div/div[1]/div[4]")
	public WebElement	ico_My_Productivity;

	/** to click on filter of My Accuracy chart for coder user role */
	@FindBy(xpath = "//div[@id='CoderMyAccuracyMainViewRender']/div/div[1]/div[4]")
	public WebElement	ico_My_Accuracy;

	/** to click on setting link of Coding Dashboard for Supervisor role */
	@FindBy(css = "#Codingsettings > h4")
	public WebElement	lnk_Setting_Coder_Supervisor;

	/** to click on Settings link of Reviewer and coder Dashboard for */
	@FindBy(css = "#Codersettings > h4")
	public WebElement	lnk_Setting_Reviewer_Supervisor;

	/** to click on settings link of CDI Dashboard for Supervisor user role */
	@FindBy(css = "#CDIsettings > h4")
	public WebElement	lnk_Setting_CDI_Supervisor;

	/** to click on Save button Settings menu */
	@FindBy(css = "#CodingsettingsSaveBtn")
	public WebElement	btn_Save;

	/** to click on date dropdown menu of Discharge Not Final Coded chart */
	@FindBy(css = "#dischargeFilter")
	public WebElement	lst_Date_DNFC;

	/** to click on date dropdown menu of Coder Productivity Chart */
	@FindBy(css = "#coderProDateFilter")
	public WebElement	lst_Date_Coder_Producitivity;

	/** to click on date dropdown menu of Query Response Rate Chart */
	@FindBy(css = "#queryDateFilter")
	public WebElement	lst_Date_Query_Response;

	/** to click on coding standard dropdown menu of Top 10 DRGs chart */
	@FindBy(css = "#codingStdFilter")
	public WebElement	lst_CodingStd_Top10_DRGs;

	/** to click on date dropdown menu of Case Mix Index chart */
	@FindBy(css = "#caseMixDateFilter")
	public WebElement	lst_Date_Case_Mix_Index;

	/** to click on coding standrad dropdown menu of Case Mix Index chart */
	@FindBy(css = "#CaseMixIndexCodSys")
	public WebElement	lst_CodingStd_Case_Mix_Index;

	/** to click on date dropdown menu of Accuracy chart */
	@FindBy(css = "#coderAccTimeFilter")
	public WebElement	lst_Date_Accuracy;

	/** to click on x-axis dropdown menu of Accuracy chart */
	@FindBy(css = "#accuracyXAxisFilter")
	public WebElement	lst_Xaxis_Accuracy;

	/** to click on Coding Standard dropdown menu of Accuracy chart */
	@FindBy(css = "#coderAccCodStd")
	public WebElement	lst_CodingStd_Accuracy;

	/** to click on date dropdown menu of Reviewer Productivity Chart */
	@FindBy(css = "#reviewerProDateFilter")
	public WebElement	lst_Date_Reviewer_Producitivity;

	/** to click on date dropdown menu of CDI Query Status chart */
	@FindBy(css = "#CDICaseTimeFilter")
	public WebElement	lst_Date_CDS_Query_Status;

	/** to click on X-axis dropdown menu of CDI Query Status chart */
	@FindBy(css = "#CDSXAxisFilter")
	public WebElement	lst_Xaxis_CDI_Query_Status;

	/** to click on date dropdown menu of CDI Productivity chart */
	@FindBy(css = "#CDIProTimeFilter")
	public WebElement	lst_Date_CDI_Productivity_Chart;

	/** to click on X-axis dropdown menu of CDI Productivity Chart */
	@FindBy(css = "#CDSProXAxisFilter")
	public WebElement	lst_Xaxis_CDI_Producitivity;

}
