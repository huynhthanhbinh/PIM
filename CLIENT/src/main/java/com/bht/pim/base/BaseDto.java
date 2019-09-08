package com.bht.pim.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

/**
 * @author bht
 */
@Log4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto {

    @NonNull
    protected Long id; // unique id of entity

    protected Long version; // concurrent control
}