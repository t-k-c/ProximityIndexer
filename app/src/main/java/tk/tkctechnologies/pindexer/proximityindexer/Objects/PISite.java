package tk.tkctechnologies.pindexer.proximityindexer.Objects;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by codename-tkc on 16/01/2018.
 */

public class PISite {
    public long site_id, user_id;
    public int site_workers_number, site_visibility;//site_verified;
    public Date site_created_at;//site_last_paid
    public String site_name, site_logo, site_description_en, site_description_fr;
    public JSONObject site_contact, site_working_period;
    public float site_longitude, site_latitude;

    public PISite(long site_id, long user_id, int site_workers_number, int site_visibility, Date site_created_at,
                  String site_name, String site_logo, String site_description_en, String site_description_fr,
                  JSONObject site_contact, JSONObject site_working_period, float site_latitude, float site_longitude) {
        this.site_id = site_id;
        this.user_id = user_id;
        this.site_workers_number = site_workers_number;
        this.site_visibility = site_visibility;
        this.site_created_at = site_created_at;
        this.site_name = site_name;
        this.site_logo = site_logo;
        this.site_description_en = site_description_en;
        this.site_description_fr = site_description_fr;
        this.site_contact = site_contact;
        this.site_working_period = site_working_period;
        this.site_latitude = site_latitude;
        this.site_longitude = site_longitude;
    }

}
