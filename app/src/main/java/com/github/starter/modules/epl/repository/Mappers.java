package com.github.starter.modules.epl.repository;

import com.github.starter.modules.epl.model.EplMatch;
import com.github.starter.modules.epl.model.EplStanding;
import io.r2dbc.spi.Readable;

import java.util.Date;
import java.util.function.Function;

public final class Mappers {
    static Function<Readable, EplMatch> eplMatch() {
        return new Function<Readable, EplMatch>() {
            @Override
            public EplMatch apply(Readable kv) {
                EplMatch eplMatch = new EplMatch();
                eplMatch.setSeason(kv.get("season", Integer.class));
                eplMatch.setTeam(kv.get("team", String.class));
                eplMatch.setWk(kv.get("wk", Integer.class));
                eplMatch.setMatchDate(kv.get("matchDate", Date.class));
                eplMatch.setPoints(kv.get("points", Integer.class));
                eplMatch.setGf(kv.get("gf", Integer.class));
                eplMatch.setGa(kv.get("ga", Integer.class));
                eplMatch.setResult(kv.get("result", String.class));
                eplMatch.setOpponent(kv.get("opponent", String.class));
                eplMatch.setVenue(kv.get("venue", String.class));
                return eplMatch;
            }
        };
    }

    static Function<Readable, EplStanding> eplStanding() {
        return new Function<Readable, EplStanding>() {
            @Override
            public EplStanding apply(Readable kv) {
                EplStanding standing = new EplStanding();
                standing.setSeason(kv.get("season", Integer.class));
                standing.setPlayed(kv.get("played", Integer.class));
                standing.setPoints(kv.get("points", Integer.class));
                standing.setTeam(kv.get("team", String.class));

                standing.setRanking(kv.get("ranking", Integer.class));
                standing.setGf(kv.get("gf", Integer.class));
                standing.setGa(kv.get("ga", Integer.class));
                standing.setGd(kv.get("gd", Integer.class));
                return standing;
            }
        };
    }

    static <T> Function<Readable, T> field(String key, Class<T> cls) {
        return new Function<Readable, T>() {
            @Override
            public T apply(Readable kv) {
                return kv.get(key, cls);
            }
        };
    }
}
