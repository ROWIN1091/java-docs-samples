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

package v2.findings;

// [START securitycenter_list_filtered_findings_v2]

import com.google.cloud.securitycenter.v2.ListFindingsRequest;
import com.google.cloud.securitycenter.v2.ListFindingsResponse.ListFindingsResult;
import com.google.cloud.securitycenter.v2.SecurityCenterClient;
import java.io.IOException;

public class ListFindingsWithFilter {

  public static void main(String[] args) throws IOException {
    // TODO: Replace the variables within {}
    // projectId: Google Cloud Project id.
    // You can also use organization/ folder as the parent resource.
    String projectId = "google-cloud-project-id";

    // Specify the DRZ location to list the findings.
    // Available locations: "us", "eu", "global".
    String location = "global";

    // The source id to scope the findings.
    String sourceId = "source-id";

    listFilteredFindings(projectId, sourceId, location);
  }

  // List filtered findings under a source.
  public static void listFilteredFindings(String projectId, String sourceId, String location)
      throws IOException {
    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests.
    try (SecurityCenterClient client = SecurityCenterClient.create()) {

      // Use any one of the following formats:
      //  * organizations/{organization_id}/sources/{source_id}/locations/{location}
      //  * folders/{folder_id}/sources/{source_id}/locations/{location}
      //  * projects/{project_id}/sources/{source_id}/locations/{location}
      String parent = String.format("projects/%s/sources/%s/locations/%s", projectId, sourceId,
          location);

      // Listing all findings of category "MEDIUM_RISK_ONE".
      String filter = "category=\"MEDIUM_RISK_ONE\"";

      ListFindingsRequest request =
          ListFindingsRequest.newBuilder()
              .setParent(parent)
              .setFilter(filter)
              .build();

      for (ListFindingsResult result : client.listFindings(request).iterateAll()) {
        System.out.printf("Finding: %s", result.getFinding().getName());
      }
      System.out.println("Listing complete.");
    }
  }
}
// [END securitycenter_list_filtered_findings_v2]