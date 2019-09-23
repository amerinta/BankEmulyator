package com.company;

/**
 * Created by student on 08.05.2019.
 */
public abstract interface AbstractCommand {

    /**
     * Выполняет команду, инкапсулированную в этом объекте.
     * @return Возвращает true при успешном ыполнении
     */

    public abstract boolean execute();

}
