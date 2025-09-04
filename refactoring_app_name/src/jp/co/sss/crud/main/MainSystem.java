package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;

/**
 * 社員情報管理システム開始クラス 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 * 
 * 初回のコード変更
 *
 * @author System Shared
 *
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 *
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ParseException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int menuNo = 0;

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

			// メニュー番号の入力
			String menuNoStr = br.readLine();
			menuNo = Integer.parseInt(menuNoStr);

			// 機能の呼出
			switch (menuNo) {
			case 1:
				// 全件表示機能の呼出
				DBController.find();
				break;

			case 2:
				// 社員名検索
				System.out.print("社員名:");

				// 検索機能の呼出
				DBController.findB();
				break;

			case 3:
				// 検索する部署IDを入力
				System.out.print("部署ID(1:営業部、2:経理部、3:総務部)を入力してください:");
				String deptIdA = br.readLine();

				// 検索機能の呼出
				DBController.findC(deptIdA);
				break;

			case 4:
				// 登録する値を入力
				System.out.print("社員名:");
				String emp_name = br.readLine();
				System.out.print("性別(0:その他, 1:男性, 2:女性, 9:回答なし):");
				String Seibetsu = br.readLine();
				System.out.print("生年月日(西暦年/月/日):");
				String birthday = br.readLine();
				System.out.print("部署ID(1:営業部、2:経理部、3:総務部):");
				String deptIdB = br.readLine();

				// 登録機能の呼出
				DBController.insert(emp_name, Seibetsu, birthday, deptIdB);
				break;

			case 5:
				// 更新する社員IDを入力
				System.out.print("更新する社員の社員IDを入力してください：");

				// 更新する値を入力する
				String empId_1 = br.readLine();
				Integer.parseInt(empId_1);

				// 更新機能の呼出
				DBController.update(empId_1);
				System.out.println("社員情報を更新しました");

				break;

			case 6:
				// 削除する社員IDを入力
				System.out.print("削除する社員の社員IDを入力してください：");

				// 削除機能の呼出
				DBController.delete();
				break;

			}
		} while (menuNo != 7);
		System.out.println("システムを終了します。");
	}
}
