package io.github.devandref.ms_sync.listeners;

public interface ListenerConfig {
    void listenToCustomerQueue(String message);
    void listenToAppointmentQueue(String message);
    void listenToBeautyProcedureQueue(String message);
}
