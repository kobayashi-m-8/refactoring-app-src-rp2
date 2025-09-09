package jp.co.sss.crud.util;

// 新規作成クラス
// 定数をまとめて管理
public class ConstantValue {

	// メニュー番号
	public static final int MENU_SHOW_ALL_EMPLOYEES = 1;
	public static final int MENU_SEARCH_EMPLOYEE_BY_NAME = 2;
	public static final int MENU_SEARCH_EMPLOYEE_BY_DEPT_ID = 3;
	public static final int MENU_REGISTER_EMPLOYEE = 4;
	public static final int MENU_UPDATE_EMPLOYEE = 5;
	public static final int MENU_DELETE_EMPLOYEE = 6;
	public static final int MENU_EXIT = 7;

	// メッセージ
	public static final String MSG_MENU = "1.全件表示 2.氏名検索 3.部署ID検索 4.登録 5.更新 6.削除 7.終了 > ";
	public static final String MSG_INPUT_EMPLOYEE_ID = "社員IDを入力してください";
	public static final String MSG_INPUT_EMPLOYEE_NAME = "社員名を入力してください";
	public static final String MSG_INPUT_EMPLOYEE_GENDER = "性別を入力してください(0:男 1:女)";
	public static final String MSG_INPUT_EMPLOYEE_BIRTHDAY = "誕生日を入力してください(yyyy/MM/dd)";
	public static final String MSG_INPUT_DEPARTMENT_ID = "部署IDを入力してください";
	public static final String MSG_INPUT_DEPARTMENT_NAME = "部署名を入力してください";
	public static final String MSG_PROCESS_FINISHED = "処理が終了しました。";
}