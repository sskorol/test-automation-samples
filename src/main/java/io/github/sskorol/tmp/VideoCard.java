package io.github.sskorol.tmp;

/**
 * Author: io.github.sskorol
 */
public class VideoCard {
    private final VideoModel model;

    public VideoCard(VideoModel model) {
        this.model = model;
    }

    public VideoModel getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoCard videoCard = (VideoCard) o;

        return model == videoCard.model;
    }

    @Override
    public int hashCode() {
        return model.hashCode();
    }

    @Override
    public String toString() {
        return "VideoCard{" +
                "model=" + model +
                '}';
    }
}
