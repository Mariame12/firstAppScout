/*
 * Copyright (c) 2021 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 */
package org.eclipse.scout.apps.contactscout.server.sql;

//tag::createDB[]
//tag::organizationListing[]
public interface SQLs {
  //end::organizationListing[]

  String SELECT_TABLE_NAMES = ""
    + "SELECT   UPPER(tablename) "
    + "FROM     sys.systables "
    + "INTO     :result"; // <1>

  String ORGANIZATION_CREATE_TABLE = ""
    + "CREATE   TABLE ORGANIZATION "
    + "         (organization_id VARCHAR(64) NOT NULL CONSTRAINT ORGANIZATION_PK PRIMARY KEY,"
    + "          name VARCHAR(64), "
    + "          logo_url VARCHAR(512), "
    + "          street VARCHAR(64), "
    + "          city VARCHAR(64), "
    + "          country VARCHAR(2), "
    + "          phone VARCHAR(20), "
    + "          email VARCHAR(64), "
    + "          notes VARCHAR(1024)"
    + "         )";

  String PERSON_CREATE_TABLE = ""
    + "CREATE   TABLE PERSON "
    + "         (person_id VARCHAR(64) NOT NULL CONSTRAINT PERSON_PK PRIMARY KEY, "
    + "          first_name VARCHAR(64), "
    + "          last_name VARCHAR(64), "
    + "          date_of_birth DATE, "
    + "          gender VARCHAR(1), "
    + "          relationType VARCHAR(64), "
    + "          street VARCHAR(64), "
    + "          city VARCHAR(64), "
    + "          country VARCHAR(2), "
    + "          phone VARCHAR(20), "
    + "          mobile VARCHAR(20), "
    + "          email VARCHAR(64), "
    + "          parent_id VARCHAR(64), "
    + "          notes VARCHAR(1024) "
    + "         )";
  // end::createDB[]

  String WORK_PERSON_CREATE_TABLE =""
    + "CREATE   TABLE WORK_PERSON "
    + "         (work_id VARCHAR(64) NOT NULL CONSTRAINT WORK_PK PRIMARY KEY,"
    + "         position VARCHAR(160), "
    + "         organization_id VARCHAR(64), "
    + "         phone_work VARCHAR(20), "
    + "         email_work VARCHAR(64), "
    + "         person_id VARCHAR(64), "
    + "          CONSTRAINT PERSON_FK FOREIGN KEY (person_id) REFERENCES PERSON (person_id)"
    + "         )";


  String PERSON_LOOKUP = ""
    + "SELECT   person_id, "
    + "         CASE "
    + "           WHEN first_name IS null "
    + "            THEN last_name "
    + "           WHEN last_name IS null "
    + "            THEN first_name "
    + "           ELSE "
    + "            first_name || ' ' || last_name "
    + "         END "
    + "FROM     PERSON "
    + "WHERE    1 = 1 "
    + "<key>    AND person_id = :key</key> "
    + "<text>   AND (UPPER(first_name) LIKE UPPER('%'||:text||'%') "
    + "         OR UPPER(last_name) LIKE UPPER('%'||:text||'%')) "
    + "</text>"
    + "<all> </all>";

  //tag::lookupService[]
  String ORGANIZATION_LOOKUP = ""
    + "SELECT   organization_id, "
    + "         name "
    + "FROM     ORGANIZATION "
    + "WHERE    1 = 1 "
    + "<key>    AND organization_id = :key</key> " // <1>
    + "<text>   AND UPPER(name) LIKE UPPER(:text||'%') </text> " // <2>
    + "<all></all>"; // <3>
  //end::lookupService[]

  String AND_LIKE_CAUSE = "AND LOWER(%s) LIKE LOWER(:%s || '%%') ";

  //tag::organizationListing[]
  String ORGANIZATION_PAGE_SELECT = ""
    + "SELECT   organization_id, "
    + "         name, "
    + "         city, "
    + "         country "
    + "FROM     ORGANIZATION ";

  String ORGANIZATION_PAGE_DATA_SELECT_INTO = ""
    + "INTO     :{page.organizationId}, " // <1>
    + "         :{page.name}, "
    + "         :{page.city}, "
    + "         :{page.country} ";
  //end::organizationListing[]

  String ORGANIZATION_INSERT = ""
    + "INSERT   INTO "
    + "ORGANIZATION  (organization_id) "
    + "VALUES   (:organizationId)";

  String ORGANIZATION_SELECT = ""
    + "SELECT   name, "
    + "         logo_url, "
    + "         phone, "
    + "         email, "
    + "         street, "
    + "         city, "
    + "         country, "
    + "         notes "
    + "FROM     ORGANIZATION "
    + "WHERE    organization_id = :organizationId "
    + "INTO     :name, "
    + "         :picture.url, "
    + "         :phone, "
    + "         :email, "
    + "         :addressBox.street, "
    + "         :addressBox.city, "
    + "         :addressBox.country, "
    + "         :notesBox.notes";

