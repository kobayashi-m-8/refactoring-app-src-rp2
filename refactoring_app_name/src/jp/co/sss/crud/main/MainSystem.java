package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;

/**
 * 社員情報管理システム開始クラス
 * 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 *
 * @author 
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 */
	public static void main(String[] args) {
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		// リファクタリング: menuNo → selectedMenu
		int selectedMenu = 0;

		try {
			do {
				// メニューの表示
				System.out.println("=== 社員管理システム ===");
				System.out.println("1.全件表示");
				System.out.println("2.社員名検索");
				System.out.println("3.部署ID検索");
				System.out.println("4.新規登録");
				System.out.println("5.更新");
				System.out.println("6.削除");
				System.out.println("7.終了");
				System.out.print("メニュー番号を入力してください：");

				// リファクタリング: menuNoStr → selectedMenuStr
				String selectedMenuStr = consoleReader.readLine();
				selectedMenu = Integer.parseInt(selectedMenuStr);

				// 機能の呼出
				switch (selectedMenu) {
				case 1:
					DBController.findAllEmployees();
					break;

				case 2:
					System.out.print("社員名:");
					DBController.findEmployeeByName();
					break;

				case 3:
					System.out.print("部署ID(1:営業部、2:経理部、3:総務部)を入力してください:");
					String inputDeptId = consoleReader.readLine();
					DBController.findEmployeeByDeptId(inputDeptId);
					break;

				case 4:
					System.out.print("社員名:");
					String inputEmployeeName = consoleReader.readLine();
					System.out.print("性別(0:その他, 1:男性, 2:女性, 9:回答なし):");
					String inputGender = consoleReader.readLine();
					System.out.print("生年月日(西暦年/月/日):");
					String inputBirthday = consoleReader.readLine();
					System.out.print("部署ID(1:営業部、2:経理部、3:総務部):");
					String inputDeptIdForInsert = consoleReader.readLine();

					DBController.insertEmployee(inputEmployeeName, inputGender, inputBirthday, inputDeptIdForInsert);
					break;

				case 5:
					System.out.print("更新する社員の社員IDを入力してください：");
					String inputEmployeeIdForUpdate = consoleReader.readLine();
					DBController.updateEmployee(inputEmployeeIdForUpdate);
					System.out.println("社員情報を更新しました");
					break;

				case 6:
					System.out.print("削除する社員の社員IDを入力してください：");
					DBController.deleteEmployee();
					break;
				}
			} while (selectedMenu != 7);

			System.out.println("システムを終了します。");

		} catch (IOException | SQLException | ClassNotFoundException | ParseException e) {
			e.printStackTrace();
		}
	}
}
