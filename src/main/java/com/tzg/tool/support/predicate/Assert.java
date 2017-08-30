package com.tzg.tool.support.predicate;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Assert {

    private final static Predicate< String > blankPredicate = StringUtils::isBlank;

    public static void isNull( Object object, String message ) {
        Objects.requireNonNull( object, message );
    }

    public static void notNull( Object object, String message ) {
        Objects.requireNonNull( object, message );
    }

    public static void areNotNull( String message, Object... obj ) {
        notNull( obj, message );
        Arrays.stream( obj ).forEach( ( object ) -> {
            if ( Objects.isNull( object ) ) {
                throw new IllegalArgumentException( message );
            }
        } );
    }

    public static void equals( Object src, Object tar, String message ) {

        if ( Objects.isNull( src ) && Objects.isNull( tar ) ) {
            return;
        }

        if ( Objects.isNull( src ) || Objects.isNull( tar ) ) {
            throw new IllegalArgumentException( message );
        }

        if ( !Objects.equals( src, tar ) ) {
            throw new IllegalArgumentException( message );
        }

    }

    public static void hasText( String text ) {
        hasText( text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank" );
    }

    public static void hasText( String text, String message ) {
        Predicate< String > predicate = StringUtils::isBlank;

        if ( predicate.test( text ) ) {
            throw new IllegalArgumentException( message );
        }
    }

    public static void isTrue( boolean expression ) {
        isTrue( expression, "[Assertion failed] - this expression must be true" );
    }

    public static void isTrue( boolean expression, String message ) {
        Predicate< Boolean > predicate = ( exp ) -> !exp;
        if ( predicate.test( expression ) ) {
            throw new IllegalArgumentException( message );
        }
    }

    public static void notTrue( boolean expression, String message ) {
        if ( expression ) {
            throw new IllegalArgumentException( message );
        }
    }

    public static void hasLengthText( String text, int length, String message ) {
        Predicate< String > blankPredicate   = StringUtils::isBlank;
        Predicate< String > comparePredicate = ( txt ) -> txt.trim().length() != length;

        if ( blankPredicate.or( comparePredicate ).test( text ) ) {
            throw new IllegalArgumentException( message );
        }
    }

    public static void isRegionText( String text, int beg, int end, String message ) {

        if ( blankPredicate.test( text ) ) {
            throw new IllegalArgumentException( message );
        }

        Predicate< Integer > lessThanPredicate   = ( len ) -> len < beg;
        Predicate< Integer > greaterThanPrediate = ( len ) -> len > end;

        if ( lessThanPredicate.or( greaterThanPrediate ).test( text.trim().length() ) ) {
            throw new IllegalArgumentException( message );
        }
    }

    public static void isContainText( String text, String[] texts, String message ) {

        if ( blankPredicate.test( text ) ) {
            throw new IllegalArgumentException( message );
        }

        boolean             expression = false;
        Predicate< String > predicate  = ( val ) -> text.equals( val );
        if ( Arrays.stream( texts ).anyMatch( predicate ) ) {
            expression = true;
        }

        isTrue( expression, message );
    }

    public static void isFromChar( String resourceStr, String[] fromStrs, String message ) {

        if ( blankPredicate.test( resourceStr ) ) {
            throw new IllegalArgumentException( message );
        }

        boolean expression    = true;
        char[]  resourceChars = resourceStr.trim().toCharArray();

        for ( int i = 0; i < resourceChars.length; i++ ) {
            boolean flag = false;
            for ( int j = 0; j < fromStrs.length; j++ ) {
                if ( String.valueOf( resourceChars[ i ] ).equals( fromStrs[ j ] ) ) {
                    flag = true;
                    break;
                }
            }
            if ( !flag ) {
                expression = false;
                break;
            }
        }

        isTrue( expression, message );

    }

    public static void isNumber( String str, String message ) {

        if ( blankPredicate.test( str ) ) {
            throw new IllegalArgumentException( message );
        }

        Pattern pattern = Pattern.compile( "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$" );
        Matcher match   = pattern.matcher( str );
        isTrue( match.matches(), message );

    }

}
