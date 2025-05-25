package org.eee.model.schema;

import stark.coderaider.fluentschema.commons.schemas.SchemaSnapshotBase;
import java.util.List;

public class SchemaSnapshot extends SchemaSnapshotBase {
    @Override
    public void buildSchema() {
        schemaBuilder.table("blog", builder -> {
            builder.column().name("name_in_oss").type("VARCHAR(100)").nullable(true).unique(false);
            builder.column().name("title").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("creator_id").type("BIGINT").nullable(false).unique(false);
            builder.column().name("creation_time").type("DATETIME").nullable(true).unique(false).defaultValue("NOW()");
            builder.column().name("modifier_id").type("BIGINT").nullable(false).unique(false);
            builder.column().name("modification_time").type("DATETIME").nullable(true).unique(false)
                    .defaultValue("NOW()").onUpdate("NOW()");
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        schemaBuilder.table("comment", builder -> {
            builder.column().name("blog_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("content").type("VARCHAR(250)").nullable(true).unique(false);
            builder.column().name("state").type("INT").nullable(true).unique(false);
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("creator_id").type("BIGINT").nullable(false).unique(false);
            builder.column().name("creation_time").type("DATETIME").nullable(true).unique(false).defaultValue("NOW()");
            builder.column().name("modifier_id").type("BIGINT").nullable(false).unique(false);
            builder.column().name("modification_time").type("DATETIME").nullable(true).unique(false)
                    .defaultValue("NOW()").onUpdate("NOW()");
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        schemaBuilder.table("like", builder -> {
            builder.column().name("blog_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("creator_id").type("BIGINT").nullable(false).unique(false);
            builder.column().name("creation_time").type("DATETIME").nullable(true).unique(false).defaultValue("NOW()");
            builder.column().name("modifier_id").type("BIGINT").nullable(false).unique(false);
            builder.column().name("modification_time").type("DATETIME").nullable(true).unique(false)
                    .defaultValue("NOW()").onUpdate("NOW()");
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        schemaBuilder.table("role", builder -> {
            builder.column().name("role_name").type("VARCHAR(20)").nullable(true).unique(false);
            builder.column().name("user_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("role_code").type("INT").nullable(true).unique(false);
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("creator_id").type("BIGINT").nullable(false).unique(false);
            builder.column().name("creation_time").type("DATETIME").nullable(true).unique(false).defaultValue("NOW()");
            builder.column().name("modifier_id").type("BIGINT").nullable(false).unique(false);
            builder.column().name("modification_time").type("DATETIME").nullable(true).unique(false)
                    .defaultValue("NOW()").onUpdate("NOW()");
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        schemaBuilder.table("user", builder -> {
            builder.column().name("username").type("VARCHAR(30)").nullable(true).unique(true);
            builder.column().name("email").type("VARCHAR(30)").nullable(true).unique(true);
            builder.column().name("encrypted_password").type("VARCHAR(100)").nullable(true).unique(false);
            builder.column().name("state").type("INT").nullable(true).unique(false).defaultValue("0");
            builder.column().name("nickname").type("VARCHAR(30)").nullable(true).unique(false);
            builder.column().name("github_info").type("VARCHAR(100)").nullable(true).unique(false);
            builder.column().name("google_info").type("VARCHAR(100)").nullable(true).unique(false);
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("creator_id").type("BIGINT").nullable(false).unique(false);
            builder.column().name("creation_time").type("DATETIME").nullable(true).unique(false).defaultValue("NOW()");
            builder.column().name("modifier_id").type("BIGINT").nullable(false).unique(false);
            builder.column().name("modification_time").type("DATETIME").nullable(true).unique(false)
                    .defaultValue("NOW()").onUpdate("NOW()");
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
    }
}