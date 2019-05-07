package org.bird.gui.common;

import javafx.beans.property.*;

/**
 * Cette classe standardise l'objet passé au TableView
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */
public class ConverterTableViewColumn<T,U,V> {

    private SimpleObjectProperty<T> objectColumn01;
    private SimpleObjectProperty<U> objectColumn02;
    private SimpleObjectProperty<V> objectColumn03;
    private StringProperty stringColumn01;
    private StringProperty stringColumn02;
    private StringProperty stringColumn03;
    private IntegerProperty integerColumn01;
    private IntegerProperty integerColumn02;
    private IntegerProperty integerColumn03;
    private BooleanProperty booleanColumn01;
    private BooleanProperty booleanColumn02;
    private BooleanProperty booleanColumn03;
    private Object source = null;

    public void setSource(Object source){
        this.source = source;
    }

    public <W> Object getSource(){
        return source;
    }

    /**
     * Object
     * @return
     */
    public SimpleObjectProperty<T> objectColumn01Property(){
        if (objectColumn01 == null) objectColumn01 = new SimpleObjectProperty<T>(this, "objectColumn01");
        return objectColumn01;
    }

    /**
     * Object
     * @return
     */
    public SimpleObjectProperty<U> objectColumn02Property(){
        if (objectColumn02 == null) objectColumn02 = new SimpleObjectProperty<U>(this, "objectColumn02");
        return objectColumn02;
    }

    /**
     * object
     * @return
     */
    public SimpleObjectProperty<V> objectColumn03Property(){
        if (objectColumn03 == null) objectColumn03 = new SimpleObjectProperty<V>(this, "objectColumn03");
        return objectColumn03;
    }

    /**
     * String
     * @return
     */
    public StringProperty stringColumn01Property(){
        if (stringColumn01 == null) stringColumn01 = new SimpleStringProperty(this,"stringColumn01");
        return stringColumn01;
    }

    /**
     * String
     * @return
     */
    public StringProperty stringColumn02Property(){
        if (stringColumn02 == null) stringColumn02 = new SimpleStringProperty(this,"stringColumn02");
        return stringColumn02;
    }

    /**
     * String
     * @return
     */
    public StringProperty stringColumn03Property(){
        if (stringColumn03 == null) stringColumn03 = new SimpleStringProperty(this,"stringColumn03");
        return stringColumn03;
    }

    /**
     * Integer
     * @return
     */
    public IntegerProperty integerColumn01Property(){
        if (integerColumn01 == null) integerColumn01 = new SimpleIntegerProperty(this,"integerColumn01");
        return integerColumn01;
    }

    /**
     * Integer
     * @return
     */
    public IntegerProperty integerColumn02Property(){
        if (integerColumn02 == null) integerColumn02 = new SimpleIntegerProperty(this,"integerColumn02");
        return integerColumn02;
    }

    /**
     * Integer
     * @return
     */
    public IntegerProperty integerColumn03Property(){
        if (integerColumn03 == null) integerColumn03 = new SimpleIntegerProperty(this,"integerColumn03");
        return integerColumn03;
    }

    /**
     * Boolean
     * @return
     */
    public BooleanProperty booleanColumn01Property(){
        if (booleanColumn01 == null) booleanColumn01 = new SimpleBooleanProperty(this,"booleanColumn01");
        return booleanColumn01;
    }

    /**
     * Boolean
     * @return
     */
    public BooleanProperty booleanColumn02Property(){
        if (booleanColumn02 == null) booleanColumn02 = new SimpleBooleanProperty(this,"booleanColumn02");
        return booleanColumn02;
    }

    /**
     * Boolean
     * @return
     */
    public BooleanProperty booleanColumn03Property(){
        if (booleanColumn03 == null) booleanColumn03 = new SimpleBooleanProperty(this,"booleanColumn03");
        return booleanColumn03;
    }

    /**
     *
     * @return
     */
    public T getObjectColumn01() {
        return objectColumn01Property().get();
    }

    public void setObjectColumn01(T objectColumn01) {
        objectColumn01Property().set(objectColumn01);
    }

    public U getObjectColumn02() {
        return objectColumn02Property().get();
    }

    public void setObjectColumn02(U objectColumn02) {
        this.objectColumn02Property().set(objectColumn02);
    }

    public V getObjectColumn03() {
        return objectColumn03Property().get();
    }

    public void setObjectColumn03(V objectColumn03) {
        this.objectColumn03Property().set(objectColumn03);
    }

    public String getStringColumn01() {
        return stringColumn01Property().get();
    }

    public void setStringColumn01(String stringColumn01) {
        this.stringColumn01Property().set(stringColumn01);
    }

    public String getStringColumn02() {
        return stringColumn02Property().get();
    }

    public void setStringColumn02(String stringColumn02) {
        this.stringColumn02Property().set(stringColumn02);
    }

    public String getStringColumn03() {
        return stringColumn03Property().get();
    }

    public void setStringColumn03(String stringColumn03) {
        this.stringColumn03Property().set(stringColumn03);
    }

    public int getIntegerColumn01() {
        return integerColumn01Property().get();
    }

    public void setIntegerColumn01(int integerColumn01) {
        this.integerColumn01Property().set(integerColumn01);
    }

    public int getIntegerColumn02() {
        return integerColumn02Property().get();
    }

    public void setIntegerColumn02(int integerColumn02) {
        this.integerColumn02Property().set(integerColumn02);
    }

    public int getIntegerColumn03() {
        return integerColumn03Property().get();
    }

    public void setIntegerColumn03(int integerColumn03) {
        this.integerColumn03Property().set(integerColumn03);
    }

    public boolean isBooleanColumn01() {
        return booleanColumn01Property().get();
    }

    public void setBooleanColumn01(boolean booleanColumn01) {
        this.booleanColumn01Property().set(booleanColumn01);
    }

    public boolean isBooleanColumn02() {
        return booleanColumn02Property().get();
    }

    public void setBooleanColumn02(boolean booleanColumn02) {
        this.booleanColumn02Property().set(booleanColumn02);
    }

    public boolean isBooleanColumn03() {
        return booleanColumn03Property().get();
    }

    public void setBooleanColumn03(boolean booleanColumn03) {
        this.booleanColumn03Property().set(booleanColumn03);
    }
}
