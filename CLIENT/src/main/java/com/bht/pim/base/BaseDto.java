package com.bht.pim.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 *
 * @author bht
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public abstract class BaseDto {

    protected Long id; // unique id of entity
    protected Long version; // concurrent control
}