package net.bplaced.abzzezz.videodroid.util.provider.providers.apimdb;

import net.bplaced.abzzezz.videodroid.util.connection.ParcelableWatchableURLConnection;
import net.bplaced.abzzezz.videodroid.util.provider.Provider;
import net.bplaced.abzzezz.videodroid.util.task.TaskExecutor;
import net.bplaced.abzzezz.videodroid.util.task.tasks.movie.ApiMdbRequestMovieLinkTask;
import net.bplaced.abzzezz.videodroid.util.task.tasks.tv.ApiMdbRequestTVLinkTask;
import net.bplaced.abzzezz.videodroid.util.watchable.Movie;
import net.bplaced.abzzezz.videodroid.util.watchable.TVShow;
import net.bplaced.abzzezz.videodroid.util.watchable.models.Episode;

import java.util.Optional;
import java.util.function.Consumer;

public class ApiMdb extends Provider {


    @Override
    public void requestMovieLink(Movie watchable, Consumer<Optional<ParcelableWatchableURLConnection>> directConnectionConsumer, Consumer<String> exceptionConsumer) {
        new ApiMdbRequestMovieLinkTask(watchable).executeAsync(new TaskExecutor.Callback<Optional<ParcelableWatchableURLConnection>>() {
            @Override
            public void onComplete(Optional<ParcelableWatchableURLConnection> result) throws Exception {
                directConnectionConsumer.accept(result);
            }

            @Override
            public void preExecute() {

            }

            @Override
            public void exceptionCaught(Exception e) {
                exceptionConsumer.accept(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void requestTVLink(TVShow watchable, Episode episode, Consumer<Optional<ParcelableWatchableURLConnection>> directConnectionConsumer, Consumer<String> exceptionConsumer) {
        new ApiMdbRequestTVLinkTask(watchable, episode.getIndices()[0], episode.getIndices()[1]).executeAsync(new TaskExecutor.Callback<Optional<ParcelableWatchableURLConnection>>() {
            @Override
            public void onComplete(Optional<ParcelableWatchableURLConnection> result) throws Exception {
                directConnectionConsumer.accept(result);
            }

            @Override
            public void preExecute() {

            }

            @Override
            public void exceptionCaught(Exception e) {
                exceptionConsumer.accept(e.getLocalizedMessage());
            }
        });
    }
}
