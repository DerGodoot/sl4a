/*
 * Copyright (C) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.ase.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.ase.AseApplication;
import com.google.ase.trigger.ConditionTrigger;
import com.google.ase.trigger.Trigger;
import com.google.ase.trigger.TriggerRepository;

public class TriggerService extends Service {
  private final TriggerRepository mTriggerRepository;
  
  public TriggerService() {
    AseApplication application = (AseApplication) this.getApplication();
    mTriggerRepository = application.getTriggerRepository();
    initializeTriggers();
  }

  private void initializeTriggers() {
    for (Trigger trigger : mTriggerRepository.getAllTriggers()) {
      if (trigger instanceof ConditionTrigger) {
        trigger.install();
      }
    }
  }

  @Override
  public void onStart(Intent intent, int startId) {
    super.onStart(intent, startId);
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}