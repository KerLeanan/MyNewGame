package ru.zayats.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.zayats.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private static final float V_LEN = 0.5f;

    private Texture img;
    private Vector2 pos;
    private Vector2 touch;
    private Vector2 v;

    private Vector2 tmp;


    @Override
    public void show() {
        super.show();
        img = new Texture("mini_1.jpg");
        pos = new Vector2(0, 0);
        touch = new Vector2();
        v = new Vector2();

        /**
         * создаем промежуточный вектор.
         * чтобы 60 раз в секунду не создавать новый вектор в render
         * (touch.cpy().sub(pos).len() <= v.len())
         */

        tmp = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y);

        //в tmp устанавливаем вектор touch, такии образом у нас всегда будет промежуточный вектор с координатами куда указал пользователь.
        tmp.set(touch);

        /**
         * для опеределения точки где нам надо остановиться
         * мы узнаем длину
         *
         * берем вектор tmp, вычитаем из него вектор позиции и получаем длину.
         * ПОЛУЧАЕТСЯ МЫ ВЫЧИТАЕМ К ПРИМЕРУ ИЗ (3,5) ВЕКТОР ПОЗИЦИИ (0,0) И ДЛИНА У НЕГО БУДЕТ 5.8309517.
         * ЭТА ДЛИНА ПОЛУЧАЕТСЯ ПРЯМАЯ ЛИНИЯ ОТ КООРДИНАТ (0,0) ДО КООРДИНАТ (3,5) ПРАВИЛЬНО?
         *
         * если оставшиеся длина меньше либо равна v.len();
         * ВЫ СКАЗАЛИ ОСТАВШИЕСЯ ДЛИНА, ЧТО ЗНАЧИТ ОСТАВЩИЕСЯ ДЛИНА?
         *
         * И ВОТ ТУТ Я НЕ ПОНИМАЮ ДАЛЬШЕ КАК ВСЕ ПРОИСХОДИТ.
         * В V МЫ УСТАНОВИЛИ v = touch.cpy().sub(pos).setLength(V_LEN);
         * КАКОЕ В ИТОГЕ У V БУДЕТ ЗНАЧЕНИЕ? 0.5f ? ИЛИ КАКОЕ?
         * ПОЛУЧАЕТСЯ МЫ СРАВНИВАЕМ 5.8309517 С 0.5 ?
         *
         * то в вектор позиции мы устанавливаем touch
         * Т.Е. МЫ ГОВОРИМ ИДИ ДО ЭТОЙ ТОЧКИ? ПРАВИЛЬНО?
         *
         * ИНАЧЕ МЫ СКЛАДЫВАЕМ ВЕКТОРА И ОБЪЕКТ КАЖДЫЙ РАЗ БУДЕТ ДВИГАТЬСЯ....
         * ВОТ ТУТ Я НЕ ПОНЯЛ ВООБЩЕ КАК ВСЕ ПРОИСХОДИТ.
         * ПОКА У НАС tmp.sub(pos).len() Н СТАНЕТ <= v.len() ?
         * вОТ МЫ ТАЧНУЛИ В ТОЧКУ И ЧТО ПРОИСХОДИТ? КАК ЭТА ELSE РАБОТАЕТ?
         * 60 РАЗ В СЕКУНДУ ОН БУДЕТ ДВИГАТЬСЯ? КАЖДЫЙ РАЗ ПОКА НЕ СТАНЕТ СООТВЕТСТВОВАТЬ УСЛОВИЮ?
         *
         * ВОТ ТУТ ОБЪЯСНИТЕ НА ПАЛЬЧАХ ПОЖАЛУЙСТА, ВООБЩЕ НЕ ПОНИМАЮ ЭТОГО.
         *
         */

        if (tmp.sub(pos).len() <= v.len()) {
            pos.set(touch);
        }
        else pos.add(v);
        batch.end();



    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v = touch.cpy().sub(pos).setLength(V_LEN);

        Vector2 v1 = new Vector2(3,5);
        Vector2 v2 = new Vector2(0,0);
        System.out.println("leeeeeeeeeeeeen: " + v1.sub(v2).len());


        return super.touchDown(screenX, screenY, pointer, button);


    }

    public void  Update (Vector2 vv){
        pos.set(vv);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Input.Keys.SPACE != keycode) {
            return super.keyDown(keycode);
        }
        if (v.isZero()) {
            v.set(1, 1);
        } else {
            v.setZero();
        }
        return super.keyDown(keycode);
    }
}
