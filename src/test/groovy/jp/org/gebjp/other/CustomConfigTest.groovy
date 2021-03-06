package jp.org.gebjp.other

import geb.spock.GebSpec

/**
 * ■公式マニュアル
 * ・7 Configuration
 * http://www.gebish.org/manual/current/configuration.html#configuration
 *
 * ■概要
 * "GebConfig.groovy"に共通設定を定義することで全テストケースで共有することが
 * できるというサンプルです
 *
 * 以下、googleログイン情報を"GebConfig.groovy"に入力してからテストを実行してください
 * ・googleUserId = "****@gmail.com"
 * ・googlePassword = "****"
 *
 * ■詳細
 * 下記、ブログを参照してください
 * http://yfj2.hateblo.jp/entry/2015/01/12/122426
 *
 * @author YukiFujisawa
 *
 */
class CustomConfigTest extends GebSpec {

  def "google login test"() {
    setup:
    def googleLoginUrl = "https://accounts.google.com"

    //GebConfig.groovyの設定値を読み込む
    def googleUserId = browser.config.rawConfig.googleUserId
    def googlePassword = browser.config.rawConfig.googlePassword

    when:
    go googleLoginUrl

    then:
    waitFor{ title == "ログイン - Google アカウント" }

    when:
    $("form").Email = googleUserId
    $("form").Passwd = googlePassword
    $("input" , name:"signIn").click()

    then:
    waitFor{ title == "アカウント設定"}
  }
}