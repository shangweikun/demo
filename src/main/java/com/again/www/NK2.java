package com.again.www;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Node {
	Character c;
	Integer count;

	public Node(Character c) {
		this.c = c;
		this.count = 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (other.c.equals(this.c))
			return true;
		else
			return false;
	}

}

public class NK2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			List<Node> lst = new LinkedList<>();
			String s = in.nextLine();
			char[] ci = s.toCharArray();
			Node tmp;
			for (char one : ci) {
				tmp = new Node(one);
				if (lst.contains(tmp)) {
					tmp = get(lst, tmp);
					tmp.count += 1;
				} else {
					lst.add(tmp);
				}
			}
			lst.sort(new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					if (o1.count == o2.count)
						return o1.c.compareTo(o2.c);
					else
						return o2.count - o1.count;
				}

			});

			Iterator<Node> ite = lst.iterator();
			while(ite.hasNext()) {
				System.out.print(ite.next().c);
			}
			System.out.println();
		}
	}

	private static Node get(List<Node> lst, Node tmp) {
		Iterator<Node> ite = lst.iterator();
		Node result = null;
		while (ite.hasNext()) {
			if ((result = ite.next()).equals(tmp))
				return result;
		}
		return result;
	}
}
