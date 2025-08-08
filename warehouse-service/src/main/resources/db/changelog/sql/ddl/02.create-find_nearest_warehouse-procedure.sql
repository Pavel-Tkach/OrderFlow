CREATE OR REPLACE FUNCTION find_nearest_warehouse(lat DOUBLE PRECISION, lon DOUBLE PRECISION)
    RETURNS TABLE(
        id UUID,
        name TEXT,
        location TEXT,
        latitude DOUBLE PRECISION,
        longitude DOUBLE PRECISION,
        is_active BOOLEAN,
        created_at TIMESTAMP WITH TIME ZONE
                 ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            w.id,
            w.name,
            w.location,
            w.latitude,
            w.longitude,
            w.is_active,
            w.created_at
        FROM
            warehouse w
        ORDER BY
            ST_MakePoint(w.longitude, w.latitude)::GEOGRAPHY <-> ST_MakePoint(lon, lat)::GEOGRAPHY
        LIMIT 1;
END;
$$ LANGUAGE plpgsql;
