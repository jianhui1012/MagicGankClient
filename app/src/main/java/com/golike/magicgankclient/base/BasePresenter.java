package com.golike.magicgankclient.base;

/**
 * 作者：created by djh on 2018/4/24 16 54.
 * 邮箱：golikeholdon@gmail.com
 */

interface BasePresenter<T> {

    void attach(T view);

    void detachView();
}
