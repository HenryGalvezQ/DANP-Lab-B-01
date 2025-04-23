// SharedViewModel.java - SIN CAMBIOS
package com.example.jetpackcompose;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> firstName = new MutableLiveData<>(""); // Inicializar con "" puede ser útil
    private final MutableLiveData<String> lastName = new MutableLiveData<>("");
    private final MutableLiveData<String> dni = new MutableLiveData<>("");
    private final MutableLiveData<String> phone = new MutableLiveData<>("");
    private final MutableLiveData<String> email = new MutableLiveData<>("");
    private final MutableLiveData<String> password = new MutableLiveData<>("");

    public void setFirstName(String v) { firstName.setValue(v); }
    public void setLastName(String v) { lastName.setValue(v); }
    public void setDni(String v) { dni.setValue(v); }
    public void setPhone(String v) { phone.setValue(v); }
    public void setEmail(String v) { email.setValue(v); }
    public void setPassword(String v) { password.setValue(v); }

    public LiveData<String> getFirstName() { return firstName; }
    public LiveData<String> getLastName() { return lastName; }
    public LiveData<String> getDni() { return dni; }
    public LiveData<String> getPhone() { return phone; }
    public LiveData<String> getEmail() { return email; }
    public LiveData<String> getPassword() { return password; }
}