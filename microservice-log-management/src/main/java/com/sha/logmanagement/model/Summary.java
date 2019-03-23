package com.sha.logmanagement.model;

import com.datastax.driver.core.DataType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

@Data
@NoArgsConstructor
@Table("summary")
public class Summary implements IModel {

    @PrimaryKeyColumn(name = "course_id", type = PrimaryKeyType.PARTITIONED)
    private Long courseId;

    @PrimaryKeyColumn(name = "hit_count", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Long hitCount;
}
