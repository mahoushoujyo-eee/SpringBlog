package org.eee.model.entity;

import java.util.Date;

import lombok.Data;
import stark.coderaider.fluentschema.commons.annotations.AutoIncrement;
import stark.coderaider.fluentschema.commons.annotations.Column;
import stark.coderaider.fluentschema.commons.annotations.NotMapped;
import stark.coderaider.fluentschema.commons.annotations.PrimaryKey;


@Data
@NotMapped
public abstract class DomainBase
{
    /**
     * ID of the record.
     */
    @PrimaryKey
    @AutoIncrement
    private long id;

    /**
     * ID of the creator of the record.
     */
    @Column(defaultValue = "1")
    private long creatorId;

    /**
     * Creation time of the record.
     */
    @Column(defaultValue = "NOW()")
    private Date creationTime;

    /**
     * ID of the user who modifies the record.
     */
    @Column(defaultValue = "1")
    private long modifierId;

    /**
     * Last modification time of the record.
     */
    @Column(defaultValue = "NOW()", onUpdate = "NOW()")
    private Date modificationTime;
}
