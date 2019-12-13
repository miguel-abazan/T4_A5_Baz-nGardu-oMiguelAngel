def floyd_warshall(self):
    nodes = list(self.graph.nodes)

    for i in nodes:
        dict_i = {}
        for j in nodes:
            if i == j:
                dict_i[j] = 0
                continue
            try:
                dict_i[j] = self.graph[i][j]['weight']
            except:
                dict_i[j] = float("inf")

        self.distances[i] = dict_i

    for i in nodes:
        for j in nodes:
            for k in nodes:
                ij = self.distances[i][j]
                ik = self.distances[i][k]
                kj = self.distances[k][j]

                if ij > ik + kj:
                    self.distances[i][j] = ik + kj

    return self.distances
def print_distances(self):
    printt = ""
    for i in self.distances:
        printt = printt + str(i) + ": \t"
        for j in self.distances[i]:
            printt = printt + str(self.distances[i][j]) + "\t"
        printt = printt + "\n"
    print("\n------------------------------------")
    print(printt)
    return