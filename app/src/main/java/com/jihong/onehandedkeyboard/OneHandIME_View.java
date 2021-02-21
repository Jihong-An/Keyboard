package com.jihong.onehandedkeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class OneHandIME_View extends View {
    String keyboardSide = "";

    OneHandIME OneHandIME;

    Context mContext;
    int screenHeight = 0;
    int screenWidth = 0;
    int viewHeight = 0;
    int viewWidth = 0;

    // upSpace 스위치
    boolean isUpSpace = false;

    // 키보드 언어
    // K : 한국어, E : 영어, N : 숫자 + 특수문자
    char keyboardType = 'K';

    public OneHandIME_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public void setIME(OneHandIME oneHandIME) {
        this.OneHandIME = oneHandIME;
    }

    public void side(String side) {
        keyboardSide = side;

        com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);

        // 왼손 모드 일때
        if (keyboardSide.equals("left")) {
            kb.setBackgroundResource(R.drawable.hangul_left);
        } else { // 오른손 모드일때
            kb.setBackgroundResource(R.drawable.hangul_right);
        }
    }

    /**
     * 스크린 크기 가져오기
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 폰 화면 크기
        this.screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        this.screenHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 화면에 그려질 키보드 크기
        this.viewWidth = this.screenWidth;
        this.viewHeight = this.screenHeight;

        setMeasuredDimension(viewWidth, viewHeight);

        viewHeight = viewWidth; // 가로와 세로 길이가 같게
        setKeyboardSize(viewWidth, (int) (viewHeight - (viewHeight * 0.18))); // 키보드 사이즈 조정
    }

    /**
     * 키보드 사이즈 조절
     */
    public void setKeyboardSize(int KeyboardWidth, int KeyboardHeight) {
        com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);
        ViewGroup.LayoutParams params = kb.getLayoutParams();
        params.width = KeyboardWidth;
        params.height = KeyboardHeight;
        kb.setLayoutParams(params);
    }

    /**
     * 키 터치 이벤트
     */
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        double x = event.getX();
        double y = event.getY();

        switch (action) {
            // 클릭했을때 : 좌표
            case MotionEvent.ACTION_DOWN:
                // 왼손 모드
                if (keyboardSide.equals("left")) {
                    switch (keyboardType) {
                        case 'K': // 한글 키보드
                            /** 1번째 줄 */
                            // ㅂ
                            if (x >= (viewWidth / 29.23) - viewWidth / 20 && x < ((viewWidth / 29.23) + viewWidth / 20)
                                    && y >= (viewHeight / 8.941) - viewWidth / 20 && y < ((viewHeight / 8.941) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulCho(7);
                                } else {
                                    OneHandIME.madeHangulCho(8);
                                }
                            }

                            // ㅈ
                            else if (x >= (viewWidth / 8.666) - viewWidth / 20 && x < ((viewWidth / 8.666) + viewWidth / 20)
                                    && y >= (viewHeight / 5.266) - viewWidth / 20 && y < ((viewHeight / 5.266) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulCho(12);
                                } else {
                                    OneHandIME.madeHangulCho(13);
                                }
                            }

                            // ㄷ
                            else if (x >= (viewWidth / 5.091) - viewWidth / 20 && x < ((viewWidth / 5.091) + viewWidth / 20)
                                    && y >= (viewHeight / 3.720) - viewWidth / 20 && y < ((viewHeight / 3.720) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulCho(3);
                                } else {
                                    OneHandIME.madeHangulCho(4);
                                }
                            }

                            // ㄱ
                            else if (x >= (viewWidth / 3.596) - viewWidth / 20 && x < ((viewWidth / 3.596) + viewWidth / 20)
                                    && y >= (viewHeight / 2.854) - viewWidth / 20 && y < ((viewHeight / 2.854) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulCho(0);
                                } else {
                                    OneHandIME.madeHangulCho(1);
                                }
                            }

                            // ㅅ
                            else if (x >= (viewWidth / 2.790) - viewWidth / 20 && x < ((viewWidth / 2.790) + viewWidth / 20)
                                    && y >= (viewHeight / 2.329) - viewWidth / 20 && y < ((viewHeight / 2.329) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulCho(9);
                                } else {
                                    OneHandIME.madeHangulCho(10);
                                }
                            }

                            // ㅗ
                            else if (x >= (viewWidth / 2.256) - viewWidth / 20 && x < ((viewWidth / 2.256) + viewWidth / 20)
                                    && y >= (viewHeight / 1.985) - viewWidth / 20 && y < ((viewHeight / 1.985) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(8);
                                } else {
                                    OneHandIME.madeHangulJung(12);
                                }
                            }

                            // ㅐ
                            else if (x >= (viewWidth / 1.934) - viewWidth / 20 && x < ((viewWidth / 1.934) + viewWidth / 20)
                                    && y >= (viewHeight / 1.705) - viewWidth / 20 && y < ((viewHeight / 1.705) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(1);
                                } else {
                                    OneHandIME.madeHangulJung(3);
                                }
                            }

                            // ㅔ
                            else if (x >= (viewWidth / 1.666) - viewWidth / 20 && x < ((viewWidth / 1.666) + viewWidth / 20)
                                    && y >= (viewHeight / 1.494) - viewWidth / 20 && y < ((viewHeight / 1.494) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(5);
                                } else {
                                    OneHandIME.madeHangulJung(7);
                                }
                            }

                            /** 2번째 줄 */
                            // ㅁ
                            if (x >= (viewWidth / 30.346) - viewWidth / 21 && x < ((viewWidth / 30.346) + viewWidth / 21)
                                    && y >= (viewHeight / 3.624) - viewWidth / 21 && y < ((viewHeight / 3.624) + viewWidth / 21)) {
                                OneHandIME.madeHangulCho(6);
                            }

                            // ㄴ
                            else if (x >= (viewWidth / 9.533) - viewWidth / 21 && x < ((viewWidth / 9.533) + viewWidth / 21)
                                    && y >= (viewHeight / 2.899) - viewWidth / 21 && y < ((viewHeight / 2.899) + viewWidth / 21)) {
                                OneHandIME.madeHangulCho(2);
                            }

                            // ㅇ
                            else if (x >= (viewWidth / 5.720) - viewWidth / 21 && x < ((viewWidth / 5.720) + viewWidth / 21)
                                    && y >= (viewHeight / 2.411) - viewWidth / 21 && y < ((viewHeight / 2.411) + viewWidth / 21)) {
                                OneHandIME.madeHangulCho(11);
                            }

                            // ㄹ
                            else if (x >= (viewWidth / 4.060) - viewWidth / 21 && x < ((viewWidth / 4.060) + viewWidth / 21)
                                    && y >= (viewHeight / 2.064) - viewWidth / 21 && y < ((viewHeight / 2.064) + viewWidth / 21)) {
                                OneHandIME.madeHangulCho(5);
                            }

                            // ㅎ
                            else if (x >= (viewWidth / 3.186) - viewWidth / 21 && x < ((viewWidth / 3.186) + viewWidth / 21)
                                    && y >= (viewHeight / 1.806) - viewWidth / 21 && y < ((viewHeight / 1.806) + viewWidth / 21)) {
                                OneHandIME.madeHangulCho(18);
                            }

                            // ㅓ
                            else if (x >= (viewWidth / 2.593) - viewWidth / 21 && x < ((viewWidth / 2.593) + viewWidth / 21)
                                    && y >= (viewHeight / 1.606) - viewWidth / 21 && y < ((viewHeight / 1.606) + viewWidth / 21)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(4);
                                } else {
                                    OneHandIME.madeHangulJung(6);
                                }
                            }

                            // ㅏ
                            else if (x >= (viewWidth / 2.193) - viewWidth / 21 && x < ((viewWidth / 2.193) + viewWidth / 21)
                                    && y >= (viewHeight / 1.457) - viewWidth / 21 && y < ((viewHeight / 1.457) + viewWidth / 21)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(0);
                                } else {
                                    OneHandIME.madeHangulJung(2);
                                }
                            }

                            // ㅣ
                            else if (x >= (viewWidth / 1.898) - viewWidth / 21 && x < ((viewWidth / 1.898) + viewWidth / 21)
                                    && y >= (viewHeight / 1.315) - viewWidth / 21 && y < ((viewHeight / 1.315) + viewWidth / 21)) {
                                OneHandIME.madeHangulJung(20);
                            }

                            /** 3번째 줄 */
                            // ㅋ
                            if (x >= (viewWidth / 27.350) - viewWidth / 22 && x < ((viewWidth / 27.350) + viewWidth / 22)
                                    && y >= (viewHeight / 2.223) - viewWidth / 22 && y < ((viewHeight / 2.223) + viewWidth / 22)) {
                                OneHandIME.madeHangulCho(15);
                            }

                            // ㅌ
                            else if (x >= (viewWidth / 9.900) - viewWidth / 22 && x < ((viewWidth / 9.900) + viewWidth / 22)
                                    && y >= (viewHeight / 1.959) - viewWidth / 22 && y < ((viewHeight / 1.959) + viewWidth / 22)) {
                                OneHandIME.madeHangulCho(16);
                            }

                            // ㅊ
                            else if (x >= (viewWidth / 6.150) - viewWidth / 22 && x < ((viewWidth / 6.150) + viewWidth / 22)
                                    && y >= (viewHeight / 1.748) - viewWidth / 22 && y < ((viewHeight / 1.748) + viewWidth / 22)) {
                                OneHandIME.madeHangulCho(14);
                            }

                            // ㅍ
                            else if (x >= (viewWidth / 4.386) - viewWidth / 22 && x < ((viewWidth / 4.386) + viewWidth / 22)
                                    && y >= (viewHeight / 1.575) - viewWidth / 22 && y < ((viewHeight / 1.575) + viewWidth / 22)) {
                                OneHandIME.madeHangulCho(17);
                            }

                            // ㅜ
                            else if (x >= (viewWidth / 3.286) - viewWidth / 22 && x < ((viewWidth / 3.286) + viewWidth / 22)
                                    && y >= (viewHeight / 1.436) - viewWidth / 22 && y < ((viewHeight / 1.436) + viewWidth / 22)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(13);
                                } else {
                                    OneHandIME.madeHangulJung(17);
                                }
                            }

                            // ㅡ
                            else if (x >= (viewWidth / 2.781) - viewWidth / 20 && x < ((viewWidth / 2.781) + viewWidth / 20)
                                    && y >= (viewHeight / 1.318) - viewWidth / 20 && y < ((viewHeight / 1.318) + viewWidth / 20)) {
                                OneHandIME.madeHangulJung(18);
                            }

                            /** 4번째 줄 */
                            // 한글 - 영어 - 숫자
                            if (x >= (viewWidth / 20.333) - viewWidth / 20 && x < ((viewWidth / 20.333) + viewWidth / 20)
                                    && y >= (viewHeight / 1.689) - viewWidth / 20 && y < ((viewHeight / 1.689) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                changeKeyboard('E');

                                OneHandIME.vibrator();
                            }

                            // enter
                            else if (x >= (viewWidth / 7.852) - viewWidth / 20 && x < ((viewWidth / 7.852) + viewWidth / 20)
                                    && y >= (viewHeight / 1.486) - viewWidth / 20 && y < ((viewHeight / 1.486) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                OneHandIME.outputEnter();
                            }

                            // 뒤로가기
                            else if (x >= (viewWidth / 4.913) - viewWidth / 20 && x < ((viewWidth / 4.913) + viewWidth / 20)
                                    && y >= (viewHeight / 1.336) - viewWidth / 20 && y < ((viewHeight / 1.336) + viewWidth / 20)) {
                                OneHandIME.delOneText();

                                OneHandIME.resetKeyboard();
                            }

                            // Space
                            else if (x >= (26) - viewWidth / 11.52 && x < ((26) + viewWidth / 11.52)
                                    && y >= (viewHeight / 1.22) - viewWidth / 11.52 && y < ((viewHeight / 1.22) + viewWidth / 11.52)) {
                                OneHandIME.outputText(" "); // 공백 입력
                                OneHandIME.resetKeyboard();
                            }

                            // upSpace
                            if (x >= (viewWidth / 1.473) - viewWidth / 20 && x < ((viewWidth / 1.473) + viewWidth / 20)
                                    && y >= (viewHeight / 1.339) - viewWidth / 20 && y < ((viewHeight / 1.339) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);
                                    kb.setBackgroundResource(R.drawable.hangul_shift_left);
                                    isUpSpace = true;
                                } else {
                                    com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);
                                    kb.setBackgroundResource(R.drawable.hangul_left);
                                    isUpSpace = false;
                                }

                                OneHandIME.vibrator();
                            }

                            if (x >= (viewWidth / 1.158) - viewWidth / 22 && x < ((viewWidth / 1.158) + viewWidth / 22)
                                    && y >= (viewHeight / 1.355) - viewWidth / 22 && y < ((viewHeight / 1.355) + viewWidth / 22)) {
                                side("right");
                                OneHandIME.right();

                                OneHandIME.resetKeyboard(); // 키보드 저장값 초기화
                                OneHandIME.oneHandIME_View.resetKeyboardLanguage(); // 키보드 언어를 한국어로 변경
                            }
                            break; // case KoreaKeyboard end

                        case 'E': // 영어 키패드
                            /** 1번째 줄 */
                            // q
                            if (x >= (viewWidth / 23.638) - viewWidth / 20 && x < ((viewWidth / 23.638) + viewWidth / 20)
                                    && y >= (viewHeight / 9.713) - viewWidth / 20 && y < ((viewHeight / 9.713) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('q');
                                } else {
                                    inputText('Q');
                                }
                            }

                            // w
                            else if (x >= (viewWidth / 9.945) - viewWidth / 20 && x < ((viewWidth / 9.945) + viewWidth / 20)
                                    && y >= (viewHeight / 5.841) - viewWidth / 20 && y < ((viewHeight / 5.841) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('w');
                                } else {
                                    inputText('W');
                                }
                            }

                            // e
                            else if (x >= (viewWidth / 6.103) - viewWidth / 20 && x < ((viewWidth / 6.103) + viewWidth / 20)
                                    && y >= (viewHeight / 4.257) - viewWidth / 20 && y < ((viewHeight / 4.257) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('e');
                                } else {
                                    inputText('E');
                                }
                            }

                            // r
                            else if (x >= (viewWidth / 4.427) - viewWidth / 20 && x < ((viewWidth / 4.427) + viewWidth / 20)
                                    && y >= (viewHeight / 3.364) - viewWidth / 20 && y < ((viewHeight / 3.364) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('r');
                                } else {
                                    inputText('R');
                                }
                            }

                            // t
                            else if (x >= (viewWidth / 3.490) - viewWidth / 20 && x < ((viewWidth / 3.490) + viewWidth / 20)
                                    && y >= (viewHeight / 2.732) - viewWidth / 20 && y < ((viewHeight / 2.732) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('t');
                                } else {
                                    inputText('T');
                                }
                            }

                            // y
                            else if (x >= (viewWidth / 2.778) - viewWidth / 20 && x < ((viewWidth / 2.778) + viewWidth / 20)
                                    && y >= (viewHeight / 2.370) - viewWidth / 20 && y < ((viewHeight / 2.370
                            ) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('y');
                                } else {
                                    inputText('Y');
                                }
                            }

                            // u
                            else if (x >= (viewWidth / 2.392) - viewWidth / 20 && x < ((viewWidth / 2.392) + viewWidth / 20)
                                    && y >= (viewHeight / 2.043) - viewWidth / 20 && y < ((viewHeight / 2.043) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('u');
                                } else {
                                    inputText('U');
                                }
                            }

                            // i
                            else if (x >= (viewWidth / 2.094) - viewWidth / 20 && x < ((viewWidth / 2.094) + viewWidth / 20)
                                    && y >= (viewHeight / 1.792) - viewWidth / 20 && y < ((viewHeight / 1.792) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('i');
                                } else {
                                    inputText('I');
                                }
                            }

                            // o
                            else if (x >= (viewWidth / 1.835) - viewWidth / 20 && x < ((viewWidth / 1.835) + viewWidth / 20)
                                    && y >= (viewHeight / 1.620) - viewWidth / 20 && y < ((viewHeight / 1.620) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('o');
                                } else {
                                    inputText('O');
                                }
                            }

                            // p
                            else if (x >= (viewWidth / 1.631) - viewWidth / 20 && x < ((viewWidth / 1.631) + viewWidth / 20)
                                    && y >= (viewHeight / 1.481) - viewWidth / 20 && y < ((viewHeight / 1.481) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('p');
                                } else {
                                    inputText('P');
                                }
                            }

                            /** 2번째 줄 */
                            // a
                            if (x >= (viewWidth / 24.082) - viewWidth / 20 && x < ((viewWidth / 24.082) + viewWidth / 20)
                                    && y >= (viewHeight / 3.647) - viewWidth / 20 && y < ((viewHeight / 3.647) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('a');
                                } else {
                                    inputText('A');
                                }
                            }

                            // s
                            else if (x >= (viewWidth / 10.159) - viewWidth / 20 && x < ((viewWidth / 10.159) + viewWidth / 20)
                                    && y >= (viewHeight / 3.018) - viewWidth / 20 && y < ((viewHeight / 3.018) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('s');
                                } else {
                                    inputText('S');
                                }
                            }

                            // d
                            else if (x >= (viewWidth / 6.566) - viewWidth / 20 && x < ((viewWidth / 6.566) + viewWidth / 20)
                                    && y >= (viewHeight / 2.545) - viewWidth / 20 && y < ((viewHeight / 2.545) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('d');
                                } else {
                                    inputText('D');
                                }
                            }

                            // f
                            else if (x >= (viewWidth / 4.791) - viewWidth / 20 && x < ((viewWidth / 4.791) + viewWidth / 20)
                                    && y >= (viewHeight / 2.224) - viewWidth / 20 && y < ((viewHeight / 2.224) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('f');
                                } else {
                                    inputText('F');
                                }
                            }

                            // g
                            else if (x >= (viewWidth / 3.624) - viewWidth / 20 && x < ((viewWidth / 3.624) + viewWidth / 20)
                                    && y >= (viewHeight / 2.006) - viewWidth / 20 && y < ((viewHeight / 2.006) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('g');
                                } else {
                                    inputText('G');
                                }
                            }

                            // h
                            else if (x >= (viewWidth / 3.092) - viewWidth / 20 && x < ((viewWidth / 3.092) + viewWidth / 20)
                                    && y >= (viewHeight / 1.775) - viewWidth / 20 && y < ((viewHeight / 1.775) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('h');
                                } else {
                                    inputText('H');
                                }
                            }

                            // j
                            else if (x >= (viewWidth / 2.587) - viewWidth / 20 && x < ((viewWidth / 2.587) + viewWidth / 20)
                                    && y >= (viewHeight / 1.618) - viewWidth / 20 && y < ((viewHeight / 1.618) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('j');
                                } else {
                                    inputText('J');
                                }
                            }

                            // k
                            else if (x >= (viewWidth / 2.289) - viewWidth / 20 && x < ((viewWidth / 2.289) + viewWidth / 20)
                                    && y >= (viewHeight / 1.478) - viewWidth / 20 && y < ((viewHeight / 1.478) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('k');
                                } else {
                                    inputText('K');
                                }
                            }

                            // l
                            else if (x >= (viewWidth / 2.020) - viewWidth / 20 && x < ((viewWidth / 2.020) + viewWidth / 20)
                                    && y >= (viewHeight / 1.361) - viewWidth / 20 && y < ((viewHeight / 1.361) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('l');
                                } else {
                                    inputText('L');
                                }
                            }

                            /** 3번째 줄 */
                            // z
                            if (x >= (viewWidth / 26.468) - viewWidth / 20 && x < ((viewWidth / 26.468) + viewWidth / 20)
                                    && y >= (viewHeight / 2.298) - viewWidth / 20 && y < ((viewHeight / 2.298) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('z');
                                } else {
                                    inputText('Z');
                                }
                            }

                            // x
                            else if (x >= (viewWidth / 10.892) - viewWidth / 20 && x < ((viewWidth / 10.892) + viewWidth / 20)
                                    && y >= (viewHeight / 2.049) - viewWidth / 20 && y < ((viewHeight / 2.049) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('x');
                                } else {
                                    inputText('X');
                                }
                            }

                            // c
                            else if (x >= (viewWidth / 6.749) - viewWidth / 20 && x < ((viewWidth / 6.749) + viewWidth / 20)
                                    && y >= (viewHeight / 1.846) - viewWidth / 20 && y < ((viewHeight / 1.846) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('c');
                                } else {
                                    inputText('C');
                                }
                            }

                            // v
                            else if (x >= (viewWidth / 4.975) - viewWidth / 20 && x < ((viewWidth / 4.975) + viewWidth / 20)
                                    && y >= (viewHeight / 1.685) - viewWidth / 20 && y < ((viewHeight / 1.685) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('v');
                                } else {
                                    inputText('V');
                                }
                            }

                            // b
                            else if (x >= (viewWidth / 3.892) - viewWidth / 20 && x < ((viewWidth / 3.892) + viewWidth / 20)
                                    && y >= (viewHeight / 1.543) - viewWidth / 20 && y < ((viewHeight / 1.543) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('b');
                                } else {
                                    inputText('B');
                                }
                            }

                            // n
                            else if (x >= (viewWidth / 3.214) - viewWidth / 20 && x < ((viewWidth / 3.214) + viewWidth / 20)
                                    && y >= (viewHeight / 1.429) - viewWidth / 20 && y < ((viewHeight / 1.429) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('n');
                                } else {
                                    inputText('N');
                                }
                            }

                            // m
                            else if (x >= (viewWidth / 2.692) - viewWidth / 20 && x < ((viewWidth / 2.692) + viewWidth / 20)
                                    && y >= (viewHeight / 1.318) - viewWidth / 20 && y < ((viewHeight / 1.318) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('m');
                                } else {
                                    inputText('M');
                                }
                            }

                            /** 4번째 줄 */
                            // 한글 - 영어 - 숫자
                            if (x >= (viewWidth / 20.333) - viewWidth / 20 && x < ((viewWidth / 20.333) + viewWidth / 20)
                                    && y >= (viewHeight / 1.689) - viewWidth / 20 && y < ((viewHeight / 1.689) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                changeKeyboard('N');

                                OneHandIME.vibrator();
                            }

                            // enter
                            else if (x >= (viewWidth / 7.852) - viewWidth / 20 && x < ((viewWidth / 7.852) + viewWidth / 20)
                                    && y >= (viewHeight / 1.486) - viewWidth / 20 && y < ((viewHeight / 1.486) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                OneHandIME.outputEnter();
                            }

                            // 뒤로가기
                            else if (x >= (viewWidth / 4.913) - viewWidth / 20 && x < ((viewWidth / 4.913) + viewWidth / 20)
                                    && y >= (viewHeight / 1.336) - viewWidth / 20 && y < ((viewHeight / 1.336) + viewWidth / 20)) {
                                OneHandIME.delOneText();

                                OneHandIME.resetKeyboard();
                            }

                            // Space
                            else if (x >= (26) - viewWidth / 11.52 && x < ((26) + viewWidth / 11.52)
                                    && y >= (viewHeight / 1.22) - viewWidth / 11.52 && y < ((viewHeight / 1.22) + viewWidth / 11.52)) {
                                OneHandIME.outputText(" "); // 공백 입력
                                OneHandIME.resetKeyboard();
                            }

                            // upSpace
                            if (x >= (viewWidth / 1.473) - viewWidth / 20 && x < ((viewWidth / 1.473) + viewWidth / 20)
                                    && y >= (viewHeight / 1.339) - viewWidth / 20 && y < ((viewHeight / 1.339) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);
                                    kb.setBackgroundResource(R.drawable.english_shift_left);
                                    isUpSpace = true;
                                } else {
                                    com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);
                                    kb.setBackgroundResource(R.drawable.english_left);
                                    isUpSpace = false;
                                }

                                OneHandIME.vibrator();
                            }

                            if (x >= (viewWidth / 1.158) - viewWidth / 22 && x < ((viewWidth / 1.158) + viewWidth / 22)
                                    && y >= (viewHeight / 1.355) - viewWidth / 22 && y < ((viewHeight / 1.355) + viewWidth / 22)) {
                                side("right");
                                OneHandIME.right();

                                OneHandIME.resetKeyboard(); // 키보드 저장값 초기화
                                OneHandIME.oneHandIME_View.resetKeyboardLanguage(); // 키보드 언어를 한국어로 변경
                            }
                            break; // case EnglishKeyboard end
                        case 'N': // 숫자 & 특수문자 키패드
                            /** 1번째 줄 */
                            // 1
                            if (x >= (viewWidth / 28.686) - viewWidth / 20 && x < ((viewWidth / 28.686) + viewWidth / 20)
                                    && y >= (viewHeight / 9.336) - viewWidth / 20 && y < ((viewHeight / 9.336) + viewWidth / 20)) {
                                inputText('1');
                            }

                            // 2
                            else if (x >= (viewWidth / 9.574) - viewWidth / 20 && x < ((viewWidth / 9.574) + viewWidth / 20)
                                    && y >= (viewHeight / 5.658) - viewWidth / 20 && y < ((viewHeight / 5.658) + viewWidth / 20)) {
                                inputText('2');
                            }

                            // 3
                            else if (x >= (viewWidth / 5.639) - viewWidth / 20 && x < ((viewWidth / 5.639) + viewWidth / 20)
                                    && y >= (viewHeight / 4.010) - viewWidth / 20 && y < ((viewHeight / 4.010) + viewWidth / 20)) {
                                inputText('3');
                            }

                            // 4
                            else if (x >= (viewWidth / 3.995) - viewWidth / 20 && x < ((viewWidth / 3.995) + viewWidth / 20)
                                    && y >= (viewHeight / 3.104) - viewWidth / 20 && y < ((viewHeight / 3.104) + viewWidth / 20)) {
                                inputText('4');
                            }

                            // 5
                            else if (x >= (viewWidth / 3.098) - viewWidth / 20 && x < ((viewWidth / 3.098) + viewWidth / 20)
                                    && y >= (viewHeight / 2.535) - viewWidth / 20 && y < ((viewHeight / 2.535) + viewWidth / 20)) {
                                inputText('5');
                            }

                            // 6
                            else if (x >= (viewWidth / 2.527) - viewWidth / 20 && x < ((viewWidth / 2.527) + viewWidth / 20)
                                    && y >= (viewHeight / 2.141) - viewWidth / 20 && y < ((viewHeight / 2.141) + viewWidth / 20)) {
                                inputText('6');
                            }

                            // 7
                            else if (x >= (viewWidth / 2.138) - viewWidth / 20 && x < ((viewWidth / 2.138) + viewWidth / 20)
                                    && y >= (viewHeight / 1.860) - viewWidth / 20 && y < ((viewHeight / 1.860) + viewWidth / 20)) {
                                inputText('7');
                            }

                            // 8
                            else if (x >= (viewWidth / 1.847) - viewWidth / 20 && x < ((viewWidth / 1.847) + viewWidth / 20)
                                    && y >= (viewHeight / 1.634) - viewWidth / 20 && y < ((viewHeight / 1.634) + viewWidth / 20)) {
                                inputText('8');
                            }

                            // 9
                            else if (x >= (viewWidth / 1.626) - viewWidth / 20 && x < ((viewWidth / 1.626) + viewWidth / 20)
                                    && y >= (viewHeight / 1.461) - viewWidth / 20 && y < ((viewHeight / 1.461) + viewWidth / 20)) {
                                inputText('9');
                            }

                            // 0
                            else if (x >= (viewWidth / 1.456) - viewWidth / 20 && x < ((viewWidth / 1.456) + viewWidth / 20)
                                    && y >= (viewHeight / 1.321) - viewWidth / 20 && y < ((viewHeight / 1.321) + viewWidth / 20)) {
                                inputText('0');
                            }

                            /** 2번째 줄 */
                            // @
                            if (x >= (viewWidth / 19.164) - viewWidth / 20 && x < ((viewWidth / 19.164) + viewWidth / 20)
                                    && y >= (viewHeight / 3.559) - viewWidth / 20 && y < ((viewHeight / 3.559) + viewWidth / 20)) {
                                inputText('@');
                            }

                            // #
                            else if (x >= (viewWidth / 9.634) - viewWidth / 20 && x < ((viewWidth / 9.634) + viewWidth / 20)
                                    && y >= (viewHeight / 2.918) - viewWidth / 20 && y < ((viewHeight / 2.918) + viewWidth / 20)) {
                                inputText('#');
                            }

                            // $
                            else if (x >= (viewWidth / 6.184) - viewWidth / 20 && x < ((viewWidth / 6.184) + viewWidth / 20)
                                    && y >= (viewHeight / 2.515) - viewWidth / 20 && y < ((viewHeight / 2.515) + viewWidth / 20)) {
                                inputText('$');
                            }

                            // /
                            else if (x >= (viewWidth / 4.641) - viewWidth / 20 && x < ((viewWidth / 4.641) + viewWidth / 20)
                                    && y >= (viewHeight / 2.192) - viewWidth / 20 && y < ((viewHeight / 2.192) + viewWidth / 20)) {
                                inputText('/');
                            }

                            // %
                            else if (x >= (viewWidth / 3.681) - viewWidth / 20 && x < ((viewWidth / 3.681) + viewWidth / 20)
                                    && y >= (viewHeight / 1.952) - viewWidth / 20 && y < ((viewHeight / 1.952) + viewWidth / 20)) {
                                inputText('%');
                            }

                            // -
                            else if (x >= (viewWidth / 3.020) - viewWidth / 20 && x < ((viewWidth / 3.020) + viewWidth / 20)
                                    && y >= (viewHeight / 1.770) - viewWidth / 20 && y < ((viewHeight / 1.770) + viewWidth / 20)) {
                                inputText('-');
                            }

                            // +
                            else if (x >= (viewWidth / 2.610) - viewWidth / 20 && x < ((viewWidth / 2.610) + viewWidth / 20)
                                    && y >= (viewHeight / 1.597) - viewWidth / 20 && y < ((viewHeight / 1.597) + viewWidth / 20)) {
                                inputText('+');
                            }

                            // (
                            else if (x >= (viewWidth / 2.260) - viewWidth / 20 && x < ((viewWidth / 2.260) + viewWidth / 20)
                                    && y >= (viewHeight / 1.476) - viewWidth / 20 && y < ((viewHeight / 1.476) + viewWidth / 20)) {
                                inputText('(');
                            }

                            // )
                            else if (x >= (viewWidth / 2.005) - viewWidth / 20 && x < ((viewWidth / 2.005) + viewWidth / 20)
                                    && y >= (viewHeight / 1.361) - viewWidth / 20 && y < ((viewHeight / 1.361) + viewWidth / 20)) {
                                inputText(')');
                            }

                            /** 3번째 줄 */
                            // *
                            if (x >= (viewWidth / 27.844) - viewWidth / 21 && x < ((viewWidth / 27.844) + viewWidth / 21)
                                    && y >= (viewHeight / 2.308) - viewWidth / 21 && y < ((viewHeight / 2.308) + viewWidth / 21)) {
                                inputText('*');
                            }

                            // "
                            else if (x >= (viewWidth / 11.199) - viewWidth / 21 && x < ((viewWidth / 11.199) + viewWidth / 21)
                                    && y >= (viewHeight / 2.047) - viewWidth / 21 && y < ((viewHeight / 2.047) + viewWidth / 21)) {
                                inputText('"');
                            }

                            // .
                            else if (x >= (viewWidth / 6.071) - viewWidth / 21 && x < ((viewWidth / 6.071) + viewWidth / 21)
                                    && y >= (viewHeight / 1.927) - viewWidth / 21 && y < ((viewHeight / 1.927) + viewWidth / 21)) {
                                inputText('.');
                            }

                            // :
                            else if (x >= (viewWidth / 4.786) - viewWidth / 21 && x < ((viewWidth / 4.786) + viewWidth / 21)
                                    && y >= (viewHeight / 1.715) - viewWidth / 21 && y < ((viewHeight / 1.715) + viewWidth / 21)) {
                                inputText(':');
                            }

                            // ;
                            else if (x >= (viewWidth / 3.756) - viewWidth / 21 && x < ((viewWidth / 3.756) + viewWidth / 21)
                                    && y >= (viewHeight / 1.576) - viewWidth / 21 && y < ((viewHeight / 1.576) + viewWidth / 21)) {
                                inputText(';');
                            }

                            // !
                            else if (x >= (viewWidth / 3.213) - viewWidth / 21 && x < ((viewWidth / 3.213) + viewWidth / 21)
                                    && y >= (viewHeight / 1.434) - viewWidth / 21 && y < ((viewHeight / 1.434) + viewWidth / 21)) {
                                inputText('!');
                            }

                            // ?
                            else if (x >= (viewWidth / 2.743) - viewWidth / 21 && x < ((viewWidth / 2.743) + viewWidth / 21)
                                    && y >= (viewHeight / 1.330) - viewWidth / 21 && y < ((viewHeight / 1.330) + viewWidth / 21)) {
                                inputText('?');
                            }

                            /** 4번째 줄 */
                            // 한글 - 영어 - 숫자
                            if (x >= (viewWidth / 20.333) - viewWidth / 20 && x < ((viewWidth / 20.333) + viewWidth / 20)
                                    && y >= (viewHeight / 1.689) - viewWidth / 20 && y < ((viewHeight / 1.689) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                changeKeyboard('K');

                                OneHandIME.vibrator();
                            }

                            // enter
                            else if (x >= (viewWidth / 7.852) - viewWidth / 20 && x < ((viewWidth / 7.852) + viewWidth / 20)
                                    && y >= (viewHeight / 1.486) - viewWidth / 20 && y < ((viewHeight / 1.486) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                OneHandIME.outputEnter();
                            }

                            // 뒤로가기
                            else if (x >= (viewWidth / 4.913) - viewWidth / 20 && x < ((viewWidth / 4.913) + viewWidth / 20)
                                    && y >= (viewHeight / 1.336) - viewWidth / 20 && y < ((viewHeight / 1.336) + viewWidth / 20)) {
                                OneHandIME.delOneText();

                                OneHandIME.resetKeyboard();
                            }

                            // Space
                            else if (x >= (26) - viewWidth / 11.52 && x < ((26) + viewWidth / 11.52)
                                    && y >= (viewHeight / 1.22) - viewWidth / 11.52 && y < ((viewHeight / 1.22) + viewWidth / 11.52)) {
                                OneHandIME.outputText(" "); // 공백 입력
                                OneHandIME.resetKeyboard();
                            }

                            if (x >= (viewWidth / 1.158) - viewWidth / 22 && x < ((viewWidth / 1.158) + viewWidth / 22)
                                    && y >= (viewHeight / 1.355) - viewWidth / 22 && y < ((viewHeight / 1.355) + viewWidth / 22)) {
                                side("right");
                                OneHandIME.right();

                                OneHandIME.resetKeyboard(); // 키보드 저장값 초기화
                                OneHandIME.oneHandIME_View.resetKeyboardLanguage(); // 키보드 언어를 한국어로 변경
                            }
                            break; // case number & special letters keyboard end
                    }

                } else if (keyboardSide.equals("right")) { // 오른손 모드
                    switch (keyboardType) {
                        case 'K': // 한글 키보드
                            /** 1번째 줄 */
                            // ㅂ
                            if (x >= (viewWidth / 3.28) - viewWidth / 20 && x < ((viewWidth / 3.28) + viewWidth / 20)
                                    && y >= (viewHeight / 1.277) - viewWidth / 20 && y < ((viewHeight / 1.277) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulCho(7);
                                } else {
                                    OneHandIME.madeHangulCho(8);
                                }
                            }

                            // ㅈ
                            else if (x >= (viewWidth / 2.601) - viewWidth / 20 && x < ((viewWidth / 2.601) + viewWidth / 20)
                                    && y >= (viewHeight / 1.426) - viewWidth / 20 && y < ((viewHeight / 1.426) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulCho(12);
                                } else {
                                    OneHandIME.madeHangulCho(13);
                                }
                            }

                            // ㄷ
                            else if (x >= (viewWidth / 2.15) - viewWidth / 20 && x < ((viewWidth / 2.15) + viewWidth / 20)
                                    && y >= (viewHeight / 1.614) - viewWidth / 20 && y < ((viewHeight / 1.614) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulCho(3);
                                } else {
                                    OneHandIME.madeHangulCho(4);
                                }
                            }

                            // ㄱ
                            else if (x >= (viewWidth / 1.829) - viewWidth / 20 && x < ((viewWidth / 1.829) + viewWidth / 20)
                                    && y >= (viewHeight / 1.891) - viewWidth / 20 && y < ((viewHeight / 1.891) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulCho(0);
                                } else {
                                    OneHandIME.madeHangulCho(1);
                                }
                            }

                            // ㅅ
                            else if (x >= (viewWidth / 1.592) - viewWidth / 20 && x < ((viewWidth / 1.592) + viewWidth / 20)
                                    && y >= (viewHeight / 2.168) - viewWidth / 20 && y < ((viewHeight / 2.168) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulCho(9);
                                } else {
                                    OneHandIME.madeHangulCho(10);
                                }
                            }

                            // ㅗ
                            else if (x >= (viewWidth / 1.41) - viewWidth / 20 && x < ((viewWidth / 1.41) + viewWidth / 20)
                                    && y >= (viewHeight / 2.627) - viewWidth / 20 && y < ((viewHeight / 2.627) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(8);
                                } else {
                                    OneHandIME.madeHangulJung(12);
                                }
                            }

                            // ㅐ
                            else if (x >= (viewWidth / 1.266) - viewWidth / 20 && x < ((viewWidth / 1.266) + viewWidth / 20)
                                    && y >= (viewHeight / 3.444) - viewWidth / 20 && y < ((viewHeight / 3.444) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(1);
                                } else {
                                    OneHandIME.madeHangulJung(3);
                                }
                            }

                            // ㅔ
                            else if (x >= (viewWidth / 1.148) - viewWidth / 20 && x < ((viewWidth / 1.148) + viewWidth / 20)
                                    && y >= (viewHeight / 4.897) - viewWidth / 20 && y < ((viewHeight / 4.897) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(5);
                                } else {
                                    OneHandIME.madeHangulJung(7);
                                }
                            }

                            /** 2번째 줄 */
                            // ㅁ
                            if (x >= (viewWidth / 2.114) - viewWidth / 21 && x < ((viewWidth / 2.114) + viewWidth / 21)
                                    && y >= (viewHeight / 1.269) - viewWidth / 21 && y < ((viewHeight / 1.269) + viewWidth / 21)) {
                                OneHandIME.madeHangulCho(6);
                            }

                            // ㄴ
                            else if (x >= (viewWidth / 1.843) - viewWidth / 21 && x < ((viewWidth / 1.843) + viewWidth / 21)
                                    && y >= (viewHeight / 1.399) - viewWidth / 21 && y < ((viewHeight / 1.399) + viewWidth / 21)) {
                                OneHandIME.madeHangulCho(2);
                            }

                            // ㅇ
                            else if (x >= (viewWidth / 1.63) - viewWidth / 21 && x < ((viewWidth / 1.63) + viewWidth / 21)
                                    && y >= (viewHeight / 1.554) - viewWidth / 21 && y < ((viewHeight / 1.554) + viewWidth / 21)) {
                                OneHandIME.madeHangulCho(11);
                            }

                            // ㄹ
                            else if (x >= (viewWidth / 1.463) - viewWidth / 21 && x < ((viewWidth / 1.463) + viewWidth / 21)
                                    && y >= (viewHeight / 1.752) - viewWidth / 21 && y < ((viewHeight / 1.752) + viewWidth / 21)) {
                                OneHandIME.madeHangulCho(5);
                            }

                            // ㅎ
                            else if (x >= (viewWidth / 1.327) - viewWidth / 21 && x < ((viewWidth / 1.327) + viewWidth / 21)
                                    && y >= (viewHeight / 1.992) - viewWidth / 21 && y < ((viewHeight / 1.992) + viewWidth / 21)) {
                                OneHandIME.madeHangulCho(18);
                            }

                            // ㅓ
                            else if (x >= (viewWidth / 1.215) - viewWidth / 21 && x < ((viewWidth / 1.215) + viewWidth / 21)
                                    && y >= (viewHeight / 2.331) - viewWidth / 21 && y < ((viewHeight / 2.331) + viewWidth / 21)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(4);
                                } else {
                                    OneHandIME.madeHangulJung(6);
                                }
                            }

                            // ㅏ
                            else if (x >= (viewWidth / 1.127) - viewWidth / 21 && x < ((viewWidth / 1.127) + viewWidth / 21)
                                    && y >= (viewHeight / 2.799) - viewWidth / 21 && y < ((viewHeight / 2.799) + viewWidth / 21)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(0);
                                } else {
                                    OneHandIME.madeHangulJung(2);
                                }
                            }

                            // ㅣ
                            else if (x >= (viewWidth / 1.039) - viewWidth / 21 && x < ((viewWidth / 1.039) + viewWidth / 21)
                                    && y >= (viewHeight / 3.507) - viewWidth / 21 && y < ((viewHeight / 3.507) + viewWidth / 21)) {
                                OneHandIME.madeHangulJung(20);
                            }

                            /** 3번째 줄 */
                            // ㅋ
                            if (x >= (viewWidth / 1.542) - viewWidth / 22 && x < ((viewWidth / 1.542) + viewWidth / 22)
                                    && y >= (viewHeight / 1.275) - viewWidth / 22 && y < ((viewHeight / 1.275) + viewWidth / 22)) {
                                OneHandIME.madeHangulCho(15);
                            }

                            // ㅌ
                            else if (x >= (viewWidth / 1.409) - viewWidth / 22 && x < ((viewWidth / 1.409) + viewWidth / 22)
                                    && y >= (viewHeight / 1.391) - viewWidth / 22 && y < ((viewHeight / 1.391) + viewWidth / 22)) {
                                OneHandIME.madeHangulCho(16);
                            }

                            // ㅊ
                            else if (x >= (viewWidth / 1.295) - viewWidth / 22 && x < ((viewWidth / 1.295) + viewWidth / 22)
                                    && y >= (viewHeight / 1.524) - viewWidth / 22 && y < ((viewHeight / 1.524) + viewWidth / 22)) {
                                OneHandIME.madeHangulCho(14);
                            }

                            // ㅍ
                            else if (x >= (viewWidth / 1.197) - viewWidth / 22 && x < ((viewWidth / 1.197) + viewWidth / 22)
                                    && y >= (viewHeight / 1.697) - viewWidth / 22 && y < ((viewHeight / 1.697) + viewWidth / 22)) {
                                OneHandIME.madeHangulCho(17);
                            }

                            // ㅜ
                            else if (x >= (viewWidth / 1.114) - viewWidth / 22 && x < ((viewWidth / 1.114) + viewWidth / 22)
                                    && y >= (viewHeight / 1.954) - viewWidth / 22 && y < ((viewHeight / 1.954) + viewWidth / 22)) {
                                if (isUpSpace == false) {
                                    OneHandIME.madeHangulJung(13);
                                } else {
                                    OneHandIME.madeHangulJung(17);
                                }
                            }

                            // ㅡ
                            else if (x >= (viewWidth / 1.041) - viewWidth / 20 && x < ((viewWidth / 1.041) + viewWidth / 20)
                                    && y >= (viewHeight / 2.196) - viewWidth / 20 && y < ((viewHeight / 2.196) + viewWidth / 20)) {
                                OneHandIME.madeHangulJung(18);
                            }

                            /** 4번째 줄 */
                            // 한글 - 영어 - 숫자
                            if (x >= (viewWidth / 1.256) - viewWidth / 20 && x < ((viewWidth / 1.256) + viewWidth / 20)
                                    && y >= (viewHeight / 1.300) - viewWidth / 20 && y < ((viewHeight / 1.300) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                changeKeyboard('E');

                                OneHandIME.vibrator();
                            }

                            // enter
                            if (x >= (viewWidth / 1.144) - viewWidth / 20 && x < ((viewWidth / 1.144) + viewWidth / 20)
                                    && y >= (viewHeight / 1.445) - viewWidth / 20 && y < ((viewHeight / 1.445) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                OneHandIME.outputEnter();
                            }

                            // 뒤로가기
                            if (x >= (viewWidth / 1.049) - viewWidth / 20 && x < ((viewWidth / 1.049) + viewWidth / 20)
                                    && y >= (viewHeight / 1.64) - viewWidth / 20 && y < ((viewHeight / 1.64) + viewWidth / 20)) {
                                OneHandIME.delOneText();

                                OneHandIME.resetKeyboard();
                            }

                            // Space
                            if (x >= (viewWidth / 1.02) - viewWidth / 11.52 && x < ((viewWidth / 1.02) + viewWidth / 11.52)
                                    && y >= (viewHeight / 1.22) - viewWidth / 11.52 && y < ((viewHeight / 1.22) + viewWidth / 11.52)) {
                                OneHandIME.outputText(" "); // 공백 입력
                                OneHandIME.resetKeyboard();
                            }

                            // upSpace
                            if (x >= (viewWidth / 1.054) - viewWidth / 20 && x < ((viewWidth / 1.054) + viewWidth / 20)
                                    && y >= (viewHeight / 7.659) - viewWidth / 20 && y < ((viewHeight / 7.659) + viewWidth / 20)) {
                                com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);

                                if (isUpSpace == false) {
                                    kb.setBackgroundResource(R.drawable.hangul_shift_right);
                                    isUpSpace = true;
                                } else {
                                    kb.setBackgroundResource(R.drawable.hangul_right);
                                    isUpSpace = false;
                                }

                                OneHandIME.vibrator();
                            }

                            if (x >= (viewWidth / 7.091) - viewWidth / 22 && x < ((viewWidth / 7.091) + viewWidth / 22)
                                    && y >= (viewHeight / 1.355) - viewWidth / 22 && y < ((viewHeight / 1.355) + viewWidth / 22)) {
                                side("left");
                                OneHandIME.left();

                                OneHandIME.resetKeyboard(); // 키보드 저장값 초기화
                                OneHandIME.oneHandIME_View.resetKeyboardLanguage(); // 키보드 언어를 한국어로 변경
                            }
                            break; // case KoreaKeyboard end
                        case 'E': // 영어 키패드
                            /** 1번째 줄 */
                            // q
                            if (x >= (viewWidth / 3.275) - viewWidth / 20 && x < ((viewWidth / 3.275) + viewWidth / 20)
                                    && y >= (viewHeight / 1.287) - viewWidth / 20 && y < ((viewHeight / 1.287) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('q');
                                } else {
                                    inputText('Q');
                                }
                            }

                            // w
                            else if (x >= (viewWidth / 2.677) - viewWidth / 20 && x < ((viewWidth / 2.677) + viewWidth / 20)
                                    && y >= (viewHeight / 1.394) - viewWidth / 20 && y < ((viewHeight / 1.394) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('w');
                                } else {
                                    inputText('W');
                                }
                            }

                            // e
                            else if (x >= (viewWidth / 2.287) - viewWidth / 20 && x < ((viewWidth / 2.287) + viewWidth / 20)
                                    && y >= (viewHeight / 1.534) - viewWidth / 20 && y < ((viewHeight / 1.534) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('e');
                                } else {
                                    inputText('E');
                                }
                            }

                            // r
                            else if (x >= (viewWidth / 2.001) - viewWidth / 20 && x < ((viewWidth / 2.001) + viewWidth / 20)
                                    && y >= (viewHeight / 1.700) - viewWidth / 20 && y < ((viewHeight / 1.700) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('r');
                                } else {
                                    inputText('R');
                                }
                            }

                            // t
                            else if (x >= (viewWidth / 1.759) - viewWidth / 20 && x < ((viewWidth / 1.759) + viewWidth / 20)
                                    && y >= (viewHeight / 1.902) - viewWidth / 20 && y < ((viewHeight / 1.902) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('t');
                                } else {
                                    inputText('T');
                                }
                            }

                            // y
                            else if (x >= (viewWidth / 1.602) - viewWidth / 20 && x < ((viewWidth / 1.602) + viewWidth / 20)
                                    && y >= (viewHeight / 2.221) - viewWidth / 20 && y < ((viewHeight / 2.221) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('y');
                                } else {
                                    inputText('Y');
                                }
                            }

                            // u
                            else if (x >= (viewWidth / 1.445) - viewWidth / 20 && x < ((viewWidth / 1.445) + viewWidth / 20)
                                    && y >= (viewHeight / 2.561) - viewWidth / 20 && y < ((viewHeight / 2.561) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('u');
                                } else {
                                    inputText('U');
                                }
                            }

                            // i
                            else if (x >= (viewWidth / 1.315) - viewWidth / 20 && x < ((viewWidth / 1.315) + viewWidth / 20)
                                    && y >= (viewHeight / 3.036) - viewWidth / 20 && y < ((viewHeight / 3.036) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('i');
                                } else {
                                    inputText('I');
                                }
                            }

                            // o
                            else if (x >= (viewWidth / 1.220) - viewWidth / 20 && x < ((viewWidth / 1.220) + viewWidth / 20)
                                    && y >= (viewHeight / 3.848) - viewWidth / 20 && y < ((viewHeight / 3.848) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('o');
                                } else {
                                    inputText('O');
                                }
                            }

                            // p
                            else if (x >= (viewWidth / 1.139) - viewWidth / 20 && x < ((viewWidth / 1.139) + viewWidth / 20)
                                    && y >= (viewHeight / 5.264) - viewWidth / 20 && y < ((viewHeight / 5.264) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('p');
                                } else {
                                    inputText('P');
                                }
                            }

                            /** 2번째 줄 */
                            // a
                            if (x >= (viewWidth / 2.098) - viewWidth / 20 && x < ((viewWidth / 2.098) + viewWidth / 20)
                                    && y >= (viewHeight / 1.286) - viewWidth / 20 && y < ((viewHeight / 1.286) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('a');
                                } else {
                                    inputText('A');
                                }
                            }

                            // s
                            else if (x >= (viewWidth / 1.874) - viewWidth / 20 && x < ((viewWidth / 1.874) + viewWidth / 20)
                                    && y >= (viewHeight / 1.390) - viewWidth / 20 && y < ((viewHeight / 1.390) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('s');
                                } else {
                                    inputText('S');
                                }
                            }

                            // d
                            else if (x >= (viewWidth / 1.680) - viewWidth / 20 && x < ((viewWidth / 1.680) + viewWidth / 20)
                                    && y >= (viewHeight / 1.506) - viewWidth / 20 && y < ((viewHeight / 1.506) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('d');
                                } else {
                                    inputText('D');
                                }
                            }

                            // f
                            else if (x >= (viewWidth / 1.534) - viewWidth / 20 && x < ((viewWidth / 1.534) + viewWidth / 20)
                                    && y >= (viewHeight / 1.651) - viewWidth / 20 && y < ((viewHeight / 1.651) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('f');
                                } else {
                                    inputText('F');
                                }
                            }

                            // g
                            else if (x >= (viewWidth / 1.426) - viewWidth / 20 && x < ((viewWidth / 1.426) + viewWidth / 20)
                                    && y >= (viewHeight / 1.863) - viewWidth / 20 && y < ((viewHeight / 1.863) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('g');
                                } else {
                                    inputText('G');
                                }
                            }

                            // h
                            else if (x >= (viewWidth / 1.306) - viewWidth / 20 && x < ((viewWidth / 1.306) + viewWidth / 20)
                                    && y >= (viewHeight / 2.050) - viewWidth / 20 && y < ((viewHeight / 2.050) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('h');
                                } else {
                                    inputText('H');
                                }
                            }

                            // j
                            else if (x >= (viewWidth / 1.219) - viewWidth / 20 && x < ((viewWidth / 1.219) + viewWidth / 20)
                                    && y >= (viewHeight / 2.365) - viewWidth / 20 && y < ((viewHeight / 2.365) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('j');
                                } else {
                                    inputText('J');
                                }
                            }

                            // k
                            else if (x >= (viewWidth / 1.137) - viewWidth / 20 && x < ((viewWidth / 1.137) + viewWidth / 20)
                                    && y >= (viewHeight / 2.694) - viewWidth / 20 && y < ((viewHeight / 2.694) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('k');
                                } else {
                                    inputText('K');
                                }
                            }

                            // l
                            else if (x >= (viewWidth / 1.067) - viewWidth / 20 && x < ((viewWidth / 1.067) + viewWidth / 20)
                                    && y >= (viewHeight / 3.211) - viewWidth / 20 && y < ((viewHeight / 3.211) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('l');
                                } else {
                                    inputText('L');
                                }
                            }

                            /** 3번째 줄 */
                            // z
                            if (x >= (viewWidth / 1.568) - viewWidth / 20 && x < ((viewWidth / 1.568) + viewWidth / 20)
                                    && y >= (viewHeight / 1.279) - viewWidth / 20 && y < ((viewHeight / 1.279) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('z');
                                } else {
                                    inputText('Z');
                                }
                            }

                            // x
                            else if (x >= (viewWidth / 1.448) - viewWidth / 20 && x < ((viewWidth / 1.448) + viewWidth / 20)
                                    && y >= (viewHeight / 1.377) - viewWidth / 20 && y < ((viewHeight / 1.377) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('x');
                                } else {
                                    inputText('X');
                                }
                            }

                            // c
                            else if (x >= (viewWidth / 1.344) - viewWidth / 20 && x < ((viewWidth / 1.344) + viewWidth / 20)
                                    && y >= (viewHeight / 1.497) - viewWidth / 20 && y < ((viewHeight / 1.497) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('c');
                                } else {
                                    inputText('C');
                                }
                            }

                            // v
                            else if (x >= (viewWidth / 1.257) - viewWidth / 20 && x < ((viewWidth / 1.257) + viewWidth / 20)
                                    && y >= (viewHeight / 1.629) - viewWidth / 20 && y < ((viewHeight / 1.629) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('v');
                                } else {
                                    inputText('V');
                                }
                            }

                            // b
                            else if (x >= (viewWidth / 1.176) - viewWidth / 20 && x < ((viewWidth / 1.176) + viewWidth / 20)
                                    && y >= (viewHeight / 1.798) - viewWidth / 20 && y < ((viewHeight / 1.798) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('b');
                                } else {
                                    inputText('B');
                                }
                            }

                            // n
                            else if (x >= (viewWidth / 1.108) - viewWidth / 20 && x < ((viewWidth / 1.108) + viewWidth / 20)
                                    && y >= (viewHeight / 1.998) - viewWidth / 20 && y < ((viewHeight / 1.998) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('n');
                                } else {
                                    inputText('N');
                                }
                            }

                            // m
                            else if (x >= (viewWidth / 1.040) - viewWidth / 20 && x < ((viewWidth / 1.040) + viewWidth / 20)
                                    && y >= (viewHeight / 2.281) - viewWidth / 20 && y < ((viewHeight / 2.281) + viewWidth / 20)) {
                                if (isUpSpace == false) {
                                    inputText('m');
                                } else {
                                    inputText('M');
                                }
                            }
                            /** 4번째 줄 */
                            // 한글 - 영어 - 숫자
                            if (x >= (viewWidth / 1.256) - viewWidth / 20 && x < ((viewWidth / 1.256) + viewWidth / 20)
                                    && y >= (viewHeight / 1.300) - viewWidth / 20 && y < ((viewHeight / 1.300) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                changeKeyboard('N');

                                OneHandIME.vibrator();
                            }

                            // enter
                            if (x >= (viewWidth / 1.144) - viewWidth / 20 && x < ((viewWidth / 1.144) + viewWidth / 20)
                                    && y >= (viewHeight / 1.445) - viewWidth / 20 && y < ((viewHeight / 1.445) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                OneHandIME.outputEnter();
                            }

                            // 뒤로가기
                            if (x >= (viewWidth / 1.049) - viewWidth / 20 && x < ((viewWidth / 1.049) + viewWidth / 20)
                                    && y >= (viewHeight / 1.64) - viewWidth / 20 && y < ((viewHeight / 1.64) + viewWidth / 20)) {
                                OneHandIME.delOneText();
                            }

                            // Space
                            if (x >= (viewWidth / 1.02) - viewWidth / 11.52 && x < ((viewWidth / 1.02) + viewWidth / 11.52)
                                    && y >= (viewHeight / 1.22) - viewWidth / 11.52 && y < ((viewHeight / 1.22) + viewWidth / 11.52)) {
                                OneHandIME.outputText(" "); // 공백 입력
                                OneHandIME.resetKeyboard();
                            }

                            // upSpace
                            if (x >= (viewWidth / 1.054) - viewWidth / 20 && x < ((viewWidth / 1.054) + viewWidth / 20)
                                    && y >= (viewHeight / 7.659) - viewWidth / 20 && y < ((viewHeight / 7.659) + viewWidth / 20)) {
                                com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);
                                Log.e("d", "d");
                                if (isUpSpace == false) {
                                    kb.setBackgroundResource(R.drawable.english_shift_right);
                                    isUpSpace = true;
                                } else {
                                    kb.setBackgroundResource(R.drawable.english_right);
                                    isUpSpace = false;
                                }

                                OneHandIME.vibrator();
                            }

                            if (x >= (viewWidth / 7.091) - viewWidth / 22 && x < ((viewWidth / 7.091) + viewWidth / 22)
                                    && y >= (viewHeight / 1.355) - viewWidth / 22 && y < ((viewHeight / 1.355) + viewWidth / 22)) {
                                side("left");
                                OneHandIME.left();

                                OneHandIME.resetKeyboard(); // 키보드 저장값 초기화
                                OneHandIME.oneHandIME_View.resetKeyboardLanguage(); // 키보드 언어를 한국어로 변경
                            }
                            break; // case EnglishKeyboard end
                        case 'N': // 숫자 & 특수문자 키패드
                            /** 1번째 줄 */
                            // 1
                            if (x >= (viewWidth / 3.239) - viewWidth / 20 && x < ((viewWidth / 3.239) + viewWidth / 20)
                                    && y >= (viewHeight / 1.312) - viewWidth / 20 && y < ((viewHeight / 1.312) + viewWidth / 20)) {
                                inputText('1');
                            }

                            // 2
                            else if (x >= (viewWidth / 2.643) - viewWidth / 20 && x < ((viewWidth / 2.643) + viewWidth / 20)
                                    && y >= (viewHeight / 1.444) - viewWidth / 20 && y < ((viewHeight / 1.444) + viewWidth / 20)) {
                                inputText('2');
                            }

                            // 3
                            else if (x >= (viewWidth / 2.217) - viewWidth / 20 && x < ((viewWidth / 2.217) + viewWidth / 20)
                                    && y >= (viewHeight / 1.614) - viewWidth / 20 && y < ((viewHeight / 1.614) + viewWidth / 20)) {
                                inputText('3');
                            }

                            // 4
                            else if (x >= (viewWidth / 1.909) - viewWidth / 20 && x < ((viewWidth / 1.909) + viewWidth / 20)
                                    && y >= (viewHeight / 1.829) - viewWidth / 20 && y < ((viewHeight / 1.829) + viewWidth / 20)) {
                                inputText('4');
                            }

                            // 5
                            else if (x >= (viewWidth / 1.678) - viewWidth / 20 && x < ((viewWidth / 1.678) + viewWidth / 20)
                                    && y >= (viewHeight / 2.109) - viewWidth / 20 && y < ((viewHeight / 2.109) + viewWidth / 20)) {
                                inputText('5');
                            }

                            // 6
                            else if (x >= (viewWidth / 1.495) - viewWidth / 20 && x < ((viewWidth / 1.495) + viewWidth / 20)
                                    && y >= (viewHeight / 2.492) - viewWidth / 20 && y < ((viewHeight / 2.492) + viewWidth / 20)) {
                                inputText('6');
                            }

                            // 7
                            else if (x >= (viewWidth / 1.353) - viewWidth / 20 && x < ((viewWidth / 1.353) + viewWidth / 20)
                                    && y >= (viewHeight / 3.036) - viewWidth / 20 && y < ((viewHeight / 3.036) + viewWidth / 20)) {
                                inputText('7');
                            }

                            // 8
                            else if (x >= (viewWidth / 1.229) - viewWidth / 20 && x < ((viewWidth / 1.229) + viewWidth / 20)
                                    && y >= (viewHeight / 3.911) - viewWidth / 20 && y < ((viewHeight / 3.911) + viewWidth / 20)) {
                                inputText('8');
                            }

                            // 9
                            else if (x >= (viewWidth / 1.128) - viewWidth / 20 && x < ((viewWidth / 1.128) + viewWidth / 20)
                                    && y >= (viewHeight / 5.498) - viewWidth / 20 && y < ((viewHeight / 5.498) + viewWidth / 20)) {
                                inputText('9');
                            }

                            // 0
                            else if (x >= (viewWidth / 1.043) - viewWidth / 20 && x < ((viewWidth / 1.043) + viewWidth / 20)
                                    && y >= (viewHeight / 9.080) - viewWidth / 20 && y < ((viewHeight / 9.0801) + viewWidth / 20)) {
                                inputText('0');
                            }

                            /** 2번째 줄 */
                            // @
                            if (x >= (viewWidth / 2.098) - viewWidth / 20 && x < ((viewWidth / 2.098) + viewWidth / 20)
                                    && y >= (viewHeight / 1.286) - viewWidth / 20 && y < ((viewHeight / 1.286) + viewWidth / 20)) {
                                inputText('@');
                            }

                            // #
                            else if (x >= (viewWidth / 1.874) - viewWidth / 20 && x < ((viewWidth / 1.874) + viewWidth / 20)
                                    && y >= (viewHeight / 1.390) - viewWidth / 20 && y < ((viewHeight / 1.390) + viewWidth / 20)) {
                                inputText('#');
                            }

                            // $
                            else if (x >= (viewWidth / 1.680) - viewWidth / 20 && x < ((viewWidth / 1.680) + viewWidth / 20)
                                    && y >= (viewHeight / 1.506) - viewWidth / 20 && y < ((viewHeight / 1.506) + viewWidth / 20)) {
                                inputText('$');
                            }

                            // /
                            else if (x >= (viewWidth / 1.534) - viewWidth / 20 && x < ((viewWidth / 1.534) + viewWidth / 20)
                                    && y >= (viewHeight / 1.651) - viewWidth / 20 && y < ((viewHeight / 1.651) + viewWidth / 20)) {
                                inputText('/');
                            }

                            // %
                            else if (x >= (viewWidth / 1.426) - viewWidth / 20 && x < ((viewWidth / 1.426) + viewWidth / 20)
                                    && y >= (viewHeight / 1.863) - viewWidth / 20 && y < ((viewHeight / 1.863) + viewWidth / 20)) {
                                inputText('%');
                            }

                            // -
                            else if (x >= (viewWidth / 1.306) - viewWidth / 20 && x < ((viewWidth / 1.306) + viewWidth / 20)
                                    && y >= (viewHeight / 2.050) - viewWidth / 20 && y < ((viewHeight / 2.050) + viewWidth / 20)) {
                                inputText('-');
                            }

                            // +
                            else if (x >= (viewWidth / 1.219) - viewWidth / 20 && x < ((viewWidth / 1.219) + viewWidth / 20)
                                    && y >= (viewHeight / 2.365) - viewWidth / 20 && y < ((viewHeight / 2.365) + viewWidth / 20)) {
                                inputText('+');
                            }

                            // (
                            else if (x >= (viewWidth / 1.137) - viewWidth / 20 && x < ((viewWidth / 1.137) + viewWidth / 20)
                                    && y >= (viewHeight / 2.694) - viewWidth / 20 && y < ((viewHeight / 2.694) + viewWidth / 20)) {
                                inputText('(');
                            }

                            // )
                            else if (x >= (viewWidth / 1.067) - viewWidth / 20 && x < ((viewWidth / 1.067) + viewWidth / 20)
                                    && y >= (viewHeight / 3.211) - viewWidth / 20 && y < ((viewHeight / 3.211) + viewWidth / 20)) {
                                inputText(')');
                            }

                            /** 3번째 줄 */
                            // *
                            if (x >= (viewWidth / 1.568) - viewWidth / 21 && x < ((viewWidth / 1.568) + viewWidth / 21)
                                    && y >= (viewHeight / 1.279) - viewWidth / 21 && y < ((viewHeight / 1.279) + viewWidth / 21)) {
                                inputText('*');
                            }

                            // "
                            else if (x >= (viewWidth / 1.448) - viewWidth / 21 && x < ((viewWidth / 1.448) + viewWidth / 21)
                                    && y >= (viewHeight / 1.377) - viewWidth / 21 && y < ((viewHeight / 1.377) + viewWidth / 21)) {
                                inputText('"');
                            }

                            // '
                            else if (x >= (viewWidth / 1.344) - viewWidth / 21 && x < ((viewWidth / 1.344) + viewWidth / 21)
                                    && y >= (viewHeight / 1.497) - viewWidth / 21 && y < ((viewHeight / 1.497) + viewWidth / 21)) {
                                inputText('.');
                            }

                            // :
                            else if (x >= (viewWidth / 1.257) - viewWidth / 21 && x < ((viewWidth / 1.257) + viewWidth / 21)
                                    && y >= (viewHeight / 1.629) - viewWidth / 21 && y < ((viewHeight / 1.629) + viewWidth / 21)) {
                                inputText(':');
                            }

                            // ;
                            else if (x >= (viewWidth / 1.176) - viewWidth / 21 && x < ((viewWidth / 1.176) + viewWidth / 21)
                                    && y >= (viewHeight / 1.798) - viewWidth / 21 && y < ((viewHeight / 1.798) + viewWidth / 21)) {
                                inputText(';');
                            }

                            // !
                            else if (x >= (viewWidth / 1.108) - viewWidth / 21 && x < ((viewWidth / 1.108) + viewWidth / 21)
                                    && y >= (viewHeight / 1.998) - viewWidth / 21 && y < ((viewHeight / 1.998) + viewWidth / 21)) {
                                inputText('!');
                            }

                            // ?
                            else if (x >= (viewWidth / 1.040) - viewWidth / 21 && x < ((viewWidth / 1.040) + viewWidth / 21)
                                    && y >= (viewHeight / 2.281) - viewWidth / 21 && y < ((viewHeight / 2.281) + viewWidth / 21)) {
                                inputText('?');
                            }

                            /** 4번째 줄 */
                            // 한글 - 영어 - 숫자
                            if (x >= (viewWidth / 1.256) - viewWidth / 20 && x < ((viewWidth / 1.256) + viewWidth / 20)
                                    && y >= (viewHeight / 1.300) - viewWidth / 20 && y < ((viewHeight / 1.300) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                changeKeyboard('K');

                                OneHandIME.vibrator();
                            }

                            // enter
                            if (x >= (viewWidth / 1.144) - viewWidth / 20 && x < ((viewWidth / 1.144) + viewWidth / 20)
                                    && y >= (viewHeight / 1.445) - viewWidth / 20 && y < ((viewHeight / 1.445) + viewWidth / 20)) {
                                OneHandIME.resetKeyboard();
                                OneHandIME.outputEnter();
                            }

                            // 뒤로가기
                            if (x >= (viewWidth / 1.049) - viewWidth / 20 && x < ((viewWidth / 1.049) + viewWidth / 20)
                                    && y >= (viewHeight / 1.64) - viewWidth / 20 && y < ((viewHeight / 1.64) + viewWidth / 20)) {
                                OneHandIME.delOneText();
                            }

                            // Space
                            if (x >= (viewWidth / 1.02) - viewWidth / 11.52 && x < ((viewWidth / 1.02) + viewWidth / 11.52)
                                    && y >= (viewHeight / 1.22) - viewWidth / 11.52 && y < ((viewHeight / 1.22) + viewWidth / 11.52)) {
                                OneHandIME.outputText(" "); // 공백 입력
                                OneHandIME.resetKeyboard();
                            }

                            if (x >= (viewWidth / 7.091) - viewWidth / 22 && x < ((viewWidth / 7.091) + viewWidth / 22)
                                    && y >= (viewHeight / 1.355) - viewWidth / 22 && y < ((viewHeight / 1.355) + viewWidth / 22)) {
                                side("left");
                                OneHandIME.left();

                                OneHandIME.resetKeyboard(); // 키보드 저장값 초기화
                                resetKeyboardLanguage(); // 키보드 언어를 한국어로 변경
                            }
                            break; // case number & special letters keyboard end
                    }
                }
                break; // case MotionEvent.ACTION_DOWN end
        } // switch end

        return false;
    }

    // 단어 입력
    public void inputText(char hangul) {
        OneHandIME.outputText(String.valueOf(hangul)); // 입력
        shiftReset(); // 아무거나 클릭하면 upSpace를 초기화 한다
    }

    // shift를 원래대로 바꾸기
    public void shiftReset() {
        com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);

        switch (keyboardType) {
            case 'K': // 한글 키보드일때
                if (isUpSpace == true) {
                    if (keyboardSide.equals("left")) {
                        kb.setBackgroundResource(R.drawable.hangul_left);
                    } else {
                        kb.setBackgroundResource(R.drawable.hangul_right);
                    }
                    isUpSpace = false;
                }
                break;
            case 'E': // 영어 키보드일때
                if (isUpSpace == true) {
                    if (keyboardSide.equals("left")) {
                        kb.setBackgroundResource(R.drawable.english_left);
                    } else {
                        kb.setBackgroundResource(R.drawable.english_right);
                    }
                    isUpSpace = false;
                }
                break;
            case 'N': // 숫자 & 특수문자 키보드일때
                // 아무것도 안함
                break;
        }
    }

    // 언어 변경
    public void changeKeyboard(char type) {
        com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);
        isUpSpace = false;
        switch (type) {
            case 'K':
                if (keyboardSide.equals("left")) {
                    kb.setBackgroundResource(R.drawable.hangul_left);
                } else {
                    kb.setBackgroundResource(R.drawable.hangul_right);
                }
                keyboardType = 'K';
                break;
            case 'E':
                if (keyboardSide.equals("left")) {
                    kb.setBackgroundResource(R.drawable.english_left);
                } else {
                    kb.setBackgroundResource(R.drawable.english_right);
                }
                keyboardType = 'E';
                break;
            case 'N':
                if (keyboardSide.equals("left")) {
                    kb.setBackgroundResource(R.drawable.number_left);
                } else {
                    kb.setBackgroundResource(R.drawable.number_right);
                }
                keyboardType = 'N';
                break;
        }
    }

    // 키보드 언어 초기화
    public void resetKeyboardLanguage() {
        com.jihong.onehandedkeyboard.OneHandIME_View kb = (com.jihong.onehandedkeyboard.OneHandIME_View) findViewById(R.id.keyboard);
        isUpSpace = false;
        if (keyboardSide.equals("left")) {
            kb.setBackgroundResource(R.drawable.hangul_left);
        } else {
            kb.setBackgroundResource(R.drawable.hangul_right);
        }
        keyboardType = 'K';
    }
}