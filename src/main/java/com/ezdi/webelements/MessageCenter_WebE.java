package com.ezdi.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MessageCenter_WebE
{

	/** to click on New Message Button" */
	@FindBy(xpath = "//div[@class='float-r addMessageBtnContainer']/descendant::button")
	public WebElement			btn_NewMessage;

	/** to click on to dropdown */
	@FindBy(css = "#toUserId_chosen > a")
	public WebElement			lst_To;

	/** to enter user name in to dropdown */
	@FindBy(css = "div[class='chosen-search']>input")
	public WebElement			txt_To;

	/** to select Subject textbox */
	@FindBy(name = "subject")
	public WebElement			txt_Subject;

	/** to select Message textarea */
	@FindBy(id = "message")
	public WebElement			txt_Message;

	/** to select Cancel button */
	@FindBy(id = "addMsgCancelBtn")
	public WebElement			btn_Cancel;

	/** to select Send button */
	@FindBy(id = "addMsgSendBtn")
	public WebElement			btn_Send;

	/** to select Close icon */
	//@FindBy(css = "#mCSB_3 > div.mCSB_container.mCS_no_scrollbar > div > div > div > div:nth-child(1) > div.float-r > a")
	@FindBy(css = "a[class='message-close']")
	public WebElement			btn_Close;

	/** to click on View Case link */
	@FindBy(xpath = "(//a[text()='View Case'])[1]")
	public WebElement			lnk_ViewCase;

	/** to close view case in message center */
	@FindBy(id = "closeBtn")
	public WebElement			btn_ViewCaseClose;

	/** to select message table */
	@FindBy(id = "message-table")
	public WebElement			tbl_Messages;

	/** to click on Previous link */
	@FindBy(id = "prevPageLink")
	public WebElement			lnk_Previous;

	/** to click on Next link */
	@FindBy(id = "nextPageLink")
	public WebElement			lnk_Next;

	/** to click on Trash link */
	@FindBy(id = "trashList")
	public WebElement			lnk_Trash;

	/** to click on Inbox link */
	@FindBy(id = "inboxList")
	public WebElement			lnk_Inbox;

	/** to click on Reply button */
	@FindBy(id = "replayButton")
	public WebElement			btn_Reply;

	/** to select Reply textarea */
	@FindBy(id = "messageReplyTxt")
	public WebElement			txt_Reply;

	/** to select Cancel button for Reply */
	@FindBy(id = "cancelReplyBtn")
	public WebElement			btn_ReplyCancel;

	/** to select Send button for Reply */
	@FindBy(id = "sendReplyBtn")
	public WebElement			btn_ReplySend;

	/** to get total no. of messages */
	//@FindBy(xpath = "//div[@id='pagingContainer']/ul/li[1]/text()[normalize-space() and not(ancestor::strong)] [2]")
	//@FindBy(xpath = "//div[@id='pagingContainer']/ul/li[1]/text()[normalize-space()] [2]")
	@FindBy(xpath = "//div[@id='pagingContainer']/ul/li[1]")
	public WebElement			total_messages;

	/** To scroll Message Center Page */
	@FindBy(css = "div[class='mCSB_dragger_bar']")
	public WebElement			main_dragger;

	/** To click on Message Center title */
	@FindBy(css = "h1[class='float-l Mail']")
	public WebElement			lbl_MessageCenter;

	/** To click on Filter icon */
	@FindBy(id = "messagefilterImage")
	public WebElement			img_Filter;

	/** To click on Read Checkbox in Filter */
	@FindBy(id = "readFltChek")
	public WebElement			chk_Read;

	/** To click on Unread Checkbox in Filter */
	@FindBy(id = "unreadFltChek")
	public WebElement			chk_Unread;

	/** To click on Apply in Filter */
	@FindBy(id = "applyMessageFilter")
	public WebElement			lnk_Apply;

	/** To get count of Unread Messages */
	@FindBy(id = "unReadMessageCount")
	public WebElement			txt_UnreadMessageCount;

	/** To get alert message if there is no value in "To" field */
	@FindBy(css = "span[class='error-block']")
	public List<WebElement>		txt_MesageCenterAlerts;

	/** To select Yes radio button from Physician reply template */
	@FindBy(css = "input[id='Yes']")
	public WebElement			btn_Yes;

	/** To select No radio button from Physician reply template */
	@FindBy(css = "input[id='No']")
	public WebElement			btn_No;

	/** To select Undetermined radio button from Physician reply template */
	@FindBy(css = "input[id='Undetermined']")
	public WebElement			btn_Undetermined;

	/** To identify Pending status of query */
	@FindBy(css = "a[class='Pending-link']")
	public WebElement			lbl_PendingQuery;

	/** To identify Pending status of discussion */
	@FindBy(css = "a[class='pending-link Pending-bg']")
	public WebElement			lbl_PendingDiscussion;

	/** To identify Replied status of discussion */
	@FindBy(css = "a[class='pending-link Replied-bg']")
	public WebElement			lbl_RepliedDiscussion;

	/** To identify Resolved status of query */
	@FindBy(css = "a[class='Resolved-link']")
	public WebElement			lbl_ResolvedQuery;

	/** To identify Resolved status of discussion */
	@FindBy(css = "a[class='pending-link Resolved-bg']")
	public WebElement			lbl_ResolvedDiscussion;

	/** To view full query as a user */
	@FindBy(css = "a[id='viewConversation']")
	public WebElement			lnk_ViewFullQuery;

	/** To check message with subject 'Query for an account' is present */
	@FindBy(css = "span[class='query-icon']")
	public WebElement			lbl_QueryForAnAccount;

	/** To check 'mark as resolved' link is displayed */
	@FindBy(css = "a[class='float-r resolvedLink']")
	public WebElement			lnk_MarkAsResolved;

	/** To check if evidence on which discussion has been raised is displayed with pink color */
	@FindBy(css = "font[class='pink-blue-bg']")
	public WebElement			lbl_PinkEvidence;

	/** To view complete conversation */
	@FindBy(id = "viewConversation")
	public WebElement			lnk_ViewConversation;

	/** To collapse complete conversation */
	@FindBy(id = "collapseConversation")
	public WebElement			lnk_CollpaseConversation;

	/** To get assign message from inbox */
	@FindBy(className = "data white disabled")
	public WebElement			txt_Assignmsg_FromInbox;

	/** To get view case iframe */
	@FindBy(id = "iFrmviewCaseElement")
	public WebElement			fra_viewCase;

	static MessageCenter_WebE	INSTANCE	= null;

	public static MessageCenter_WebE getInstance(WebDriver driver)
	{
		if (INSTANCE == null)
		{
			INSTANCE = PageFactory.initElements(driver, MessageCenter_WebE.class);
		}
		return INSTANCE;
	}

}
