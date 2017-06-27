package fjab

/**
 * Created by franciscoalvarez on 23/06/2017.
 */
package object graphs {

  type Vertex = Int
  type Path = List[Vertex]
  type Point = (Vertex, Path)//tuple representing a vertex and the path to said vertex

}
