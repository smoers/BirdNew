package org.bird.gui.common;

import javafx.beans.property.*;

/**
 * Cette classe standardise l'objet passé au TableView
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */
public class ConverterTableViewColumnSix<T,U,V,W,X,Y> extends ConverterTableViewColumnThree<T,U,V> {

    protected SimpleObjectProperty<W> objectColumn04;
    protected SimpleObjectProperty<X> objectColumn05;
    protected SimpleObjectProperty<Y> objectColumn06;
    protected StringProperty stringColumn04;
    protected StringProperty stringColumn05;
    protected StringProperty stringColumn06;
    protected IntegerProperty integerColumn04;
    protected IntegerProperty integerColumn05;
    protected IntegerProperty integerColumn06;
    protected BooleanProperty booleanColumn04;
    protected BooleanProperty booleanColumn05;
    protected BooleanProperty booleanColumn06;

    /**
     * Object
     * @return
     */
    public SimpleObjectProperty<W> objectColumn04Property(){
        if (objectColumn04 == null) objectColumn04 = new SimpleObjectProperty<W>(this, "objectColumn04");
        return objectColumn04;
    }

    /**
     * Object
     * @return
     */
    public SimpleObjectProperty<X> objectColumn05Property(){
        if (objectColumn05 == null) objectColumn05 = new SimpleObjectProperty<X>(this, "objectColumn05");
        return objectColumn05;
    }

    /**
     * object
     * @return
     */
    public SimpleObjectProperty<Y> objectColumn06Property(){
        if (objectColumn06 == null) objectColumn06 = new SimpleObjectProperty<Y>(this, "objectColumn06");
        return objectColumn06;
    }

    /**
     * String
     * @return
     */
    public StringProperty stringColumn04Property(){
        if (stringColumn04 == null) stringColumn04 = new SimpleStringProperty(this,"stringColumn04");
        return stringColumn04;
    }

    /**
     * String
     * @return
     */
    public StringProperty stringColumn05Property(){
        if (stringColumn05 == null) stringColumn05 = new SimpleStringProperty(this,"stringColumn05");
        return stringColumn05;
    }

    /**
     * String
     * @return
     */
    public StringProperty stringColumn06Property(){
        if (stringColumn06 == null) stringColumn06 = new SimpleStringProperty(this,"stringColumn06");
        return stringColumn06;
    }

    /**
     * Integer
     * @return
     */
    public IntegerProperty integerColumn04Property(){
        if (integerColumn04 == null) integerColumn04 = new SimpleIntegerProperty(this,"integerColumn04");
        return integerColumn04;
    }

    /**
     * Integer
     * @return
     */
    public IntegerProperty integerColumn05Property(){
        if (integerColumn05 == null) integerColumn05 = new SimpleIntegerProperty(this,"integerColumn05");
        return integerColumn05;
    }

    /**
     * Integer
     * @return
     */
    public IntegerProperty integerColumn06Property(){
        if (integerColumn06 == null) integerColumn06 = new SimpleIntegerProperty(this,"integerColumn06");
        return integerColumn06;
    }

    /**
     * Boolean
     * @return
     */
    public BooleanProperty booleanColumn04Property(){
        if (booleanColumn04 == null) booleanColumn04 = new SimpleBooleanProperty(this,"booleanColumn04");
        return booleanColumn04;
    }

    /**
     * Boolean
     * @return
     */
    public BooleanProperty booleanColumn05Property(){
        if (booleanColumn05 == null) booleanColumn05 = new SimpleBooleanProperty(this,"booleanColumn05");
        return booleanColumn05;
    }

    /**
     * Boolean
     * @return
     */
    public BooleanProperty booleanColumn06Property(){
        if (booleanColumn06 == null) booleanColumn06 = new SimpleBooleanProperty(this,"booleanColumn06");
        return booleanColumn06;
    }

    /**
     *
     * @return
     */
    public W getObjectColumn04() {
        return objectColumn04Property().get();
    }

    public void setObjectColumn04(W objectColumn04) {
        objectColumn04Property().set(objectColumn04);
    }

    public X getObjectColumn05() {
        return objectColumn05Property().get();
    }

    public void setObjectColumn05(X objectColumn05) {
        this.objectColumn05Property().set(objectColumn05);
    }

    public Y getObjectColumn06() {
        return objectColumn06Property().get();
    }

    public void setObjectColumn06(Y objectColumn06) {
        this.objectColumn06Property().set(objectColumn06);
    }

    public String getStringColumn04() {
        return stringColumn04Property().get();
    }

    public void setStringColumn04(String stringColumn04) {
        this.stringColumn04Property().set(stringColumn04);
    }

    public String getStringColumn05() {
        return stringColumn05Property().get();
    }

    public void setStringColumn05(String stringColumn05) {
        this.stringColumn05Property().set(stringColumn05);
    }

    public String getStringColumn06() {
        return stringColumn06Property().get();
    }

    public void setStringColumn06(String stringColumn06) {
        this.stringColumn06Property().set(stringColumn06);
    }

    public int getIntegerColumn04() {
        return integerColumn04Property().get();
    }

    public void setIntegerColumn04(int integerColumn04) {
        this.integerColumn04Property().set(integerColumn04);
    }

    public int getIntegerColumn05() {
        return integerColumn05Property().get();
    }

    public void setIntegerColumn05(int integerColumn05) {
        this.integerColumn05Property().set(integerColumn05);
    }

    public int getIntegerColumn06() {
        return integerColumn06Property().get();
    }

    public void setIntegerColumn06(int integerColumn06) {
        this.integerColumn06Property().set(integerColumn06);
    }

    public boolean isBooleanColumn04() {
        return booleanColumn04Property().get();
    }

    public void setBooleanColumn04(boolean booleanColumn04) {
        this.booleanColumn04Property().set(booleanColumn04);
    }

    public boolean isBooleanColumn05() {
        return booleanColumn05Property().get();
    }

    public void setBooleanColumn05(boolean booleanColumn05) {
        this.booleanColumn05Property().set(booleanColumn05);
    }

    public boolean isBooleanColumn06() {
        return booleanColumn06Property().get();
    }

    public void setBooleanColumn06(boolean booleanColumn06) {
        this.booleanColumn06Property().set(booleanColumn06);
    }
}
