import sys

# ************************************************
#   CSC 1800-002 Programming Assignment #2
#   Last updated by Miguel Corte-Real on 9/12/16
# ************************************************

# Initialize a dictionary representing the family tree
family_tree = {}


# Functions used to handle the user input
def handle_event(splitstr):
    p1 = get_person(splitstr[1])
    p2 = get_person(splitstr[2])
    if not are_married(p1, p2):
        p1.spouses.append(p2)
        p2.spouses.append(p1)
    if len(splitstr) is 4:
        p3 = Person(splitstr[3], p1, p2)
        family_tree[p3.name] = p3
        p1.children.append(p3)
        p2.children.append(p3)


def handle_r_query(splitstr):
    p1 = get_person(splitstr[1])
    p2 = get_person(splitstr[2])
    if p1 in p2.spouses:
        return "spouse"
    if p1 in p2.parents:
        return "parent"
    if p1 in get_siblings(p2):
        return "sibling"
    if p1 in get_half_siblings(p2):
        return "half-sibling"
    # TODO: ancestor
    # TODO: cousin
    return "unrelated"


def handle_x_query(splitstr):
    p1 = get_person(splitstr[1])
    p2 = get_person(splitstr[3])
    relation = splitstr[2]
    if relation == "spouse":
        return p1 in p2.spouses
    elif relation == "parent":
        return p1 in p2.parents
    elif relation == "sibling":
        return p1 in get_siblings(p2)
    elif relation == "half-sibling":
        return p1 in get_half_siblings(p2)
    # TODO: ancestor
    # TODO: cousin
    # TODO: unrelated


def handle_w_query(splitstr):
    p = get_person(splitstr[2])
    relation = splitstr[1]
    names = []
    if relation == "spouse":
        names = sorted([s.name for s in p.spouses])
    if relation == "parent":
        names = sorted([r.name for r in p.parents])
    if relation == "sibling":
        names = sorted([s.name for s in get_siblings(p)])
    if relation == "half-sibling":
        names = sorted([hs.name for hs in get_half_siblings(p)])
    # TODO: ancestor
    # TODO: cousin
    # TODO: unrelated
    for name in names:
        print(name)


def are_married(p1, p2):
    return p2 in p1.spouses and p1 in p2.spouses


def get_siblings(p):
    if len(p.parents) == 0:
        return []
    siblings = list(set(p.parents[0].children) & set(p.parents[1].children))
    return [s for s in siblings if s is not p]


def get_half_siblings(p):
    if len(p.parents) == 0:
        return[]
    half_siblings = list((set(p.parents[0].children) | set(p.parents[1].children))
                         - (set(p.parents[0].children) & set(p.parents[1].children)))
    return [hs for hs in half_siblings if hs is not p]


# TODO: get_ancestors
# def get_ancestors(p):
#     # do stuff


def get_person(name):
    if name in family_tree.keys():
        person = family_tree.get(name)
    else:
        person = Person(name)
        family_tree[person.name] = person
    return person


# The Person class
class Person:
    def __init__(self, name, parent1=None, parent2=None):
        self.name = name
        self.parents = []
        if parent1 is not None:
            self.parents.insert(0, parent1)
        if parent2 is not None:
            self.parents.insert(1, parent2)
        self.children = []
        self.spouses = []

    def add_child(self, child):
        self.children.append(child)

    def add_spouse(self, spouse):
        if spouse not in self.spouses:
            self.spouses.append(spouse)

    def get_ancestors(self, ancestors):
        ancestors.append(self)
        if len(self.parents) != 0:
            ancestors.append(self.parents[0].get_ancestors(ancestors))
            ancestors.append(self.parents[1].get_ancestors(ancestors))
        return ancestors

# Read the input, build tree, and handle queries
for line in sys.stdin:
    delim_line = line.rstrip().split(' ')
    if line.startswith('E'):
        handle_event(delim_line)
    else:
        print("\n" + line.rstrip())
        if line.startswith('R'):
            print(handle_r_query(delim_line))
        elif line.startswith('X'):
            print(handle_x_query(delim_line))
        elif line.startswith('W'):
            handle_w_query(delim_line)
