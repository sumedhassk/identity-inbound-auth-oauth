/*
 * Copyright (c) 2013, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.oauth.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.oauth.event.OAuthEventListener;
import org.wso2.carbon.registry.api.RegistryService;
import org.wso2.carbon.user.core.service.RealmService;

import java.util.ArrayList;
import java.util.List;

public class OAuthComponentServiceHolder {

    private static OAuthComponentServiceHolder instance = new OAuthComponentServiceHolder();
    private RegistryService registryService;
    private RealmService realmService;
    private List<OAuthEventListener> oAuthEventListeners;
    private static Log log = LogFactory.getLog(OAuthComponentServiceHolder.class);

    private OAuthComponentServiceHolder() {

    }

    public static OAuthComponentServiceHolder getInstance() {

        return instance;
    }

    public RegistryService getRegistryService() {

        return registryService;
    }

    public void setRegistryService(RegistryService registryService) {

        this.registryService = registryService;
    }

    public RealmService getRealmService() {

        return realmService;
    }

    public void setRealmService(RealmService realmService) {

        this.realmService = realmService;
    }

    public void addOauthEventListener(OAuthEventListener oAuthEventListener) {

        if (oAuthEventListeners == null) {
            oAuthEventListeners = new ArrayList<>();
        }
        oAuthEventListeners.add(oAuthEventListener);
    }

    public void removeOauthEventListener(OAuthEventListener OAuthEventListener) {

        if (oAuthEventListeners != null && OAuthEventListener != null) {
            boolean isRemoved = oAuthEventListeners.remove(OAuthEventListener);
            if (!isRemoved) {
                log.warn(OAuthEventListener.getClass().getName() + " had not been registered as a listener");
            }
        }
    }

    public List<OAuthEventListener> getoAuthEventListeners() {

        if (oAuthEventListeners == null) {
            oAuthEventListeners = new ArrayList<>();
        }
        return oAuthEventListeners;
    }
}
