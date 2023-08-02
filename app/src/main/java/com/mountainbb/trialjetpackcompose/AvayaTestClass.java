package com.mountainbb.trialjetpackcompose;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.avaya.callprovider.cp.handlers.CPAudioDeviceType;
import com.avaya.callprovider.cp.handlers.OnAudioDeviceChangeListener;
import com.avaya.callprovider.enums.CPAudioDeviceError;
import com.avaya.ocs.Config.ClientConfiguration;
import com.avaya.ocs.Config.Config;
import com.avaya.ocs.Config.WebGatewayConfiguration;
import com.avaya.ocs.OceanaCustomerWebVoiceVideo;
import com.avaya.ocs.Services.Work.Interactions.AudioInteraction;
import com.avaya.ocs.Services.Work.Schema.Attributes;
import com.avaya.ocs.Services.Work.Schema.Resource;
import com.avaya.ocs.Services.Work.Schema.Service;
import com.avaya.ocs.Services.Work.Work;

import java.util.ArrayList;
import java.util.List;

public class AvayaTestClass extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        Config config = new Config("amc-cluster.avaya.com");
        config.setRestUrl("amc-cluster.avaya.com");
        config.setSecure(true);
        config.setPort(443);

        WebGatewayConfiguration webGatewayConfiguration = new WebGatewayConfiguration();
        webGatewayConfiguration.setWebGatewayAddress("aawg-fqdn");
        webGatewayConfiguration.setPort(8443);
        webGatewayConfiguration.setSecure(true);

        clientConfiguration.setWebGatewayConfiguration(webGatewayConfiguration);
        clientConfiguration.setConfig(config);

        OceanaCustomerWebVoiceVideo  client = new OceanaCustomerWebVoiceVideo(clientConfiguration);
        Work work = client.createWork();

        List<Service> services = new ArrayList<>();
        Service service = new Service();

        List<String> tesList = new ArrayList<>();

        Attributes testing = new Attributes();
        testing.add("newAttribute", tesList);

        service.setAttributes(testing);
        service.setPriority(5);
        services.add(service);
        work.setServices(services);

        List<Resource> resources = new ArrayList<>();
        Resource resource = new Resource();
        resource.setNativeResourceId("agent6220");
        resource.setSourceName("CM");
        resources.add(resource);
        work.setResources((List<Resource>) resource);

        work.setContext("Creating an Application: Generic Elements - Context");

        OnAudioDeviceChangeListener testAudio = new OnAudioDeviceChangeListener() {
            @Override
            public void onAudioDeviceListChanged(List<CPAudioDeviceType> list) {

            }

            @Override
            public void onAudioDeviceChanged(CPAudioDeviceType cpAudioDeviceType) {

            }

            @Override
            public void onAudioDeviceError(CPAudioDeviceError cpAudioDeviceError) {

            }
        };
        
    }
}
