package com.hospitalManagement.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Type enumeration represents the type of room
 *
 * @see com.hospitalManagement.entity.Room
 */
@AllArgsConstructor
@Getter
public enum Type {
    OPERATING,
    RECEPTION,
    WARD
}
