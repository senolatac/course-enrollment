package com.sha.logmanagement.model;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("log")
public class Log implements IModel {

    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    @Column("ip")
    private String ip;

    @Column("course_id")
    private Long courseId;

    @Column("log_date")
    private LocalDateTime logDate;
}