  String ORGANIZATION_UPDATE = ""
    + "UPDATE   ORGANIZATION "
    + "SET      name = :name, "
    + "         logo_url = :picture.url, "
    + "         phone = :phone, "
    + "         email = :email, "
    + "         street = :addressBox.street, "
    + "         city = :addressBox.city, "
    + "         country = :addressBox.country, "
    + "         notes = :notesBox.notes "
    + "WHERE    organization_id = :organizationId";

  String PERSON_PAGE_SELECT = ""
    + "SELECT   p.person_id, "
    + "         p.first_name, "
    + "         p.last_name, "
    + "         p.city, "
    + "         p.country, "
    + "         p.phone, "
    + "         p.mobile, "
    + "         p.email "
    + "FROM     PERSON p ";

  String PERSON_PAGE_DATA_SELECT_INTO = ""
    + "INTO     :{page.personId}, "
    + "         :{page.firstName}, "
    + "         :{page.lastName}, "
    + "         :{page.city}, "
    + "         :{page.country}, "
    + "         :{page.phone}, "
    + "         :{page.mobile}, "
    + "         :{page.email} ";


  String PERSON_INSERT = ""
    + "INSERT   INTO "
    + "PERSON  (person_id) "
    + "VALUES   (:personId)";

  String PERSON_SELECT = ""
    + "SELECT   first_name, "
    + "         last_name, "
    + "         date_of_birth, "
    + "         gender, "
    + "         relationType,  "
    + "         phone, "
    + "         mobile, "
    + "         email, "
    + "         street, "
    + "         city, "
    + "         country, "
    + "         parent_id,  "
    + "         notes "
    + "FROM     PERSON "
    + "WHERE    person_id = :personId "
    + "INTO     :firstName, "
    + "         :lastName, "
    + "         :dateOfBirth, "
    + "         :genderGroup, "
    + "         :relationType, "
    + "         :phone, "
    + "         :mobile, "
    + "         :email, "
    + "         :addressBox.street, "
    + "         :addressBox.city, "
    + "         :addressBox.country, "
    + "         :parentId, "
    + "         :notesBox.notes";

  String PERSON_UPDATE = ""
    + "UPDATE   PERSON "
    + "SET      first_name = :firstName, "
    + "         last_name = :lastName, "
    + "         date_of_birth = :dateOfBirth, "
    + "         gender = :genderGroup, "
    + "         relationType = :relationType,  "
    + "         phone  = :phone, "
    + "         mobile = :mobile, "
    + "         email = :email, "
    + "         street = :addressBox.street, "
    + "         city = :addressBox.city, "
    + "         country = :addressBox.country, "
    + "         parent_id = :parentId,  "
    + "         notes = :notesBox.notes "
    + "WHERE    person_id = :personId";

  String PERSON_SELECT_CHILDREN_DATA =""
    + "SELECT   first_name, "
    + "         last_name, "
    + "         date_of_birth, "
    + "         gender, "
    + "         phone "
    + "FROM     PERSON "
    + "WHERE    parent_id = :personId "
    + "AND      relationType = :type "
    + "INTO     :firstName, "
    + "         :lastName, "
    + "         :dateOfBirth, "
    + "         :genderGroup, "
    + "         :phone ";
  String PERSON_SELECT_WORK_ID=""
    + "SELECT work_id "
    + " FROM  WORK_PERSON "
    + "WHERE  person_id = :personId "
    + "INTO   :workId";

  String PERSON_SELECT_FULL = ""
  + "SELECT person_id, first_name, last_name"
  +" FROM PERSON";

  String WORK_PERSON_PAGE_SELECT = ""
          + "SELECT   wp.work_id, "
          + "         wp.position, "
          + "         wp.organization_id, "
          + "         wp.phone_work ,"
          + "         wp.email_work,"
          + "         wp.person_id"
          + "FROM     wp.WORK_PERSON ";

  String WORK_PERSON_PAGE_DATA_SELECT_INTO = ""
          + "INTO     :{page.workId}, " // <1>
          + "         :{page.position}, "
          + "         :{page.organisation}, "
          + "         :{page.phoneWork} ,"
          + "         :{page.emailWork} ,"
          + "         :{page.person} ";

  String WORK_PERSON_INSERT ="INSERT   INTO "
          + " WORK_PERSON   (work_id, position, organization_id, phone_work, email_work, person_id) "
          + " VALUES   (:workId, :position, :organisation, :phoneWork, :emailWork, :personId)";

  String WORK_PERSON_SELECT = ""
          + "SELECT   position, "
          + "         organization_id, "
          + "         phone_work, "
          + "         email_work, "
          + "         person_id "
          + "FROM   WORK_PERSON "
          + "WHERE    work_id = :workId "
          + "INTO     :position, "
          + "         :organisation, "
          + "         :phoneWork, "
          + "         :emailWork ,"
          + "         :personId ";

