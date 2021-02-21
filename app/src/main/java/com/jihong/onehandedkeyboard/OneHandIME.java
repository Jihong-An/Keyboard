package com.jihong.onehandedkeyboard;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class OneHandIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
    OneHandIME_View oneHandIME_View;

    // 0 : 자음만
    // 1 : 초성 + 중성
    // 2 : 초성 + 중성 (병서)
    // 3 : 초성 + 중성 + 종성
    // 4 : 초성 + 중성 + 종성 (병서)
    char hangulArray[] = new char[5];
    int hangulCount = 0;

    public View onCreateInputView() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

        this.oneHandIME_View = (OneHandIME_View) getLayoutInflater().inflate(R.layout.keyboard, null);
        this.oneHandIME_View.setIME(this);
        this.oneHandIME_View.side(pref.getString("side", ""));
        return this.oneHandIME_View;
    }

    public void right() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        // right : 오른손 모드
        // left : 왼손 모드

        editor.putString("side", "right");
        editor.apply();
    }

    public void left() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        // right : 오른손 모드
        // left : 왼손 모드

        editor.putString("side", "left");
        editor.apply();
    }

    public void outputText(String str) {
        if (str.equals("backspace")) {
            getCurrentInputConnection().deleteSurroundingText(1, 0);
            vibrator();
        } else {
            getCurrentInputConnection().commitText(str, 1);
            vibrator();
        }
    }

    public void outputEnter() {
        getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
        vibrator();
    }

    public void delOneText() {
        getCurrentInputConnection().deleteSurroundingText(1, 0);
        vibrator();
    }

    public boolean onEvaluateFullscreenMode() {
        return false;
    }

    public void swipeLeft() {
        // 왼쪽 제스처 (왼손 키보드)
        Log.e("Left", "Left");
    }

    @Override
    public void swipeRight() {
        // 오른쪽 제스처 (오른손 키보드)
        Log.e("Right", "Right");
    }

    @Override
    public void swipeUp() {
        // 위쪽 제스처 (평범한 쿼티 키보드)
        Log.e("Up", "Up");
    }

    @Override
    public void swipeDown() {
        // 아래쪽 제스처
        Log.e("Down", "Down");
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void hideWindow() {
        super.hideWindow();
        resetKeyboard(); // 키보드 저장값 초기화
        oneHandIME_View.resetKeyboardLanguage(); // 키보드 언어를 한국어로 변경
    }

    public void vibrator() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(8);
    }

    /**
     * 한글 만들기
     */

    // 한글 순서
    // ㄱ  ㄲ  ㄴ  ㄷ  ㄸ  ㄹ  ㅁ  ㅂ  ㅃ  ㅅ  ㅆ  ㅇ  ㅈ  ㅉ  ㅊ  ㅋ  ㅌ  ㅍ  ㅎ
    // 0   1  2   3  4   5  6  7   8   9  10 11 12  13 14  15 16  17 18
    private static final int[] cho = {
            12593, 12594, 12596, 12599, 12600, 12601, 12609, 12610,
            12611, 12613, 12614, 12615, 12616, 12617, 12618, 12619,
            12620, 12621, 12622};

    // ㅏ  ㅐ  ㅑ  ㅒ  ㅓ  ㅔ  ㅕ  ㅖ  ㅗ  ㅘ  ㅙ  ㅚ  ㅛ  ㅜ  ㅝ  ㅞ  ㅟ  ㅠ  ㅡ  ㅢ  ㅣ
    // 0   1  2   3  4   5  6   7  8  9  10  11 12  13  14 15 16  17 18  19 20
    private static final int[] jung = {
            12623, 12624, 12625, 12626, 12627, 12628, 12629, 12630,
            12631, 12632, 12633, 12634, 12635, 12636, 12637, 12638,
            12639, 12640, 12641, 12642, 12643};

    // ㄱ  ㄲ  ㄳ  ㄴ  ㄵ  ㄶ  ㄷ  ㄹ  ㄺ  ㄻ  ㄼ  ㄽ  ㄾ  ㄿ  ㅀ  ㅁ  ㅂ  ㅄ  ㅅ  ㅆ  ㅇ  ㅈ  ㅊ  ㅋ  ㅌ  ㅍ  ㅎ
    // 0   1  2   3  4  5   6  7   8  9  10  11 12  13 14  15 16  17 18 19  20  21 22 23  24 25  26
    private static final int[] jong = {
            12593, 12594, 12595, 12596, 12597, 12598, 12599, 12601,
            12602, 12603, 12604, 12605, 12606, 12607, 12608, 12609,
            12610, 12612, 12613, 12614, 12615, 12616, 12618, 12619,
            12620, 12621, 12622};

    char hangul; // 한글이 담길 char

    // 자음
    boolean lastIsJaum = false; // 마지막이 자음이면
    int lastJaumNo = 0; // 마지막 자음 숫자

    // 모음
    boolean lastIsMoum = false;

    // 이중모음
    boolean doubleMoum = false; // 이중모음 스위치 변수
    int lastMoumNo = 0;
    boolean lastIsDoubleMoum = false;

    // 종성
    boolean lastIsJongsung = false; // 마지막이 종성인지
    int lastJongsungNo = 0;
    boolean doubleJongsung = false; // 이중 종성을 위해 필요함

    int lastJongsungCode = 0;
    boolean lastIsDoubleJongsung = false;

    // 저장
    int lastCode = 0; // 마지막 코드 (종성 사용을 위해 만든 변수)

    // 뒤로가기를 위해 저장하는 배열 - 역 오토마타보다 훨씬 합리적


    /**
     * 한글 만들기
     */

    public void cho(int no) {
        // 마지막 입력이 자음
        lastIsJaum = true;
        lastIsMoum = false;

        // 마지막 no를 저장
        lastJaumNo = no;

        // 마지막 입력은 종성이 아님
        lastIsJongsung = false;

        doubleJongsung = false;

        lastIsDoubleMoum = false;

        lastIsDoubleJongsung = false;
    }

    // 초성
    public void madeHangulCho(int no) {
        // 종성(병서)가 4가지(ㄱ, ㄴ, ㄹ, ㅂ)밖에 없기 때문에 switch로 함
        // ㄱ - ㄳ
        // ㄴ - ㄵ ㄶ
        // ㄹ - ㄺ ㄻ ㄼ ㄽ ㄾ ㄿ ㅀ
        // ㅂ - ㅄ

        // 이중모음 입력 초기화
        doubleMoum = false;

        // 마지막 입력이 이중모음으로 변환 가능한 모음이면
        // 자음이 입력됬으니 이중모음으로 변환되는것은 무시한다
        // 1 4 8 10 13 된소리 면 종성 X
        if ((lastIsMoum == true || lastIsDoubleMoum == true) && no != 4 && no != 8 && no != 13) {
            if (doubleJongsung == true) {
                lastJaumNo = no;
                hangul = madeHangulDoubleJong(no);

                for (int i = 0; i < jong.length; i++) {
                    // 만약 초성 문자와 종성 문자가 같으면
                    if (cho[no] == jong[i]) {
                        no = i; // no에 i를 저장 후 break
                        break;
                    }
                }
                lastJongsungNo = no;
            } else {
                lastIsJaum = true;
                lastIsMoum = false;
                lastJaumNo = no;
                hangul = madeHangulJong(no);
            }

            // 종성(병서) 스위치 활성화
            if (no == 0 || no == 2 || no == 5 || no == 7) {
                doubleJongsung = true;
                lastIsMoum = true;
            } else {
                doubleJongsung = false;
                lastIsMoum = false;
            }
        } else {
            hangul = (char) cho[no];

            // 마지막 입력이 자음
            lastIsJaum = true;
            lastIsMoum = false;

            // 마지막 no를 저장
            lastJaumNo = no;

            // 마지막 입력은 종성이 아님
            lastIsJongsung = false;

            lastIsDoubleJongsung = false;

            // 한글 역 오토마타를 위해 저장
            hangulArray[0] = (char) cho[no];
            hangulCount = 1;
        }

        // 한글 입력
        inputText(hangul);
    }

    // 중성
    public void madeHangulJung(int no) {
        if (lastIsJaum == true) {
            // 만약 마지막 입력이 종성이라면
            if (lastIsJongsung == true) {
                if (lastIsDoubleJongsung == true) {

                    lastIsDoubleJongsung = false;

                    delOneText();
                    hangul = (char) lastJongsungCode;
                    inputText(hangul);
                } else {
                    // 이번 입력이 모음이므로 종성을 지운다.
                    lastCode = lastCode - lastJongsungNo - 1; // 종성은 1부터 시작이므로 지움

                    // 마지막 입력 지우기
                    delOneText();

                    hangul = (char) lastCode;
                    inputText(hangul);
                }
            }

            // 이중모음으로 사용이 가능한지 구분
            // 만약 이중모음으로 사용이 가능하면 이중모음으로 변환
            if (doubleMoum == true) {
                // ㅗ
                if (lastMoumNo == 8) {
                    switch (no) {
                        case 0: // ㅘ
                            delOneText();
                            lastCode = ((lastJaumNo * 21) + 9) * 28 + 44032;
                            break;
                        case 1: // ㅙ
                            delOneText();
                            lastCode = ((lastJaumNo * 21) + 10) * 28 + 44032;
                            break;
                        case 20: // ㅚ
                            delOneText();
                            lastCode = ((lastJaumNo * 21) + 11) * 28 + 44032;
                            break;
                        default: // 그냥 입력
                            lastCode = jung[no];
                            break;
                    }
                }

                // ㅜ
                if (lastMoumNo == 13) {
                    switch (no) {
                        case 4: // ㅝ
                            delOneText();
                            lastCode = ((lastJaumNo * 21) + 14) * 28 + 44032;
                            break;
                        case 5: // ㅞ
                            delOneText();
                            lastCode = ((lastJaumNo * 21) + 15) * 28 + 44032;
                            break;
                        case 20: // ㅟ
                            delOneText();
                            lastCode = ((lastJaumNo * 21) + 16) * 28 + 44032;
                            break;
                        default: // 그냥 입력
                            lastCode = jung[no];
                            break;
                    }
                }

                // ㅡ
                if (lastMoumNo == 18) {
                    switch (no) {
                        case 20: // ㅢ
                            delOneText();
                            lastCode = ((lastJaumNo * 21) + 19) * 28 + 44032;
                            break;
                        default: // 그냥 입력
                            lastCode = jung[no];
                            break;
                    }
                }

                hangul = (char) lastCode;

                doubleMoum = false;

                lastIsJaum = false; // 마지막 입력이 자음이 아님
                lastIsMoum = true; // 마지막 입력은 모음임
            } else {
                // ㅗ, ㅜ, ㅡ 면 이중모음으로 바꿈
                if ((no == 8 || no == 13 || no == 18)) {
                    // 이중모음 스위치 활성화
                    doubleMoum = true;

                    // 이중모음을 위해 마지막 모음을 저장한다
                    lastMoumNo = no;

                    lastIsJaum = true; // 마지막 입력이 자음으로 그대로 놔둠 (이중모음으로 변환 가능하면 변환)
                    lastIsMoum = false; // 마지막 입력은 모음이 아님

                    lastIsDoubleMoum = true; // 마지막 클릭이 이중모음으로 변환이 가능
                    // 다음 클릭이 자음이 되면 종성으로 가야함 그래서 필요한 변수
                } else {
                    doubleMoum = false;

                    lastIsJaum = false; // 마지막 입력이 자음이 아님
                    lastIsMoum = true; // 마지막 입력은 모음임
                }

                // 그냥 입력
                lastCode = ((lastJaumNo * 21) + no) * 28 + 44032;
                hangul = (char) lastCode;

                if (lastIsJongsung == false) {
                    delOneText(); // 마지막 글자 지우기
                } else {
                    // 마지막 입력은 종성이 아님
                    lastIsJongsung = false;
                }

                // 한글 역 오토마타를 위해 저장
                hangulArray[1] = hangul;
                hangulCount = 2;
            }
        } else {
            hangul = (char) jung[no];
            lastIsDoubleJongsung = false;
            lastIsDoubleMoum = false;
            lastIsJongsung = false;
            resetKeyboard();
        }
        doubleJongsung = false;

        inputText(hangul);
    }

    // 종성
    public char madeHangulJong(int no) {
        for (int i = 0; i < jong.length; i++) {
            // 만약 초성 문자와 종성 문자가 같으면
            if (cho[no] == jong[i]) {
                no = i; // no에 i를 저장 후 break
                break;
            }
        }

        // 마지막 단어 지우기
        delOneText();

        // 문자 만들기
        lastCode = lastCode + no + 1; // 종성은 1부터 시작이니 1을 더해줌
        hangul = (char) lastCode;

        // 마지막이 종성
        lastIsJongsung = true; // 만일 다음이 모음이 입력되면 종성을 지워야하기 때문
        lastJongsungNo = no;

        // 마지막 클릭이 자음
        lastIsJaum = true;
        lastIsMoum = false; // 모음이 아님

        lastIsDoubleMoum = false;

        // 마지막 종성 코드
        lastJongsungCode = lastCode;
        return hangul;
    }

    // 종음(병서)
    public char madeHangulDoubleJong(int no) {
        if (lastJongsungNo == 0) { // ㄱ
            switch (no) {
                case 9: // ㅅ (ㄳ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 2;
                    lastIsDoubleJongsung = true;
                    break;
                default: // 그냥 입력
                    lastCode = cho[no];
                    cho(no);
                    break;
            }
        }

        if (lastJongsungNo == 3) { // ㄴ
            switch (no) {
                case 12: // ㅈ (ㄵ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 4;
                    lastIsDoubleJongsung = true;
                    break;
                case 18: // ㅎ (ㄶ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 5;
                    lastIsDoubleJongsung = true;
                    break;
                default:
                    lastCode = cho[no];
                    cho(no);
                    break;
            }
        } else if (lastJongsungNo == 7) { // ㄹ
            switch (no) {
                case 0: // ㄱ (ㄺ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 8;
                    lastIsDoubleJongsung = true;
                    break;
                case 6: // ㅁ (ㄻ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 9;
                    lastIsDoubleJongsung = true;
                    break;
                case 7: // ㅂ (ㄼ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 10;
                    lastIsDoubleJongsung = true;
                    break;
                case 9: // ㅅ (ㄽ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 11;
                    lastIsDoubleJongsung = true;
                    break;
                case 16: // ㅌ (ㄾ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 12;
                    lastIsDoubleJongsung = true;
                    break;
                case 17: // ㅍ (ㄿ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 13;
                    lastIsDoubleJongsung = true;
                    break;
                case 18: // ㅎ (ㅀ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 14;
                    lastIsDoubleJongsung = true;
                    break;
                default:
                    lastCode = cho[no];
                    cho(no);
                    break;
            }
        } else if (lastJongsungNo == 16) { // ㅂ
            switch (no) {
                case 9: // ㅅ (ㅄ)
                    delOneText();
                    lastCode = lastCode - lastJongsungNo + 17;
                    lastIsDoubleJongsung = true;
                    break;
                default:
                    lastCode = cho[no];
                    cho(no);
                    break;
            }
        }

        lastIsJaum = true;
        lastIsMoum = false;
        doubleJongsung = false;
        lastIsDoubleMoum = false;

        hangul = (char) lastCode;
        return hangul;
    }

    // 키보드 초기화
    public void resetKeyboard() {
        // 자음
        lastIsJaum = false;
        lastJaumNo = 0;

        // 모음
        lastIsMoum = false;
        lastIsDoubleMoum = false;

        // 종성
        lastIsJongsung = false;
        lastJongsungNo = 0;

        // 저장
        lastCode = 0; // 마지막 코드 (종성 사용을 위해 만든 변수)
    }

    // 단어 입력
    public void inputText(char hangul) {
        outputText(String.valueOf(hangul)); // 입력
        oneHandIME_View.shiftReset(); // 아무거나 클릭하면 upSpace를 초기화 한다
    }
}
