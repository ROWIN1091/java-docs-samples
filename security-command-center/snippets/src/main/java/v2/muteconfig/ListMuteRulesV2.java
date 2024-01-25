/*
 * Copyright 2024 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package v2.muteconfig;

// [START securitycenter_list_mute_configs_v2]

import com.google.cloud.securitycenter.v2.ListMuteConfigsRequest;
import com.google.cloud.securitycenter.v2.MuteConfig;
import com.google.cloud.securitycenter.v2.SecurityCenterClient;
import java.io.IOException;

public class ListMuteRulesV2 {

  public static void main(String[] args) {
    // TODO: Replace variables enclosed within {}
    // projectId: Google Cloud Project id.
    String projectId = "google-cloud-project-id";

    // Specify the DRZ location to list mute configs.
    // Available locations: "us", "eu", "global".
    String location = "global";

    listMuteRules(projectId, location);
  }

  // Lists all mute rules present under the resource type in the given location.
  public static void listMuteRules(String projectId, String location) {
    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests.
    try (SecurityCenterClient client = SecurityCenterClient.create()) {

      // Parent can also be one of:
      //  * "organization/{org_id}/locations/{location}"
      //  * "folders/{folder_id}/locations/{location}"
      ListMuteConfigsRequest listMuteConfigsRequest = ListMuteConfigsRequest.newBuilder()
          .setParent(String.format("projects/%s/locations/%s", projectId, location))
          .build();

      // List all mute configs present in the resource.
      for (MuteConfig muteConfig : client.listMuteConfigs(listMuteConfigsRequest).iterateAll()) {
        System.out.println(muteConfig.getName());
      }
    } catch (IOException e) {
      System.out.println("Listing Mute rule failed! \n Exception: " + e);
    }
  }
}
// [END securitycenter_list_mute_configs_v2]