  String WORK_PERSON_UPDATE = ""
          + "UPDATE   WORK_PERSON "
          + "SET      position = :position, "
          + "         organization_id = :organisation, "
          + "         phone_work = :phoneWork, "
          + "         email_work = :emailWork, "
          + "         person_id = :personId "
          + "WHERE    work_id = :workId";

  String WORK_AND_PERSON_PAGE_SELECT = ""
          +"SELECT   p.first_name, "
          + "        p.last_name, "
          + "        p.date_of_birth, "
          + "        p.gender, "
          + "        p.relationType, "
          + "        p.phone, "
          + "        p.mobile, "
          + "        p.email, "
          + "        p.street, "
          + "        p.city, "
          + "        p.country, "
          + "        wp.position, "
          + "        wp.organization_id, "
          + "        wp.phone_work, "
          + "        wp.email_work, "
          + "        wp.work_id "
          + "FROM     PERSON p "
          + "LEFT JOIN WORK_PERSON wp "
          +"ON  p.person_id=wp.person_id "
          + "WHERE p.person_id = :personId "
          + "INTO     :firstName, "
          + "         :lastName, "
          + "         :dateOfBirth, "
          + "         :genderGroup, "
          + "         :relationType, "
          + "         :phone, "
          + "         :mobile, "
          + "         :email, "
          + "         :addressBox.street, "
          + "         :addressBox.city, "
          + "         :addressBox.country, "
          + "         :position, "
          + "         :organisation, "
          + "         :phoneWork, "
          + "         :emailWork,  "
          +"           :workId";
  String ORGANIZATION_INSERT_SAMPLE = ""
    + "INSERT   INTO ORGANIZATION "
    + "        (organization_id, "
    + "         name, "
    + "         city, "
    + "         country, "
    + "         logo_url) ";

  String ORGANIZATION_VALUES_01 = ""
    + "VALUES  ('org01', "
    + "         'Eclipse Foundation', "
    + "         'Brussels', "
    + "         'BE', "
    + "         'https://wiki.eclipse.org/images/a/ab/Eclipse_foundation_logo.png')";

  String ORGANIZATION_VALUES_02 = ""
    + "VALUES  ('org02', "
    + "         'BSI Business Systems Integration AG', "
    + "         'Baden', "
    + "         'CH', "
    + "         'https://wiki.eclipse.org/images/4/4f/Bsiag.png')";

  String PERSON_INSERT_SAMPLE = ""
    + "INSERT   INTO PERSON "
    + "         (person_id, "
    + "          first_name, "
    + "          last_name, "
    + "          date_of_birth, "
    + "          gender, "
    + "          street, "
    + "          city, "
    + "          country )";

  String PERSON_VALUES_01 = ""
    + "VALUES   ('prs05', "
    + "          'Kenneth', "
    + "          'Hartley', "
    + "          '06.03.1977', "
    + "          'M') ";


  String PERSON_VALUES_02 = ""
    + "VALUES   ('prs02', "
    + "          'Thea', "
    + "          'Ommundsen', "
    + "          '17.12.1999', "
    + "          'F')";


  String PERSON_VALUES_03 = ""
    + "VALUES   ('prs01', "
    + "          'Julek', "
    + "          'Ostrowski', "
    + "          '05.11.1954', "
    + "          'M') ";

  String PERSON_VALUES_04 = ""
    + "VALUES   ('prs04', "
    + "          'Joséphine', "
    + "          'Doiron', "
    + "          '18.08.1990', "
    + "          'F') ";


  String PERSON_VALUES_05 = ""
    + "VALUES   ('prs03', "
    + "          'Bagi', "
    + "          'Gergely', "
    + "          '21.03.1972', "
    + "          'M')";


  String PERSON_DELETE_TABLE="DELETE FROM PERSON WHERE person_id = :personId";
  String PERSON_DROP_TABLE = "DROP TABLE PERSON";
  String ORGANIZATION_DROP_TABLE = "DROP TABLE ORGANIZATION";
  String WORK_PERSON_DROP_TABLE ="DROP TABLE WORK_PERSON";
  String WORK_PERSON_DELETE_TABLE_FROM_ORGANISATION=" DELETE FROM WORK_PERSON WHERE  organization_id= :organizationId ";
  String WORK_PERSON_DELETE_TABLE_FROM_PERSON=" DELETE FROM WORK_PERSON WHERE  person_id= :personId ";
  String WORK_PERSON_DELETE_TABLE=" DELETE FROM WORK_PERSON WHERE  work_id= :workId ";
  String ORGANISATION_DELETE_TABLE = "DELETE FROM ORGANIZATION WHERE organization_id = :organizationId ";


  // tag::organizationListing[]
  // tag::createDB[]

}
// end::createDB[]
// end::organizationListing[]
