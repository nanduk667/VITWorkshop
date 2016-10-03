package com.example.intodarkness.vitworkshop;

/**
 * Created by nandu on 05-10-2015.
 */
public class FormDatabase {
    String _rollNo;
    String _name;

    public FormDatabase() {

    }

    public FormDatabase(String _rollNo, String _name) {
        this._rollNo = _rollNo;
        this._name = _name;
    }

    public String get_rollNo() {
        return _rollNo;
    }

    public void set_rollNo(String _rollNo) {
        this._rollNo = _rollNo;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
