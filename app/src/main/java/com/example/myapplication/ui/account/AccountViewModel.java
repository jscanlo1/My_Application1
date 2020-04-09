package com.example.myapplication.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> ohText;
    private MutableLiveData<String> apcText;
    private MutableLiveData<String> pmText;
    private MutableLiveData<String> msText;
    private MutableLiveData<String> sasText;
    private MutableLiveData<String> faqText;



    public AccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Hi, Bob");
        ohText = new MutableLiveData<>();
        ohText.setValue("Order History");
        apcText = new MutableLiveData<>();
        apcText.setValue("Add a Promo Code");
        pmText = new MutableLiveData<>();
        pmText.setValue("Payment Methods");
        msText = new MutableLiveData<>();
        msText.setValue("Message Support");
        sasText = new MutableLiveData<>();
        sasText.setValue("Suggest a Store");
        faqText = new MutableLiveData<>();
        faqText.setValue("FAQ");

    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getText1() {
        return ohText;
    }
    public LiveData<String> getText2() {
        return apcText;
    }
    public LiveData<String> getText3() {
        return pmText;
    }
    public LiveData<String> getText4() {
        return msText;
    }
    public LiveData<String> getText5() {
        return sasText;
    }
    public LiveData<String> getText6() {
        return faqText;
    }

}