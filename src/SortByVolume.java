package src;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.print.event.PrintEvent;

public class SortByVolume implements CollectionSorter{

    @Override
    public void sort(List<Comic> comics) {
       comics.sort((Comic c1, Comic c2) -> c1.getVolumeNumber() > c2.getVolumeNumber() ? 1 : -1);
    }
    
    public static void main(String[] args) {
        //test sort by volume
        List<Creator> cList = new ArrayList<>();
        Creator first = new Creator("CREATOR1");
        Creator second = new Creator("CREATOR2");

        List<Comic> comics = new ArrayList<>();
        Comic firstComic = new Comic(null, "SERIES 1", null, 1, null, null, cList, null, 0, null);
        Comic secondComic = new Comic(null, "SERIES 2", null, 2, null, null, cList, null, 0, null);

        comics.add(secondComic);
        comics.add(firstComic);

        SortByVolume sort = new SortByVolume();
        System.out.println(comics.get(0).getSeriesTitle()); //pre sorted
        System.out.println(comics.get(1).getSeriesTitle());

        sort.sort(comics);
        System.out.println(comics.get(0).getSeriesTitle());
        System.out.println(comics.get(1).getSeriesTitle());

    }
}
