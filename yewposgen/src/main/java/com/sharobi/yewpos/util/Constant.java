package com.sharobi.yewpos.util;

/**
 * @author Manodip
 *
 */

public class Constant {
	public final static String DEFAULT_LANG = "english";
	public final static Integer LOOSE_UNIT_ID =1;
	public final static Integer PACKING_UNIT_ID =2;
	//public final static Integer NO_OF_DUE =7;
	public final static String PURCHASE_INVOICE ="PIM";
	public final static String SALES_INVOICE ="SIM";
	public final static String SALES_INVOICE_QS ="SAL";
	public final static String SALES_INVOICE_RETURN_MEMO ="SRM";
	public final static String PURCHASE_INVOICE_RETURN_MEMO ="PRM";
	public final static String EXPIRY_INVOICE_MEMO ="EXP";
	public final static String VENDOR_PAY_MEMO ="VPM";
	public final static String CUSTOMER_PAY_MEMO ="CPM";
	public final static Integer ORDER_ALL =1;
	public final static Integer ORDER_PAID =2;
	public final static Integer ORDER_HOLD =3;
	public final static Integer SHOW_SALES_INVOICE_RETURN_ITEM_NOOFMONTHSBEFORE =1;
	public final static Integer SHOW_PURCHASE_INVOICE_RETURN_ITEM_NOOFMONTHSBEFORE =0;
	public final static String PURCHASE_ORDER ="PO";////
	
	public final static String SALE_ORDER_QS ="SO";
	public final static String SIZE ="size";
	public final static String COLOR ="colour";

	public final static String VENDOR_PAYMENT_QS ="VAP";
	public final static String CUSTOMER_PAYMENT_QS ="CUP";
	public final static String SALE_QS ="SAL";
	public final static String SALE_RETURN_PAYMENT_QS ="SALRTN";
	public final static String PURCHASE_PAYMENT_QS ="PUR";
	public final static String PURCHASE_RETURN_PAYMENT_QS ="PURRTN";
	public final static String EXPIRE_QS ="EXP";
	
	public final static String SALE_ORDER_PREFIX="SO";
	public final static String PUR_ORDER_PREFIX="PO";
	public final static String PUR_INVOICE_QS="PURINV";
	public final static String PUR_INVOICE_PREFIX="PIMV";
	

	//public final static Integer PROFIT_PERCENTAGE_OF_RETAILER = 20;
	/*Session Constant*/
	public final static String SES_LOGGED_IN_USER = "sesloggedinUser";
	public final static String SES_APP_MENU_LIST = "sesappMenuList";
	public final static String SES_LOGGED_IN_STORE="sesloggedinStore";
	public final static String SES_LOGGED_IN_LANG="selectedlang";
	public final static String SES_ALL_STORES_DATA="allstoresdata"; // new added 22.04.2019
	public final static String SES_LOGGED_IN_STORE_ID = "sesloggedinStoreId";// new added 22.04.2019
	public final static String SES_BILL_PREFIX_LIST="frefixlist";// new added 09.07.2019
	public final static String SES_BILL_PRINT_DATA="billprintdata";// new added 02.08.2019
	public final static String SES_BILL_PRINT_DETAILS_DATA="billprintdetailsdata";// new added 02.08.2019
	/*End Session Constant*/
	/*Main Menu*/
	public final static String HOME="home";
	public final static String POS="100";
	public final static String INVENTORY="200";
	public final static String PROCUREMENT="300";
	public final static String ACCOUNTS="400";
	public final static String STORE_MANAGEMENT="500";
	public final static String HR="600";
	public final static String USER_MANAGEMENT="700";
	public final static String REPORTS="800";
	public final static String ADMIN="900";

	/*End Main Menu*/
	/*Sub Menu*/
	public final static String CASH_MEMO="101";
	public final static String RET_MEMO="102";
	public final static String MC_CASH_MEMO="103";
	public final static String MC_RET_MEMO="104";
	public final static String REPRINT_CASH_MEMO="105";
	public final static String REPRINT_RET_MEMO="106";
	/* public final static String CUSTOMER="107"; */
	 public final static String CUSTOMER="201N"; /*Sayantan Mahanty added date-19/02/2020*/
	public final static String SALE_ORDER="107";
	public final static String SALE_ORDER_REG="108";
	public final static String SALE_TOUR_PLAN="109";
	public final static String SALE_MAN_TRACKING="110";
	public final static String INV_SETUP="201";
	public final static String STOCK="202";
	public final static String ITEM_SEARCH="203";
	public final static String PUR_INV="301";
	public final static String PUR_RET="302";
	/* public final static String VENDOR="303"; */
	 public final static String VENDOR="201O"; /*Sayantan Mahanty added date-19/02/2020*/
	public final static String PUR_INV_REG="304";
	public final static String PUR_RET_REG="305";
	public final static String PUR_INV_REG_SRCH="311";
	public final static String VENDORPAY="306";
	public final static String PUR_ORD="307";
	public final static String PUR_ORD_REG="308";
	public final static String REP_SALES="801";
	public final static String REP_PROC="802";
	public final static String REP_INV="803";
	public final static String REP_TAX="804";
	public static final String PUR_INV_NEW = "309";
	public static final String PUR_BILL_CHALAN = "310";
	public final static String USER_SETUP="901";
	public final static String ROLE_SETUP="902";
	public final static String MAPPING_SETUP="903";
	public final static String HR_SETUP="601";
	public final static String HR_SHIFTSCHEDULE="602";
	public final static String HR_ATTENDANCE="603";
	public final static String HR_LEAVE_REGISTER="604";
	/*
	 *  for account add on 15_2_2018
	 */
	public final static String REP_LEDGER="805";
	public final static String ACCOUNT_SETUP="401";
	public final static String ACCOUNT_ENTRY="402";

