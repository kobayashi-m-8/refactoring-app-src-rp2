package jp.co.sss.crud.exception;

/**
 * システム例外クラス
 * 業務処理中に発生した予期しない例外を表す。
 */
public class SystemException extends Exception {
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String message) {
        super(message);
    }
}