	/*End Sub Menu*/
	/*Child Sub Menu*/
	public final static String BRAND="201A";
	public final static String UNIT="201B";
	public final static String CAT="201C";
	public final static String SUBCAT="201D";
	public final static String MANUFACTURER="201E";
	public final static String DOCTOR="201F";
	public final static String RACK="201G";
	public final static String GROUP="201H";
	public final static String SCHEDULE="201I";
	public final static String ITEM="201J";
	public final static String CONTENT="201K";
	public final static String TAX="201L";
	public final static String STOCK_ENTRY="202A";
	public final static String STOCK_ADJ="202B";
	public final static String STOCK_EXPIRY_ISSUE="202C";
	public final static String STOCK_EXPIRY_ISSUEREG="202D";
	public final static String STOCK_EXPIRY_ISSUE_MANUAL="202E";
	public final static String STOCK_TRANSFER="202F";
	public final static String STOCK_TRANSFER_REG="202G";
	public final static String STOCK_RECEIVE="202H";
	public final static String STOCK_RECEIVE_REG="202I";
	public final static String STOCK_TRANSFER_QS ="STRNF";
	public final static String REP_SALES_SUMMARY="801A";
	public final static String REP_SALES_ITEM="801B";
	public final static String REP_SALES_REG="801C";
	public final static String REP_SALES_RETURN_SUMMARY="801D";
	public final static String REP_SALES_RETURN_ITEM="801E";
	public final static String REP_SALES_RETURN_REG="801F";
	public final static String REP_SALES_NON_MOVING="801G";
	public final static String REP_SALES_CUSTOMER_LEDGER="801H";
	public final static String REP_PROC_SUMMARY="802A";
	public final static String REP_PROC_ITEMWISE="802B";
	public final static String REP_PROC_REG="802C";
	public final static String REP_PROC_RETURN_SUMMARY="802D";
	public final static String REP_PROC_RETURN_ITEMWISE="802E";
	public final static String REP_PROC_RETURN_REG="802F";
	public final static String REP_PROC_FREE_QTY="802G";
	public final static String REP_PROC_VENDOR_LEDGER="802H";
	public final static String REP_PROC_PURCHASE_ORDER_ADJ="802J";
	public final static String REP_INV_REG="803A";
	public final static String REP_INV_EXP="803B";
	public final static String REP_INV_EXP_DISTWISE="803C";
	public final static String STOCK_ONVALUE="803D";
	public final static String GROUP_WISE_STOCK="803E";
	public final static String REP_TAX_INTEGRITY="804A";
	public final static String REP_TAX_SUMMARY="804B";
	public final static String REP_TAX_SLAB_SUMMARY="804C";
	public final static String REP_TAX_B2CS_SUMMARY="804D";
	public final static String REP_ESIONGC_SALES_REG="801I";
	public final static String REP_TAX_HSN_SUMMARY="804E";

	public final static String REP_VENDROWISE_STOCK="803F";
	public final static String REP_GSTR3B="804F";
	public final static String REP_GSTR9A="804G";
	public final static String REP_B2B="804H";
	
	public final static String CITY="201P";
	public final static String ZONE="201Q";
	public final static String AREA="201R";

	public final static String MARKETER="201M";
	public final static String SALESMAN="201S";
	public final static String CHARGEMASTER="201T";
	public final static String SIZEMASTER="201U";

	public final static String REP_LEDGER_REPORT="805A";
	public final static String REP_TRIAL_BALANCE_REPORT="805B";
	public final static String REP_DAILY_COLLECTION_REPORT="805D";
	public final static String REP_DAILY_PAYMENT_REPORT="805E";

	public final static String REP_BALANCE_SHEET_REPORT="805C";
	public final static String REP_ACCOUNT_BALANCE_REPORT="805F";
	public final static String REP_ACCOUNT_PROFIT_AND_LOSS_REPORT="805G";
		/*
		 *  for account add on 15_2_2018
		 */
	public final static String LEDGER="401A";
	public final static String ACCOUNT_SETUP_ACCOUNTSETUP="401B";
	public final static String ACCOUNT_JOURNAL="402A";
	public final static String ACCOUNT_CHARTOFACCOUNT="402B";
	//HR CHILD SUBMENU BY PROTHIT ON 28 NOVEMBER 2019//
	public final static String HR_DEPT="601A";
	public final static String HR_DESIG="601B";
	public final static String HR_EMP_TYPE="601C";
	public final static String HR_EMP="601D";
	public final static String HR_DUTYSHIFT="601E";

	/*End Child Sub Menu*/
}
